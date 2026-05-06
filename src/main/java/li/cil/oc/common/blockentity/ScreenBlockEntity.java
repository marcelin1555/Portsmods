package li.cil.oc.common.blockentity;

import li.cil.oc.common.init.OCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

/** Manages OC text buffer (GPU output). Tier determines resolution and color depth. */
public class ScreenBlockEntity extends BaseBlockEntity {
    private final int tier;
    private int resolutionW = 80;
    private int resolutionH = 25;
    private boolean hasPower = false;

    public ScreenBlockEntity(BlockPos pos, BlockState state) {
        super(OCBlockEntities.SCREEN.get(), pos, state);
        this.tier = 1;
    }

    public int getResolutionWidth()  { return resolutionW; }
    public int getResolutionHeight() { return resolutionH; }
    public boolean hasPower()        { return hasPower; }

    @Override
    protected void writeCustomNBT(CompoundTag tag, HolderLookup.Provider r, boolean client) {
        tag.putInt("tier", tier);
        tag.putBoolean("hasPower", hasPower);
        tag.putInt("resW", resolutionW);
        tag.putInt("resH", resolutionH);
    }

    @Override
    protected void readCustomNBT(CompoundTag tag, HolderLookup.Provider r, boolean client) {
        hasPower    = tag.getBoolean("hasPower");
        resolutionW = tag.getInt("resW");
        resolutionH = tag.getInt("resH");
    }
}
