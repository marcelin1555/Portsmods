package li.cil.oc.common.blockentity.traits;

/**
 * Trait for BlockEntities that consume OC internal energy (not RF/FE directly).
 *
 * OC uses its own energy unit internally. PowerConverter blocks translate
 * between FE (NeoForge IEnergyStorage) and OC energy.
 *
 * 1.12.2: li.cil.oc.common.tileentity.traits.PowerAcceptor (Scala trait)
 * 1.21.1: Java interface with default methods.
 */
public interface PowerAcceptor {

    /** OC internal energy buffer in OC-energy units. */
    double getEnergyBuffer();

    /** Maximum OC energy this device can store. */
    double getEnergyBufferSize();

    /**
     * Add OC energy to the buffer. Returns the amount actually accepted.
     * Respects the buffer capacity limit.
     */
    default double addEnergy(double amount) {
        double space = getEnergyBufferSize() - getEnergyBuffer();
        double accepted = Math.min(space, amount);
        setEnergyBuffer(getEnergyBuffer() + accepted);
        return accepted;
    }

    void setEnergyBuffer(double energy);

    /** Whether this device currently has enough energy to operate. */
    default boolean hasPower() {
        return getEnergyBuffer() > 0;
    }
}
