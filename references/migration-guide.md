# OpenComputers: Migration Guide — 1.12.2 Forge → 1.21.1 NeoForge

This document is the canonical reference for every porting decision made in this project.

---

## 0. Language Change: Scala → Java

OpenComputers 1.12.2 was written in Scala 2.11. NeoForge 1.21.1 does not officially support
Scala in its toolchain (ForgeGradle/ModDevGradle have no Scala plugin integration, and Scala
2.11 is EOL). The port is therefore rewritten in **Java 21**.

Key Scala→Java mappings:
| Scala concept | Java 21 equivalent |
|---|---|
| `trait` | `interface` (with `default` methods) or `abstract class` |
| `object` (singleton) | `class` with `static` members |
| `case class` | `record` |
| `Option[T]` | `Optional<T>` |
| `ArrayBuffer` | `ArrayList<>` |
| `@SideOnly(Side.CLIENT)` | `@OnlyIn(Dist.CLIENT)` (now a marker only — use `DistExecutor`) |
| `@Mod.EventBusSubscriber` | `@EventBusSubscriber(modid = OC.ID)` |
| `SimpleChannel` | `CustomPacketPayload` + `RegisterPayloadHandlersEvent` |

---

## 1. Mod Entry Point

### 1.12.2 (Forge)
```scala
@Mod(modid = "opencomputers", name = "OpenComputers", version = "@VERSION@",
     dependencies = "required-after:Forge@[14.23.5,);")
object OpenComputers {
  @SidedProxy(clientSide = "li.cil.oc.client.Proxy", serverSide = "li.cil.oc.server.Proxy")
  var proxy: common.Proxy = _

  @Mod.EventHandler
  def preInit(e: FMLPreInitializationEvent): Unit = proxy.preInit(e)
}
```

### 1.21.1 (NeoForge)
```java
@Mod(OpenComputers.ID)
public class OpenComputers {
    public static final String ID = "opencomputers";

    public OpenComputers(IEventBus modEventBus) {
        OCBlocks.BLOCKS.register(modEventBus);
        OCItems.ITEMS.register(modEventBus);
        OCBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> OCClientSetup.register(modEventBus));
    }
}
```

**Key changes:**
- No more `@SidedProxy`. Use `DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ...)`.
- No lifecycle events (`preInit`/`init`/`postInit`). Use `FMLCommonSetupEvent` and `RegisterEvent`.
- Constructor receives `IEventBus` — use it to register all `DeferredRegister`s.

---

## 2. Registration: `GameRegistry` → `DeferredRegister`

### 1.12.2 (Forge)
```scala
// Block registration via @GameRegistry.ObjectHolder or GameRegistry.register()
@GameRegistry.ObjectHolder("opencomputers:case1")
val Case1: Block = null
```

### 1.21.1 (NeoForge)
```java
public class OCBlocks {
    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(Registries.BLOCK, OpenComputers.ID);

    public static final DeferredBlock<ComputerCaseBlock> CASE_TIER1 =
        BLOCKS.register("case_tier1", () -> new ComputerCaseBlock(1));
}
```

---

## 3. Tile Entities → Block Entities

### 1.12.2 (Forge)
```scala
class Case extends TileEntity with traits.Computer with traits.PowerAcceptor {
  override def readFromNBT(tag: NBTTagCompound): Unit = { super.readFromNBT(tag); ... }
  override def writeToNBT(tag: NBTTagCompound): NBTTagCompound = { super.writeToNBT(tag); ... }
  override def update(): Unit = { ... } // called every tick
}
```

### 1.21.1 (NeoForge)
```java
public class ComputerCaseBlockEntity extends BlockEntity implements BlockEntityTicker<ComputerCaseBlockEntity> {

    public ComputerCaseBlockEntity(BlockPos pos, BlockState state) {
        super(OCBlockEntities.CASE.get(), pos, state);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, ComputerCaseBlockEntity self) {
        // called by the block's getTicker() — only on server side
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        // write NBT
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        // read NBT
    }
}
```

**Block side (to enable ticking):**
```java
@Nullable
@Override
public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                               BlockEntityType<T> type) {
    return createTickerHelper(type, OCBlockEntities.CASE.get(),
        ComputerCaseBlockEntity::serverTick);
}
```

**Key changes:**
- `TileEntity` → `BlockEntity`
- `readFromNBT`/`writeToNBT` → `loadAdditional`/`saveAdditional` (takes `HolderLookup.Provider`)
- `update()` tick is no longer called automatically — the block must implement `getTicker()`
- `getUpdateTag`/`onDataPacket` for client sync remain but signatures changed

---

## 4. Networking: `SimpleNetworkWrapper` → `CustomPacketPayload`

### 1.12.2 (Forge)
```scala
val channel: SimpleNetworkWrapper = NetworkRegistry.newSimpleChannel("opencomputers")
channel.registerMessage(classOf[PacketComputerState.Handler], classOf[PacketComputerState], 0, Side.CLIENT)
```

### 1.21.1 (NeoForge)
```java
// In your mod constructor or RegisterPayloadHandlersEvent listener:
@SubscribeEvent
public static void registerPayloads(RegisterPayloadHandlersEvent event) {
    PayloadRegistrar registrar = event.registrar(OpenComputers.ID);
    registrar.playToClient(
        ComputerStatePayload.TYPE,
        ComputerStatePayload.STREAM_CODEC,
        ClientPacketHandler::handleComputerState
    );
}

// Payload record:
public record ComputerStatePayload(BlockPos pos, boolean running, boolean errored)
    implements CustomPacketPayload {

    public static final Type<ComputerStatePayload> TYPE =
        new Type<>(ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "computer_state"));

    public static final StreamCodec<FriendlyByteBuf, ComputerStatePayload> STREAM_CODEC =
        StreamCodec.composite(
            BlockPos.STREAM_CODEC, ComputerStatePayload::pos,
            ByteBufCodecs.BOOL,    ComputerStatePayload::running,
            ByteBufCodecs.BOOL,    ComputerStatePayload::errored,
            ComputerStatePayload::new
        );

    @Override public Type<ComputerStatePayload> type() { return TYPE; }
}

// Sending (server → client):
PacketDistributor.sendToPlayersTrackingChunk(
    (ServerLevel) level, new ChunkPos(pos),
    new ComputerStatePayload(pos, running, errored)
);
```

**Key changes:**
- All ~60 packet types in the original `PacketSender` become individual `record` classes implementing `CustomPacketPayload`.
- Handlers run on the network thread by default. To execute on the main thread use `event.registrar(...).executesOn(HandlerThread.MAIN)`.
- `ByteBufCodecs` replaces manual `ByteBuf` read/write.

---

## 5. Capabilities → `ICapabilityProvider` + Data Attachments

### 1.12.2 (Forge)
```scala
// In TileEntity subclass:
override def hasCapability(capability: Capability[_], facing: EnumFacing): Boolean =
  capability == CapabilityEnergy.ENERGY || super.hasCapability(capability, facing)

override def getCapability[T](capability: Capability[T], facing: EnumFacing): T =
  if (capability == CapabilityEnergy.ENERGY) CapabilityEnergy.ENERGY.cast(energyStorage)
  else super.getCapability(capability, facing)
```

### 1.21.1 (NeoForge)
```java
// In BlockEntity subclass:
private final LazyOptional<IEnergyStorage> energyOptional =
    LazyOptional.of(() -> energyStorage);

@Override
public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
    if (cap == Capabilities.EnergyStorage.BLOCK) return energyOptional.cast();
    return super.getCapability(cap, side);
}

@Override
public void invalidateCaps() {
    super.invalidateCaps();
    energyOptional.invalidate();
}
```

**Note:** In NeoForge 21.1 the energy capability key is `Capabilities.EnergyStorage.BLOCK`
(not `CapabilityEnergy.ENERGY`).

---

## 6. Events: `MinecraftForge.EVENT_BUS` → NeoForge buses

### 1.12.2 (Forge)
```scala
MinecraftForge.EVENT_BUS.register(this) // for game events
FMLCommonHandler.instance().bus().register(this) // for FML lifecycle events
```

### 1.21.1 (NeoForge)
```java
// Annotation-based — class-level registration:
@EventBusSubscriber(modid = OpenComputers.ID, bus = Bus.GAME)  // game events (drops, ticks, etc.)
public class CommonEvents { ... }

@EventBusSubscriber(modid = OpenComputers.ID, bus = Bus.MOD)   // mod lifecycle events
public class ModEvents { ... }

// All event handlers must be public static methods (or instance methods if registered manually).
```

**Breaking:** In NeoForge 20.2+, `@SubscribeEvent` is NOT inherited from superclasses.
Every handler method must be in the class annotated with `@EventBusSubscriber`.

---

## 7. Rendering: TESR → `BlockEntityRenderer`

### 1.12.2 (Forge)
```scala
ClientRegistry.bindTileEntitySpecialRenderer(classOf[Hologram], new HologramRenderer)
```

### 1.21.1 (NeoForge)
```java
// Inside a method called from the MOD bus EntityRenderersEvent.RegisterRenderers:
@SubscribeEvent
public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerBlockEntityRenderer(OCBlockEntities.HOLOGRAM.get(), HologramRenderer::new);
}
```

`BlockEntityRenderer<T>` replaces `TileEntitySpecialRenderer<T>`.
The `render(T be, float partialTick, PoseStack ps, MultiBufferSource buf, int light, int overlay)`
signature is unchanged from 1.16+ so pre-existing 1.16+ renderers are mostly compatible.

---

## 8. Item Data: NBT → `DataComponentType` (1.20.5+)

### 1.12.2 (Forge) — raw NBT on items
```scala
stack.getTagCompound.getInteger("tier")
stack.getTagCompound.setInteger("tier", tier)
```

### 1.21.1 (NeoForge) — DataComponents
```java
// Register a component type:
public class OCDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
        DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, OpenComputers.ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> TIER =
        DATA_COMPONENTS.register("tier",
            () -> DataComponentType.<Integer>builder()
                .persistent(Codec.INT)
                .networkSynchronized(ByteBufCodecs.INT)
                .build());
}

// Reading / writing:
int tier = stack.getOrDefault(OCDataComponents.TIER.get(), 1);
stack.set(OCDataComponents.TIER.get(), 3);
```

---

## 9. Embedded Lua — LuaJ / JNLua

OpenComputers embeds two Lua interpreters:
- **OC-LuaJ** — pure-Java Lua 5.2 (fallback, always available)
- **OC-JNLua** — JNI-based Lua 5.3 (requires native libraries per platform)

For NeoForge 1.21.1 the JARs must be updated:
- LuaJ: use `luaj-jse 3.0.1` from Maven or maintain the OC-LuaJ fork
- JNLua: still requires a fork; the upstream `squeek502/JNLua` has 1.18+ compatible patches

Add to `build.gradle`:
```groovy
implementation 'org.luaj:luaj-jse:3.0.1'
// implementation files("libs/OC-JNLua.jar")  // place native JAR in /libs
```

---

## 10. Checklist Before First Build

- [ ] `./gradlew runData` — regenerate block/item models and language files
- [ ] `./gradlew runGameTestServer` — basic integration tests
- [ ] Native LuaJ/JNLua JARs placed in `/libs`
- [ ] Block/item textures copied from old assets
- [ ] Lang file migrated from `.lang` (1.12) to `.json` (1.13+)
- [ ] Sound events registered and migrated from `sounds.json`
- [ ] Creative tab icon confirmed visible in-game
