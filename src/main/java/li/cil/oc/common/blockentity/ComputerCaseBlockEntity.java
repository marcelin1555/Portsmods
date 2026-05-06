package li.cil.oc.common.blockentity;

import li.cil.oc.common.blockentity.traits.Environment;
import li.cil.oc.common.blockentity.traits.PowerAcceptor;
import li.cil.oc.common.init.OCBlockEntities;
import li.cil.oc.common.machine.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * BlockEntity for computer cases (all tiers).
 *
 * 1.12.2 equivalent: li.cil.oc.common.tileentity.Case (Scala)
 *
 * Key migrations:
 * - TileEntity → BlockEntity
 * - update() tick → serverTick() called by the block's getTicker()
 * - IItemHandler capability: getCapability(cap, side) replaced by
 *   NeoForge 21.1 block capability system (registered in the block class)
 * - Energy stored as OC-energy internally; FE conversion handled by PowerConverter
 */
public class ComputerCaseBlockEntity extends BaseBlockEntity
        implements Environment, PowerAcceptor, BlockEntityTicker<ComputerCaseBlockEntity> {

    // ------------------------------------------------------------------ Constants
    private static final String TAG_TIER          = "tier";
    private static final String TAG_ENERGY        = "energy";
    private static final String TAG_ADDRESS       = "address";
    private static final String TAG_MACHINE        = "machine";
    private static final int    COMPONENT_SLOTS   = 4; // tier determines actual count
    private static final int    EXPANSION_SLOTS   = 6;
    private static final int    TOTAL_SLOTS       = COMPONENT_SLOTS + EXPANSION_SLOTS;

    // ------------------------------------------------------------------ State
    private final int tier;
    private double energyBuffer = 0.0;
    private String nodeAddress  = UUID.randomUUID().toString();
    private boolean running     = false;
    private boolean errored     = false;

    /** Internal inventory — component slots + expansion slots. */
    private final ItemStackHandler inventory = new ItemStackHandler(TOTAL_SLOTS) {
        @Override
        public int getSlotLimit(int slot) {
            return 1; // each slot holds exactly one component
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    /** The Lua machine instance. Null when the computer is not running. */
    @Nullable
    private Machine machine;

    // ------------------------------------------------------------------ Constructors

    public ComputerCaseBlockEntity(BlockPos pos, BlockState state) {
        this(1, pos, state);
    }

    public ComputerCaseBlockEntity(int tier, BlockPos pos, BlockState state) {
        super(OCBlockEntities.CASE.get(), pos, state);
        this.tier = tier;
    }

    // ------------------------------------------------------------------ Ticking

    /**
     * Called every server tick by ComputerCaseBlock.getTicker().
     *
     * In 1.12.2, TileEntity.update() was called automatically.
     * In 1.21.1, the block must explicitly opt-in to ticking.
     */
    public static void serverTick(Level level, BlockPos pos, BlockState state,
                                  ComputerCaseBlockEntity be) {
        be.tick(level, pos, state, be);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state,
                     ComputerCaseBlockEntity self) {
        if (level.isClientSide) return;

        if (machine != null) {
            machine.update();
        }

        // Drain energy per tick (placeholder rate — read from Settings in full port)
        double upkeep = tier * 0.5;
        if (energyBuffer >= upkeep) {
            energyBuffer -= upkeep;
        } else {
            // Not enough power — pause execution
            if (machine != null) machine.onLowPower();
            energyBuffer = 0;
        }
    }

    // ------------------------------------------------------------------ Machine control

    public boolean isRunning() { return running; }

    public boolean isErrored() { return errored; }

    public void start() {
        if (level == null || level.isClientSide || running) return;
        machine = new Machine(this);
        running = true;
        errored = false;
        setChanged();
        sendUpdate();
    }

    public void stop() {
        if (machine != null) {
            machine.close();
            machine = null;
        }
        running = false;
        setChanged();
        sendUpdate();
    }

    // ------------------------------------------------------------------ Environment

    @Override
    public String getNodeAddress() { return nodeAddress; }

    // ------------------------------------------------------------------ PowerAcceptor

    @Override
    public double getEnergyBuffer() { return energyBuffer; }

    @Override
    public double getEnergyBufferSize() {
        // Capacity scales with tier: 10 000 × tier OC-energy units
        return 10_000.0 * tier;
    }

    @Override
    public void setEnergyBuffer(double energy) {
        this.energyBuffer = Math.clamp(energy, 0, getEnergyBufferSize());
    }

    // ------------------------------------------------------------------ Inventory

    public IItemHandler getItemHandler(@Nullable Direction side) {
        return inventory;
    }

    public ItemStack getComponentStack(int slot) {
        return inventory.getStackInSlot(slot);
    }

    public int getTier() { return tier; }

    // ------------------------------------------------------------------ NBT

    @Override
    protected void writeCustomNBT(CompoundTag tag, HolderLookup.Provider registries, boolean clientSide) {
        tag.putInt(TAG_TIER, tier);
        tag.putString(TAG_ADDRESS, nodeAddress);
        tag.putBoolean("running", running);
        tag.putBoolean("errored", errored);

        if (!clientSide) {
            tag.putDouble(TAG_ENERGY, energyBuffer);
            tag.put("inventory", inventory.serializeNBT(registries));
            if (machine != null) {
                tag.put(TAG_MACHINE, machine.saveState(registries));
            }
        }
    }

    @Override
    protected void readCustomNBT(CompoundTag tag, HolderLookup.Provider registries, boolean clientSide) {
        nodeAddress = tag.getString(TAG_ADDRESS);
        running     = tag.getBoolean("running");
        errored     = tag.getBoolean("errored");

        if (!clientSide) {
            energyBuffer = tag.getDouble(TAG_ENERGY);
            if (tag.contains("inventory")) {
                inventory.deserializeNBT(registries, tag.getCompound("inventory"));
            }
            if (tag.contains(TAG_MACHINE) && running) {
                if (machine == null) machine = new Machine(this);
                machine.loadState(tag.getCompound(TAG_MACHINE), registries);
            }
        }
    }
}
