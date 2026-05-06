package li.cil.oc.common.blockentity.traits;

/**
 * Marker interface for BlockEntities that participate in the OC component network.
 *
 * 1.12.2: li.cil.oc.api.network.Environment
 * 1.21.1: same concept, now a plain Java interface.
 *
 * Implement this on any BlockEntity that should be addressable by computers.
 * The node address is stored in OCDataComponents.NODE_ADDRESS on the placed block item.
 */
public interface Environment {

    /** Returns the unique address of this component on the network. */
    String getNodeAddress();

    /** Called when this component is added to an OC network. */
    default void onConnect() {}

    /** Called when this component is removed from an OC network. */
    default void onDisconnect() {}
}
