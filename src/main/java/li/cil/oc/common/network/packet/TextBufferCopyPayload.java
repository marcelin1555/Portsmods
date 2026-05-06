package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** gpu.copy() — copies a screen region to another position. */
public record TextBufferCopyPayload(String address, int col, int row, int w, int h, int tx, int ty)
        implements CustomPacketPayload {

    public static final Type<TextBufferCopyPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "text_buffer_copy"));

    public static final StreamCodec<ByteBuf, TextBufferCopyPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, TextBufferCopyPayload::address,
                    ByteBufCodecs.VAR_INT,     TextBufferCopyPayload::col,
                    ByteBufCodecs.VAR_INT,     TextBufferCopyPayload::row,
                    ByteBufCodecs.VAR_INT,     TextBufferCopyPayload::w,
                    ByteBufCodecs.VAR_INT,     TextBufferCopyPayload::h,
                    ByteBufCodecs.VAR_INT,     TextBufferCopyPayload::tx,
                    ByteBufCodecs.VAR_INT,     TextBufferCopyPayload::ty,
                    TextBufferCopyPayload::new);

    @Override
    public Type<TextBufferCopyPayload> type() { return TYPE; }
}
