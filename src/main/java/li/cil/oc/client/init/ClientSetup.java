package li.cil.oc.client.init;

import li.cil.oc.client.blockentity.renderer.HologramRenderer;
import li.cil.oc.client.blockentity.renderer.ScreenRenderer;
import li.cil.oc.common.init.OCBlockEntities;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Client-only initialization registered on the MOD bus.
 *
 * Called via DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ...) in OpenComputers
 * to prevent classloading issues on dedicated servers.
 *
 * 1.12.2 equivalents:
 *   ClientRegistry.bindTileEntitySpecialRenderer()  → EntityRenderersEvent.RegisterRenderers
 *   RenderingRegistry.registerEntityRenderingHandler → EntityRenderersEvent.RegisterRenderers
 *   ModelRegistryEvent                               → ModelEvent.RegisterAdditional
 */
public class ClientSetup {

    public static void register(IEventBus modBus) {
        modBus.addListener(ClientSetup::registerRenderers);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // BlockEntityRenderers replace TileEntitySpecialRenderers (TESR).
        // The constructor receives a BlockEntityRendererProvider.Context (not used in simple cases).
        event.registerBlockEntityRenderer(OCBlockEntities.SCREEN.get(),
                ctx -> new ScreenRenderer());
        event.registerBlockEntityRenderer(OCBlockEntities.HOLOGRAM.get(),
                ctx -> new HologramRenderer());
    }
}
