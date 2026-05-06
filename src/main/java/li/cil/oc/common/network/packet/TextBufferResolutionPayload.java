package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Tells the client that a GPU changed the screen resolution. */
public record TextBufferResolutionPayload(String address, int width, int height)
        implements CustomPacketPayload {

    public static final Type<TextBufferResolutionPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "text_buffer_resolution"));

    public static final StreamCodec<ByteBuf, TextBufferResolutionPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,      TextBufferResolutionPayload::address,
                    ByteBufCodecs.VAR_INT,          TextBufferResolutionPayload::width,
                    ByteBufCodecs.VAR_INT,          TextBufferResolutionPayload::height,
                    TextBufferResolutionPayload::new);

    @Override
    public Type<TextBufferResolutionPayload> type() { return TYPE; }
}
