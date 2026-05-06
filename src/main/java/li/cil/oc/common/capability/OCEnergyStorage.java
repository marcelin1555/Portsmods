package li.cil.oc.common.capability;

import li.cil.oc.common.blockentity.traits.PowerAcceptor;
import net.neoforged.neoforge.energy.IEnergyStorage;

/**
 * Adapts OC's internal energy system to NeoForge's IEnergyStorage (FE).
 *
 * 1.12.2 equivalent: li.cil.oc.common.tileentity.traits.power.* Scala traits
 * that each implemented the old IEnergyStorage from RF API.
 *
 * 1.21.1:
 * - IEnergyStorage is now from net.neoforged.neoforge.energy
 * - Exposed via getCapability(Capabilities.EnergyStorage.BLOCK, side) in the BlockEntity
 * - Conversion ratio: 1 FE = Settings.ratioRF OC-energy units (configurable)
 *
 * The LazyOptional wrapper and invalidation happen in the BlockEntity:
 *
 *   private final LazyOptional<IEnergyStorage> energyCap =
 *       LazyOptional.of(() -> new OCEnergyStorage(this));
 *
 *   @Override
 *   public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
 *       if (cap == Capabilities.EnergyStorage.BLOCK) return energyCap.cast();
 *       return super.getCapability(cap, side);
 *   }
 *
 *   @Override
 *   public void invalidateCaps() {
 *       super.invalidateCaps();
 *       energyCap.invalidate();
 *   }
 */
public class OCEnergyStorage implements IEnergyStorage {

    /** 1 FE = this many OC-energy units. Matches the old RF conversion ratio. */
    public static final double FE_TO_OC = 2.5;

    private final PowerAcceptor acceptor;

    public OCEnergyStorage(PowerAcceptor acceptor) {
        this.acceptor = acceptor;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        double ocAmount = maxReceive * FE_TO_OC;
        double space = acceptor.getEnergyBufferSize() - acceptor.getEnergyBuffer();
        double accepted = Math.min(ocAmount, space);
        if (!simulate) {
            acceptor.setEnergyBuffer(acceptor.getEnergyBuffer() + accepted);
        }
        return (int) (accepted / FE_TO_OC);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0; // OC blocks do not export FE
    }

    @Override
    public int getEnergyStored() {
        return (int) (acceptor.getEnergyBuffer() / FE_TO_OC);
    }

    @Override
    public int getMaxEnergyStored() {
        return (int) (acceptor.getEnergyBufferSize() / FE_TO_OC);
    }

    @Override
    public boolean canExtract() { return false; }

    @Override
    public boolean canReceive() { return true; }
}
