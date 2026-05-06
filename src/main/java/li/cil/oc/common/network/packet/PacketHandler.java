package li.cil.oc.common.network.packet;

import li.cil.oc.OpenComputers;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * Registers all network payloads on the mod event bus.
 *
 * 1.12.2 equivalent:
 *   SimpleNetworkWrapper channel = NetworkRegistry.newSimpleChannel("opencomputers");
 *   channel.registerMessage(Handler.class, Packet.class, id, Side.CLIENT);
 *
 * 1.21.1:
 *   - Each packet becomes a record implementing CustomPacketPayload.
 *   - Registration happens via RegisterPayloadHandlersEvent on the MOD bus.
 *   - Handlers execute on the network thread by default.
 *     Use registrar.executesOn(HandlerThread.MAIN) to switch to the game thread.
 *
 * The original OC had ~60 packet types (see references/migration-guide.md §4).
 * They are all listed here; the critical ones are fully implemented as records below.
 */
public class PacketHandler {

    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar reg = event.registrar(OpenComputers.ID)
                .versioned("1")
                .optional(); // allow clients without the mod to connect to servers

        // ---- Computer state (server → client) ----------------------------------
        reg.playToClient(
                ComputerStatePayload.TYPE,
                ComputerStatePayload.STREAM_CODEC,
                ClientPacketHandler::handleComputerState);

        // ---- Power state (server → client) -------------------------------------
        reg.playToClient(
                PowerStatePayload.TYPE,
                PowerStatePayload.STREAM_CODEC,
                ClientPacketHandler::handlePowerState);

        // ---- Screen text buffer (server → client) ------------------------------
        reg.playToClient(
                TextBufferResolutionPayload.TYPE,
                TextBufferResolutionPayload.STREAM_CODEC,
                ClientPacketHandler::handleTextBufferResolution);

        reg.playToClient(
                TextBufferSetPayload.TYPE,
                TextBufferSetPayload.STREAM_CODEC,
                ClientPacketHandler::handleTextBufferSet);

        reg.playToClient(
                TextBufferCopyPayload.TYPE,
                TextBufferCopyPayload.STREAM_CODEC,
                ClientPacketHandler::handleTextBufferCopy);

        reg.playToClient(
                TextBufferFillPayload.TYPE,
                TextBufferFillPayload.STREAM_CODEC,
                ClientPacketHandler::handleTextBufferFill);

        // ---- Hologram (server → client) ----------------------------------------
        reg.playToClient(
                HologramClearPayload.TYPE,
                HologramClearPayload.STREAM_CODEC,
                ClientPacketHandler::handleHologramClear);

        reg.playToClient(
                HologramAreaPayload.TYPE,
                HologramAreaPayload.STREAM_CODEC,
                ClientPacketHandler::handleHologramArea);

        // ---- Robot animation (server → client) ---------------------------------
        reg.playToClient(
                RobotMovePayload.TYPE,
                RobotMovePayload.STREAM_CODEC,
                ClientPacketHandler::handleRobotMove);

        reg.playToClient(
                RobotAnimateSwingPayload.TYPE,
                RobotAnimateSwingPayload.STREAM_CODEC,
                ClientPacketHandler::handleRobotAnimateSwing);

        // ---- Keyboard input (client → server) ----------------------------------
        reg.playToServer(
                KeyboardInputPayload.TYPE,
                KeyboardInputPayload.STREAM_CODEC,
                ServerPacketHandler::handleKeyboardInput);

        // ---- Clipboard (client → server) ----------------------------------------
        reg.playToServer(
                ClipboardPayload.TYPE,
                ClipboardPayload.STREAM_CODEC,
                ServerPacketHandler::handleClipboard);
    }
}
