package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** gpu.set(col, row, str) — writes a string to the screen buffer at a position. */
public record TextBufferSetPayload(String address, int col, int row, String text, boolean vertical)
        implements CustomPacketPayload {

    public static final Type<TextBufferSetPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "text_buffer_set"));

    public static final StreamCodec<ByteBuf, TextBufferSetPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,  TextBufferSetPayload::address,
                    ByteBufCodecs.VAR_INT,      TextBufferSetPayload::col,
                    ByteBufCodecs.VAR_INT,      TextBufferSetPayload::row,
                    ByteBufCodecs.STRING_UTF8,  TextBufferSetPayload::text,
                    ByteBufCodecs.BOOL,         TextBufferSetPayload::vertical,
                    TextBufferSetPayload::new);

    @Override
    public Type<TextBufferSetPayload> type() { return TYPE; }
}
