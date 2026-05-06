package li.cil.oc.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

/** Stub — full implementation pending. Extends BaseBlock to satisfy OCBlocks registration. */
public class CapacitorBlock extends BaseBlock {
    public CapacitorBlock() { super(machineProperties()); }
    public CapacitorBlock(int tier) { super(machineProperties()); }

    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return null; }
}
