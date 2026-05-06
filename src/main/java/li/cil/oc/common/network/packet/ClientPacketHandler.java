package li.cil.oc.common.network.packet;

import li.cil.oc.common.blockentity.ComputerCaseBlockEntity;
import li.cil.oc.common.blockentity.HologramBlockEntity;
import li.cil.oc.common.blockentity.ScreenBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Handles all server→client packets on the client side.
 *
 * 1.12.2 equivalent: individual IMessageHandler implementations.
 * 1.21.1: static handler methods referenced in PacketHandler.registerPayloads().
 *
 * These handlers run on the NETWORK thread by default.
 * Access to level/BE must be done via Minecraft.getInstance().execute() to
 * ensure thread safety, unless the registrar used executesOn(HandlerThread.MAIN).
 */
public final class ClientPacketHandler {

    private ClientPacketHandler() {}

    public static void handleComputerState(ComputerStatePayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            if (level == null) return;
            BlockEntity be = level.getBlockEntity(payload.pos());
            if (be instanceof ComputerCaseBlockEntity computer) {
                // Client-side state update (no machine running on client)
                // In full impl: update running/errored flags, trigger block state change
            }
        });
    }

    public static void handlePowerState(PowerStatePayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            if (level == null) return;
            BlockEntity be = level.getBlockEntity(payload.pos());
            if (be instanceof ComputerCaseBlockEntity computer) {
                computer.setEnergyBuffer(payload.buffer());
            }
        });
    }

    public static void handleTextBufferResolution(TextBufferResolutionPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            // Look up the screen BE by address in the client-side address→BE map
            // and update its resolution. Full impl maintains an address registry.
        });
    }

    public static void handleTextBufferSet(TextBufferSetPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            // Write characters into the client-side text buffer for the given address.
        });
    }

    public static void handleTextBufferCopy(TextBufferCopyPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            // Copy region within the client-side text buffer.
        });
    }

    public static void handleTextBufferFill(TextBufferFillPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            // Fill region in the client-side text buffer with a character.
        });
    }

    public static void handleHologramClear(HologramClearPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            if (level == null) return;
            BlockEntity be = level.getBlockEntity(payload.pos());
            if (be instanceof HologramBlockEntity hologram) {
                hologram.clear();
            }
        });
    }

    public static void handleHologramArea(HologramAreaPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            if (level == null) return;
            BlockEntity be = level.getBlockEntity(payload.pos());
            if (be instanceof HologramBlockEntity hologram) {
                hologram.applyAreaUpdate(
                        payload.minX(), payload.minZ(),
                        payload.maxX(), payload.maxZ(),
                        payload.data());
            }
        });
    }

    public static void handleRobotMove(RobotMovePayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            // Trigger interpolated movement animation for the robot BE.
        });
    }

    public static void handleRobotAnimateSwing(RobotAnimateSwingPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            // Trigger swing animation on the robot's arm.
        });
    }
}
