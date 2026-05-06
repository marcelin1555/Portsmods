package li.cil.oc.common.init;

import li.cil.oc.OpenComputers;
import li.cil.oc.common.block.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * All OpenComputers block registrations.
 *
 * 1.12.2 used @GameRegistry.ObjectHolder + GameRegistry.register().
 * 1.21.1 uses DeferredRegister — each entry is a DeferredBlock<T> supplier.
 *
 * Tier suffixes (1–4) match the original OC nomenclature.
 */
public class OCBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(OpenComputers.ID);

    // ------------------------------------------------------------------ Cases
    public static final DeferredBlock<ComputerCaseBlock> CASE_TIER1 =
            BLOCKS.register("case_tier1", () -> new ComputerCaseBlock(1));
    public static final DeferredBlock<ComputerCaseBlock> CASE_TIER2 =
            BLOCKS.register("case_tier2", () -> new ComputerCaseBlock(2));
    public static final DeferredBlock<ComputerCaseBlock> CASE_TIER3 =
            BLOCKS.register("case_tier3", () -> new ComputerCaseBlock(3));
    public static final DeferredBlock<ComputerCaseBlock> CASE_CREATIVE =
            BLOCKS.register("case_creative", () -> new ComputerCaseBlock(4));

    // ------------------------------------------------------------------ Screens
    public static final DeferredBlock<ScreenBlock> SCREEN_TIER1 =
            BLOCKS.register("screen_tier1", () -> new ScreenBlock(1));
    public static final DeferredBlock<ScreenBlock> SCREEN_TIER2 =
            BLOCKS.register("screen_tier2", () -> new ScreenBlock(2));
    public static final DeferredBlock<ScreenBlock> SCREEN_TIER3 =
            BLOCKS.register("screen_tier3", () -> new ScreenBlock(3));

    // ------------------------------------------------------------------ Robot
    public static final DeferredBlock<RobotBlock> ROBOT =
            BLOCKS.register("robot", RobotBlock::new);

    // ------------------------------------------------------------------ Microcontroller
    public static final DeferredBlock<MicrocontrollerBlock> MICROCONTROLLER =
            BLOCKS.register("microcontroller", MicrocontrollerBlock::new);

    // ------------------------------------------------------------------ Storage
    public static final DeferredBlock<DiskDriveBlock> DISK_DRIVE =
            BLOCKS.register("disk_drive", DiskDriveBlock::new);

    public static final DeferredBlock<RaidBlock> RAID =
            BLOCKS.register("raid", RaidBlock::new);

    // ------------------------------------------------------------------ Network
    public static final DeferredBlock<AdapterBlock> ADAPTER =
            BLOCKS.register("adapter", AdapterBlock::new);

    public static final DeferredBlock<CableBlock> CABLE =
            BLOCKS.register("cable", CableBlock::new);

    public static final DeferredBlock<SwitchBlock> SWITCH =
            BLOCKS.register("switch", SwitchBlock::new);

    public static final DeferredBlock<AccessPointBlock> ACCESS_POINT =
            BLOCKS.register("access_point", AccessPointBlock::new);

    // ------------------------------------------------------------------ Power
    public static final DeferredBlock<CapacitorBlock> CAPACITOR =
            BLOCKS.register("capacitor", CapacitorBlock::new);

    public static final DeferredBlock<ChargerBlock> CHARGER =
            BLOCKS.register("charger", ChargerBlock::new);

    public static final DeferredBlock<PowerConverterBlock> POWER_CONVERTER =
            BLOCKS.register("power_converter", PowerConverterBlock::new);

    public static final DeferredBlock<PowerDistributorBlock> POWER_DISTRIBUTOR =
            BLOCKS.register("power_distributor", PowerDistributorBlock::new);

    // ------------------------------------------------------------------ I/O
    public static final DeferredBlock<RedstoneBlock> REDSTONE =
            BLOCKS.register("redstone", RedstoneBlock::new);

    public static final DeferredBlock<MotionSensorBlock> MOTION_SENSOR =
            BLOCKS.register("motion_sensor", MotionSensorBlock::new);

    public static final DeferredBlock<GeolyzerBlock> GEOLYZER =
            BLOCKS.register("geolyzer", GeolyzerBlock::new);

    public static final DeferredBlock<TransposerBlock> TRANSPOSER =
            BLOCKS.register("transposer", TransposerBlock::new);

    public static final DeferredBlock<AssemblerBlock> ASSEMBLER =
            BLOCKS.register("assembler", AssemblerBlock::new);

    public static final DeferredBlock<DisassemblerBlock> DISASSEMBLER =
            BLOCKS.register("disassembler", DisassemblerBlock::new);

    // ------------------------------------------------------------------ Display / misc
    public static final DeferredBlock<HologramBlock> HOLOGRAM_TIER1 =
            BLOCKS.register("hologram_tier1", () -> new HologramBlock(1));
    public static final DeferredBlock<HologramBlock> HOLOGRAM_TIER2 =
            BLOCKS.register("hologram_tier2", () -> new HologramBlock(2));

    public static final DeferredBlock<PrintBlock> PRINT =
            BLOCKS.register("print", PrintBlock::new);

    public static final DeferredBlock<PrinterBlock> PRINTER =
            BLOCKS.register("printer", PrinterBlock::new);

    public static final DeferredBlock<RackBlock> RACK =
            BLOCKS.register("rack", RackBlock::new);

    public static final DeferredBlock<WaypointBlock> WAYPOINT =
            BLOCKS.register("waypoint", WaypointBlock::new);

    public static final DeferredBlock<NetSplitterBlock> NET_SPLITTER =
            BLOCKS.register("net_splitter", NetSplitterBlock::new);

    public static final DeferredBlock<KeyboardBlock> KEYBOARD =
            BLOCKS.register("keyboard", KeyboardBlock::new);
}
