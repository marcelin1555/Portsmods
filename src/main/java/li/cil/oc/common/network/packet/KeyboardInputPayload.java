package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Sends a keyboard character + key code from client to server.
 *
 * In 1.12.2 OC used LWJGL 2 key codes. In 1.21.1, GLFW key codes are used
 * (the Keyboard class in 1.12 → InputConstants in 1.21).
 */
public record KeyboardInputPayload(String address, char character, int keyCode, boolean isDown)
        implements CustomPacketPayload {

    public static final Type<KeyboardInputPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "keyboard_input"));

    public static final StreamCodec<ByteBuf, KeyboardInputPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,  KeyboardInputPayload::address,
                    ByteBufCodecs.SHORT.map(s -> (char)(short)s, c -> (short)(char)c),
                    KeyboardInputPayload::character,
                    ByteBufCodecs.VAR_INT,      KeyboardInputPayload::keyCode,
                    ByteBufCodecs.BOOL,         KeyboardInputPayload::isDown,
                    KeyboardInputPayload::new);

    @Override
    public Type<KeyboardInputPayload> type() { return TYPE; }
}
