package li.cil.oc.common.event;

import li.cil.oc.OpenComputers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

/**
 * Game-event (GAME bus) handlers for OpenComputers.
 *
 * 1.12.2 migration:
 *   MinecraftForge.EVENT_BUS.register(this)  →  NeoForge.EVENT_BUS + @EventBusSubscriber
 *
 * Key changes in NeoForge 20.2+:
 * - @SubscribeEvent is no longer inherited from superclasses.
 *   All handlers MUST be in the class that is registered.
 * - Handler methods may now be private.
 * - The event object is passed as the sole parameter (no additional context arg).
 * - ICancellableEvent replaces @Cancelable.
 * - event.post() returns the event, not a boolean.
 *
 * We use the manual register() pattern (rather than @EventBusSubscriber) so the
 * registration is explicit and easier to trace.
 */
public class CommonEvents {

    private static final CommonEvents INSTANCE = new CommonEvents();

    /** Called from OpenComputers constructor. */
    public static void register() {
        NeoForge.EVENT_BUS.register(INSTANCE);
    }

    // ------------------------------------------------------------------ Level tick

    @SubscribeEvent
    public void onLevelTickEnd(LevelTickEvent.Post event) {
        // In 1.12.2: WorldTickEvent with TickEvent.Phase.END
        // Use this to flush any deferred OC network messages
    }

    // ------------------------------------------------------------------ Block break / place

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        // Notify the OC component network that a block (possibly a component) was broken.
        // 1.12.2: same event name, different import path (net.minecraftforge.event.world)
    }

    @SubscribeEvent
    public void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        // Notify the OC component network of a newly placed component block.
    }

    // ------------------------------------------------------------------ Chunk unload

    @SubscribeEvent
    public void onChunkUnload(ChunkEvent.Unload event) {
        // Invalidate any OC nodes that are in the unloading chunk.
        // 1.12.2: ChunkEvent.Unload — same idea, different import.
    }

    // ------------------------------------------------------------------ Player death

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        // Handle nano-machine deactivation / robot recall on death.
    }
}
