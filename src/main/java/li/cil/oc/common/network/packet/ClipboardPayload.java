package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Sends clipboard paste text from the client to the focused terminal on the server. */
public record ClipboardPayload(String address, String text) implements CustomPacketPayload {

    public static final Type<ClipboardPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "clipboard"));

    public static final StreamCodec<ByteBuf, ClipboardPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, ClipboardPayload::address,
                    ByteBufCodecs.STRING_UTF8, ClipboardPayload::text,
                    ClipboardPayload::new);

    @Override
    public Type<ClipboardPayload> type() { return TYPE; }
}
