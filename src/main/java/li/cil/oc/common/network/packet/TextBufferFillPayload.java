package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** gpu.fill() — fills a rectangular region with a single character. */
public record TextBufferFillPayload(String address, int col, int row, int w, int h, String ch)
        implements CustomPacketPayload {

    public static final Type<TextBufferFillPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "text_buffer_fill"));

    public static final StreamCodec<ByteBuf, TextBufferFillPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, TextBufferFillPayload::address,
                    ByteBufCodecs.VAR_INT,     TextBufferFillPayload::col,
                    ByteBufCodecs.VAR_INT,     TextBufferFillPayload::row,
                    ByteBufCodecs.VAR_INT,     TextBufferFillPayload::w,
                    ByteBufCodecs.VAR_INT,     TextBufferFillPayload::h,
                    ByteBufCodecs.STRING_UTF8, TextBufferFillPayload::ch,
                    TextBufferFillPayload::new);

    @Override
    public Type<TextBufferFillPayload> type() { return TYPE; }
}
