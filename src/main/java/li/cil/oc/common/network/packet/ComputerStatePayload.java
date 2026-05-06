package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Notifies the client that a computer's running/errored state changed.
 *
 * 1.12.2 equivalent: PacketSender.sendComputerState(te, running, errored)
 * → manually serialized over SimpleNetworkWrapper channel.
 *
 * 1.21.1: Java record + StreamCodec. StreamCodec replaces manual ByteBuf read/write.
 *
 * Sending (server side):
 *   PacketDistributor.sendToPlayersTrackingChunk(
 *       (ServerLevel) level, new ChunkPos(pos),
 *       new ComputerStatePayload(pos, running, errored));
 */
public record ComputerStatePayload(BlockPos pos, boolean running, boolean errored)
        implements CustomPacketPayload {

    public static final Type<ComputerStatePayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "computer_state"));

    public static final StreamCodec<ByteBuf, ComputerStatePayload> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,      ComputerStatePayload::pos,
                    ByteBufCodecs.BOOL,         ComputerStatePayload::running,
                    ByteBufCodecs.BOOL,         ComputerStatePayload::errored,
                    ComputerStatePayload::new);

    @Override
    public Type<ComputerStatePayload> type() { return TYPE; }
}
