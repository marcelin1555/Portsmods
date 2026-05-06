package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Triggers a smooth movement animation on the client for a robot entity. */
public record RobotMovePayload(BlockPos from, Direction direction) implements CustomPacketPayload {

    public static final Type<RobotMovePayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "robot_move"));

    public static final StreamCodec<ByteBuf, RobotMovePayload> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    RobotMovePayload::from,
                    ByteBufCodecs.idMapper(Direction.BY_3D_DATA, Direction::get3DDataValue),
                    RobotMovePayload::direction,
                    RobotMovePayload::new);

    @Override
    public Type<RobotMovePayload> type() { return TYPE; }
}
