package li.cil.oc.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for all OpenComputers BlockEntities.
 *
 * Migration from 1.12.2 TileEntity:
 *
 * | 1.12.2                        | 1.21.1                                          |
 * |-------------------------------|-------------------------------------------------|
 * | readFromNBT(NBTTagCompound)   | loadAdditional(CompoundTag, HolderLookup)       |
 * | writeToNBT(NBTTagCompound)    | saveAdditional(CompoundTag, HolderLookup)       |
 * | getUpdateTag()                | getUpdateTag(HolderLookup) [for client sync]    |
 * | onDataPacket(...)             | handleUpdateTag(CompoundTag, HolderLookup)      |
 * | update() [every tick]         | NOT called automatically — see BlockEntityTicker|
 * | markDirty()                   | setChanged()                                    |
 *
 * Ticking is opt-in: the block must override getTicker() and return a
 * BlockEntityTicker<T> that calls the actual tick method.
 */
public abstract class BaseBlockEntity extends BlockEntity {

    protected BaseBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    // ------------------------------------------------------------------ NBT

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        writeCustomNBT(tag, registries, false);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        readCustomNBT(tag, registries, false);
    }

    // ------------------------------------------------------------------ Client sync

    /**
     * Returns the tag sent to clients when the chunk is loaded.
     * In 1.12.2 this was getUpdateTag() without parameters.
     */
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        writeCustomNBT(tag, registries, true);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        readCustomNBT(tag, registries, true);
    }

    /**
     * Sends a block entity update packet to tracking clients.
     * Call setChanged() + sendUpdate() whenever client-visible state changes.
     */
    public void sendUpdate() {
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    /** Returns the S2C packet that carries getUpdateTag() data. */
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    // ------------------------------------------------------------------ Overridable hooks

    /**
     * Write persistent or sync NBT data.
     *
     * @param clientSide true when writing data for the update packet (client sync only).
     *                   Exclude data the client doesn't need (e.g. file system content).
     */
    protected void writeCustomNBT(CompoundTag tag, HolderLookup.Provider registries, boolean clientSide) {}

    /**
     * Read persistent or sync NBT data.
     *
     * @param clientSide true when reading data from the update packet.
     */
    protected void readCustomNBT(CompoundTag tag, HolderLookup.Provider registries, boolean clientSide) {}
}
