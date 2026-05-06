package li.cil.oc.common.machine;

import li.cil.oc.common.blockentity.ComputerCaseBlockEntity;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;

/**
 * The core Lua computer machine.
 *
 * 1.12.2 equivalent: li.cil.oc.server.machine.Machine (Scala)
 *
 * In the original OC, Machine implements:
 *   - MachineAPI: the API exposed to Lua (component.list, component.invoke, etc.)
 *   - A Lua state via LuaJ (pure Java) or JNLua (JNI native Lua 5.3)
 *   - A signal queue (keyboard, network, component events)
 *   - A component map (address → component)
 *   - A thread pool for async execution (machine runs in its own thread)
 *   - Crash detection and error recovery
 *
 * Migration notes:
 * - The Lua runtime (LuaJ / JNLua) is language-agnostic and not MC-version-specific.
 *   The same OC-LuaJ and OC-JNLua JARs can be updated independently.
 * - The main changes are in how Machine integrates with the BlockEntity:
 *   - Old: Machine held a reference to the TileEntity (which was mutable / could be GC'd)
 *   - New: Machine is owned by ComputerCaseBlockEntity; the BE is passed on construction.
 * - Thread safety: Machine threads must synchronize on the level's server lock when
 *   accessing world state. Use ServerLevel.getServer().execute() for main-thread work.
 * - saveState / loadState replace the old save(NBTTagCompound) / load() pair.
 *
 * This is a stub. The full port of the Lua execution engine is a separate workstream.
 */
public class Machine {

    private final ComputerCaseBlockEntity owner;
    private boolean running = false;

    public Machine(ComputerCaseBlockEntity owner) {
        this.owner = owner;
    }

    /** Called every server tick from ComputerCaseBlockEntity.serverTick(). */
    public void update() {
        if (!running) return;
        // In full impl: execute one Lua timeslice, process signal queue
    }

    /** Called when energy drops below the upkeep threshold. */
    public void onLowPower() {
        // In full impl: send low-power signal to Lua, potentially halt
    }

    /** Saves the full Lua state (memory, globals, coroutines) to NBT. */
    public CompoundTag saveState(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("running", running);
        // In full impl: serialize LuaJ/JNLua state
        return tag;
    }

    /** Restores Lua state from NBT (after server restart or chunk reload). */
    public void loadState(CompoundTag tag, HolderLookup.Provider registries) {
        running = tag.getBoolean("running");
        // In full impl: deserialize LuaJ/JNLua state
    }

    /** Releases all resources (Lua state, threads, file handles). */
    public void close() {
        running = false;
        // In full impl: interrupt machine thread, close Lua state
    }
}
