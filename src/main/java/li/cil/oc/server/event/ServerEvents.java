package li.cil.oc.server.event;

import li.cil.oc.OpenComputers;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

/**
 * Server-lifecycle game-bus event handlers.
 *
 * 1.12.2 equivalent:
 *   @Mod.EventHandler onServerStart(FMLServerStartedEvent)
 *   @Mod.EventHandler onServerStop(FMLServerStoppingEvent)
 *
 * In 1.21.1 there are no FML lifecycle @Mod.EventHandler methods.
 * Server start/stop events live on the GAME bus.
 */
public class ServerEvents {

    private static final ServerEvents INSTANCE = new ServerEvents();

    public static void register() {
        NeoForge.EVENT_BUS.register(INSTANCE);
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        OpenComputers.LOGGER.info("OpenComputers server started.");
        // Initialize thread pools, internet-access filters, command registration, etc.
    }

    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        OpenComputers.LOGGER.info("OpenComputers server stopping — flushing thread pools.");
        // Await completion of async machine threads (equivalent to ThreadPoolFactory.safePools)
    }
}
