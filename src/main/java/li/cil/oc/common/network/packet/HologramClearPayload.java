package li.cil.oc.common.network.packet;

import io.netty.buffer.ByteBuf;
import li.cil.oc.OpenComputers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Clears all hologram voxels to empty. */
public record HologramClearPayload(BlockPos pos) implements CustomPacketPayload {

    public static final Type<HologramClearPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, "hologram_clear"));

    public static final StreamCodec<ByteBuf, HologramClearPayload> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, HologramClearPayload::pos,
                    HologramClearPayload::new);

    @Override
    public Type<HologramClearPayload> type() { return TYPE; }
}
