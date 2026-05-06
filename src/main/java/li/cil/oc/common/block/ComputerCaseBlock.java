package li.cil.oc.common.block;

import li.cil.oc.common.blockentity.ComputerCaseBlockEntity;
import li.cil.oc.common.init.OCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

/**
 * The computer case block (tiers 1–4 and creative).
 *
 * 1.12.2 equivalent: li.cil.oc.common.block.Case (Scala)
 *
 * Key migration points:
 * - extends BaseEntityBlock (replaces old BlockContainer)
 * - newBlockEntity() replaces createNewTileEntity()
 * - getTicker() is required for the BE to receive tick() calls
 * - useWithoutItem() replaces onBlockActivated() — opens the GUI
 * - onRemove() replaces breakBlock() — saves state before removal
 */
public class ComputerCaseBlock extends BaseBlock {

    private final int tier;

    public ComputerCaseBlock(int tier) {
        super(machineProperties());
        this.tier = tier;
    }

    public int getTier() { return tier; }

    // ------------------------------------------------------------------ BlockEntity

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ComputerCaseBlockEntity(tier, pos, state);
    }

    /**
     * Enables ticking for this block entity.
     * Without this override, ComputerCaseBlockEntity.tick() is never called.
     *
     * 1.12.2: TileEntity.update() was called automatically every tick.
     * 1.21.1: The block must opt-in via getTicker().
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return BaseEntityBlock.createTickerHelper(
                type, OCBlockEntities.CASE.get(),
                ComputerCaseBlockEntity::serverTick);
    }

    // ------------------------------------------------------------------ Render

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    // ------------------------------------------------------------------ Interaction

    /**
     * Right-click → open the computer case GUI.
     *
     * 1.12.2: onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ)
     * 1.21.1: useWithoutItem(state, level, pos, player, hit)
     */
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ComputerCaseBlockEntity computer) {
                // In full impl: open the case GUI via player.openMenu(computer)
                // ComputerCaseBlockEntity must implement MenuProvider for that.
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    // ------------------------------------------------------------------ Removal

    /**
     * Called just before the block is removed.
     * Use this to save/stop the machine rather than destroying state.
     *
     * 1.12.2: breakBlock() was the equivalent hook.
     * 1.21.1: onRemove() is called before the block state changes.
     */
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos,
                         BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ComputerCaseBlockEntity computer) {
                computer.stop();
            }
            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }
}
