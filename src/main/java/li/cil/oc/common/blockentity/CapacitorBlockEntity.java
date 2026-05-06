package li.cil.oc.common.blockentity;

import li.cil.oc.common.init.OCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/** Stub — full implementation pending. See references/migration-guide.md for patterns. */
public class CapacitorBlockEntity extends BaseBlockEntity {
    public CapacitorBlockEntity(BlockPos pos, BlockState state) {
        super(OCBlockEntities.CAPACITOR.get(), pos, state);
    }
}
