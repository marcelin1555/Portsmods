package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Syncs energy buffer and max capacity to the client for display in GUIs. */
public record PowerStatePayload(BlockPos pos, double buffer, double bufferSize)
        implements CustomPacketPayload {

    public static final Type<PowerStatePayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "power_state"));

    public static final StreamCodec<ByteBuf, PowerStatePayload> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,   PowerStatePayload::pos,
                    ByteBufCodecs.DOUBLE,    PowerStatePayload::buffer,
                    ByteBufCodecs.DOUBLE,    PowerStatePayload::bufferSize,
                    PowerStatePayload::new);

    @Override
    public Type<PowerStatePayload> type() { return TYPE; }
}
