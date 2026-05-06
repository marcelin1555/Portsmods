package li.cil.oc.common.blockentity;

import li.cil.oc.common.init.OCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/** Stub — full implementation pending. See references/migration-guide.md for patterns. */
public class NetSplitterBlockEntity extends BaseBlockEntity {
    public NetSplitterBlockEntity(BlockPos pos, BlockState state) {
        super(OCBlockEntities.NET_SPLITTER.get(), pos, state);
    }
}
