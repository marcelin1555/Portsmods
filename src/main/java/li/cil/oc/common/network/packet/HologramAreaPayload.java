package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Sends a dirty region of hologram voxel data to the client.
 * The raw voxel array is sent as a compressed byte array (zlib in full impl).
 */
public record HologramAreaPayload(BlockPos pos, int minX, int minZ, int maxX, int maxZ, byte[] data)
        implements CustomPacketPayload {

    public static final Type<HologramAreaPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "hologram_area"));

    public static final StreamCodec<ByteBuf, HologramAreaPayload> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,         HologramAreaPayload::pos,
                    ByteBufCodecs.VAR_INT,         HologramAreaPayload::minX,
                    ByteBufCodecs.VAR_INT,         HologramAreaPayload::minZ,
                    ByteBufCodecs.VAR_INT,         HologramAreaPayload::maxX,
                    ByteBufCodecs.VAR_INT,         HologramAreaPayload::maxZ,
                    ByteBufCodecs.BYTE_ARRAY,      HologramAreaPayload::data,
                    HologramAreaPayload::new);

    @Override
    public Type<HologramAreaPayload> type() { return TYPE; }
}
