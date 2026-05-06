package li.cil.oc.common.blockentity;

import li.cil.oc.common.init.OCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

/**
 * Stores the 3D voxel data and display parameters for a hologram projector.
 *
 * 1.12.2 equivalent: li.cil.oc.common.tileentity.Hologram (Scala)
 *
 * Voxel array: 48×48×48 bytes for tier 1; tier 2 doubles vertical resolution.
 * Each byte: 0 = empty, 1-n = color palette index.
 * Dirty tracking: minX/minZ/maxX/maxZ mark the region sent in HologramAreaPayload.
 */
public class HologramBlockEntity extends BaseBlockEntity {

    public static final int WIDTH    = 48;
    public static final int HEIGHT_T1 = 48;
    public static final int HEIGHT_T2 = 96;

    private final int tier;
    private byte[] voxels;
    private int[]  colorPalette = { 0x00FF00, 0xFF0000, 0x0000FF };
    private float  scale = 1.0f;
    private float  rotationAngle = 0.0f;

    public HologramBlockEntity(BlockPos pos, BlockState state) {
        super(OCBlockEntities.HOLOGRAM.get(), pos, state);
        this.tier   = 1;
        this.voxels = new byte[WIDTH * WIDTH * HEIGHT_T1];
    }

    public int   getTier()            { return tier; }
    public byte[] getVoxels()         { return voxels; }
    public int[]  getColorPalette()   { return colorPalette; }
    public float  getScale()          { return scale; }
    public float  getRotationAngle()  { return rotationAngle; }

    public void clear() {
        Arrays.fill(voxels, (byte) 0);
        setChanged();
        sendUpdate();
    }

    public void applyAreaUpdate(int minX, int minZ, int maxX, int maxZ, byte[] data) {
        // In full impl: zlib-decompress data, write into voxels[] within the dirty region.
        setChanged();
    }

    @Override
    protected void writeCustomNBT(CompoundTag tag, HolderLookup.Provider r, boolean client) {
        tag.putByteArray("voxels", voxels);
        tag.putIntArray("palette", colorPalette);
        tag.putFloat("scale", scale);
    }

    @Override
    protected void readCustomNBT(CompoundTag tag, HolderLookup.Provider r, boolean client) {
        if (tag.contains("voxels"))  voxels       = tag.getByteArray("voxels");
        if (tag.contains("palette")) colorPalette = tag.getIntArray("palette");
        scale = tag.getFloat("scale");
    }
}
