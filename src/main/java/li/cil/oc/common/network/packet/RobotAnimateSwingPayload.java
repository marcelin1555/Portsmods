package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Triggers a swing animation on the robot's tool arm on the client. */
public record RobotAnimateSwingPayload(BlockPos pos, int ticks) implements CustomPacketPayload {

    public static final Type<RobotAnimateSwingPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "robot_animate_swing"));

    public static final StreamCodec<ByteBuf, RobotAnimateSwingPayload> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,  RobotAnimateSwingPayload::pos,
                    ByteBufCodecs.VAR_INT,  RobotAnimateSwingPayload::ticks,
                    RobotAnimateSwingPayload::new);

    @Override
    public Type<RobotAnimateSwingPayload> type() { return TYPE; }
}
