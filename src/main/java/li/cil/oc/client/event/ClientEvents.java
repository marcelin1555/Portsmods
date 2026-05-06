package li.cil.oc.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderFrameEvent;

/**
 * Client-only game-bus event handlers.
 *
 * We use @EventBusSubscriber here (annotation-based) to show both patterns.
 * bus = Bus.GAME is the default and can be omitted.
 *
 * 1.12.2 equivalents:
 *   MouseEvent          → InputEvent.MouseButton
 *   KeyInputEvent       → InputEvent.Key
 *   RenderGameOverlayEvent → GuiOverlayEvent (NeoForge) — screen HUD
 *   RenderWorldLastEvent   → RenderLevelStageEvent
 */
@EventBusSubscriber(modid = "opencomputers", value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        // Forward key events to the focused OC terminal screen.
        // InputConstants replaces the old Keyboard / LWJGL2 key constants.
    }

    @SubscribeEvent
    public static void onMouseButton(InputEvent.MouseButton.Post event) {
        // Handle mouse clicks on OC screens (touch-mode forwarding).
    }

    @SubscribeEvent
    public static void onRenderFrame(RenderFrameEvent.Pre event) {
        // Advance screen cursor blink timers, etc.
    }
}
