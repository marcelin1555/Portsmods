package li.cil.oc.common.network.packet;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Handles all client→server packets on the server side.
 */
public final class ServerPacketHandler {

    private ServerPacketHandler() {}

    public static void handleKeyboardInput(KeyboardInputPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) ctx.player();
            // Look up the screen/keyboard component by address and forward the key event
            // into the OC signal queue of the bound computer.
        });
    }

    public static void handleClipboard(ClipboardPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) ctx.player();
            // Forward clipboard text as a series of key_down / key_up signals.
        });
    }
}
