package li.cil.oc.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * Base class for all OpenComputers blocks.
 *
 * 1.12.2 equivalent: li.cil.oc.common.block.SimpleBlock (Scala)
 *
 * Migration notes:
 * - Material has been removed in 1.20+. Use MapColor + SoundType directly.
 * - Block.Properties (static factory) replaces Block.Properties.create(Material.X).
 * - hasTileEntity() / createTileEntity() are replaced by overriding
 *   newBlockEntity() in the Block subclass.
 * - onBlockActivated() → useWithoutItem() / useItemOn()
 * - breakBlock() → onRemove() (called before the block is actually removed)
 */
public abstract class BaseBlock extends Block {

    protected BaseBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    /** Default properties for a machine-like OC block. Subclasses may override. */
    protected static BlockBehaviour.Properties machineProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .sound(SoundType.METAL)
                .strength(2.0f, 6.0f)
                .requiresCorrectToolForDrops();
    }
}
