package li.cil.oc.common.init;

import li.cil.oc.OpenComputers;
import li.cil.oc.common.blockentity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;

/**
 * All BlockEntity type registrations.
 *
 * Migration note: In 1.12.2, tile entities were registered via
 * GameRegistry.registerTileEntity(MyTE.class, "opencomputers:myTE").
 * In 1.21.1, use DeferredRegister + BlockEntityType.Builder.of(...).build(null).
 * The null is fine here; the block validation is handled by the blocks set.
 */
public class OCBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, OpenComputers.ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ComputerCaseBlockEntity>> CASE =
            BLOCK_ENTITIES.register("case", () -> BlockEntityType.Builder.of(
                    ComputerCaseBlockEntity::new,
                    OCBlocks.CASE_TIER1.get(),
                    OCBlocks.CASE_TIER2.get(),
                    OCBlocks.CASE_TIER3.get(),
                    OCBlocks.CASE_CREATIVE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ScreenBlockEntity>> SCREEN =
            BLOCK_ENTITIES.register("screen", () -> BlockEntityType.Builder.of(
                    ScreenBlockEntity::new,
                    OCBlocks.SCREEN_TIER1.get(),
                    OCBlocks.SCREEN_TIER2.get(),
                    OCBlocks.SCREEN_TIER3.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RobotBlockEntity>> ROBOT =
            BLOCK_ENTITIES.register("robot", () -> BlockEntityType.Builder.of(
                    RobotBlockEntity::new,
                    OCBlocks.ROBOT.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MicrocontrollerBlockEntity>> MICROCONTROLLER =
            BLOCK_ENTITIES.register("microcontroller", () -> BlockEntityType.Builder.of(
                    MicrocontrollerBlockEntity::new,
                    OCBlocks.MICROCONTROLLER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DiskDriveBlockEntity>> DISK_DRIVE =
            BLOCK_ENTITIES.register("disk_drive", () -> BlockEntityType.Builder.of(
                    DiskDriveBlockEntity::new,
                    OCBlocks.DISK_DRIVE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RaidBlockEntity>> RAID =
            BLOCK_ENTITIES.register("raid", () -> BlockEntityType.Builder.of(
                    RaidBlockEntity::new,
                    OCBlocks.RAID.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AdapterBlockEntity>> ADAPTER =
            BLOCK_ENTITIES.register("adapter", () -> BlockEntityType.Builder.of(
                    AdapterBlockEntity::new,
                    OCBlocks.ADAPTER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CableBlockEntity>> CABLE =
            BLOCK_ENTITIES.register("cable", () -> BlockEntityType.Builder.of(
                    CableBlockEntity::new,
                    OCBlocks.CABLE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SwitchBlockEntity>> SWITCH =
            BLOCK_ENTITIES.register("switch", () -> BlockEntityType.Builder.of(
                    SwitchBlockEntity::new,
                    OCBlocks.SWITCH.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CapacitorBlockEntity>> CAPACITOR =
            BLOCK_ENTITIES.register("capacitor", () -> BlockEntityType.Builder.of(
                    CapacitorBlockEntity::new,
                    OCBlocks.CAPACITOR.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChargerBlockEntity>> CHARGER =
            BLOCK_ENTITIES.register("charger", () -> BlockEntityType.Builder.of(
                    ChargerBlockEntity::new,
                    OCBlocks.CHARGER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RedstoneBlockEntity>> REDSTONE =
            BLOCK_ENTITIES.register("redstone", () -> BlockEntityType.Builder.of(
                    RedstoneBlockEntity::new,
                    OCBlocks.REDSTONE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HologramBlockEntity>> HOLOGRAM =
            BLOCK_ENTITIES.register("hologram", () -> BlockEntityType.Builder.of(
                    HologramBlockEntity::new,
                    OCBlocks.HOLOGRAM_TIER1.get(),
                    OCBlocks.HOLOGRAM_TIER2.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PrintBlockEntity>> PRINT =
            BLOCK_ENTITIES.register("print", () -> BlockEntityType.Builder.of(
                    PrintBlockEntity::new,
                    OCBlocks.PRINT.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PrinterBlockEntity>> PRINTER =
            BLOCK_ENTITIES.register("printer", () -> BlockEntityType.Builder.of(
                    PrinterBlockEntity::new,
                    OCBlocks.PRINTER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RackBlockEntity>> RACK =
            BLOCK_ENTITIES.register("rack", () -> BlockEntityType.Builder.of(
                    RackBlockEntity::new,
                    OCBlocks.RACK.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WaypointBlockEntity>> WAYPOINT =
            BLOCK_ENTITIES.register("waypoint", () -> BlockEntityType.Builder.of(
                    WaypointBlockEntity::new,
                    OCBlocks.WAYPOINT.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GeolyzerBlockEntity>> GEOLYZER =
            BLOCK_ENTITIES.register("geolyzer", () -> BlockEntityType.Builder.of(
                    GeolyzerBlockEntity::new,
                    OCBlocks.GEOLYZER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TransposerBlockEntity>> TRANSPOSER =
            BLOCK_ENTITIES.register("transposer", () -> BlockEntityType.Builder.of(
                    TransposerBlockEntity::new,
                    OCBlocks.TRANSPOSER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AssemblerBlockEntity>> ASSEMBLER =
            BLOCK_ENTITIES.register("assembler", () -> BlockEntityType.Builder.of(
                    AssemblerBlockEntity::new,
                    OCBlocks.ASSEMBLER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DisassemblerBlockEntity>> DISASSEMBLER =
            BLOCK_ENTITIES.register("disassembler", () -> BlockEntityType.Builder.of(
                    DisassemblerBlockEntity::new,
                    OCBlocks.DISASSEMBLER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MotionSensorBlockEntity>> MOTION_SENSOR =
            BLOCK_ENTITIES.register("motion_sensor", () -> BlockEntityType.Builder.of(
                    MotionSensorBlockEntity::new,
                    OCBlocks.MOTION_SENSOR.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetSplitterBlockEntity>> NET_SPLITTER =
            BLOCK_ENTITIES.register("net_splitter", () -> BlockEntityType.Builder.of(
                    NetSplitterBlockEntity::new,
                    OCBlocks.NET_SPLITTER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KeyboardBlockEntity>> KEYBOARD =
            BLOCK_ENTITIES.register("keyboard", () -> BlockEntityType.Builder.of(
                    KeyboardBlockEntity::new,
                    OCBlocks.KEYBOARD.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PowerConverterBlockEntity>> POWER_CONVERTER =
            BLOCK_ENTITIES.register("power_converter", () -> BlockEntityType.Builder.of(
                    PowerConverterBlockEntity::new,
                    OCBlocks.POWER_CONVERTER.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PowerDistributorBlockEntity>> POWER_DISTRIBUTOR =
            BLOCK_ENTITIES.register("power_distributor", () -> BlockEntityType.Builder.of(
                    PowerDistributorBlockEntity::new,
                    OCBlocks.POWER_DISTRIBUTOR.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AccessPointBlockEntity>> ACCESS_POINT =
            BLOCK_ENTITIES.register("access_point", () -> BlockEntityType.Builder.of(
                    AccessPointBlockEntity::new,
                    OCBlocks.ACCESS_POINT.get()
            ).build(null));
}
