package li.cil.oc.common.init;

import li.cil.oc.OpenComputers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * All OpenComputers item registrations.
 *
 * Block items (BlockItem) are registered separately so they use their block's
 * resource location automatically. Plain items (cards, upgrades, media) are
 * registered as custom Item subclasses or vanilla Item instances.
 *
 * 1.12.2 used a Delegator + metadata approach (multiple items in one Item class).
 * 1.21.1 uses individual item registrations — one per logical item.
 * DataComponentType replaces NBT metadata for properties like tier.
 */
public class OCItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(OpenComputers.ID);

    // ---- Block items (auto-created from registered blocks) -----------------
    public static final DeferredItem<BlockItem> CASE_TIER1 =
            ITEMS.registerSimpleBlockItem(OCBlocks.CASE_TIER1);
    public static final DeferredItem<BlockItem> CASE_TIER2 =
            ITEMS.registerSimpleBlockItem(OCBlocks.CASE_TIER2);
    public static final DeferredItem<BlockItem> CASE_TIER3 =
            ITEMS.registerSimpleBlockItem(OCBlocks.CASE_TIER3);
    public static final DeferredItem<BlockItem> CASE_CREATIVE =
            ITEMS.registerSimpleBlockItem(OCBlocks.CASE_CREATIVE);

    public static final DeferredItem<BlockItem> SCREEN_TIER1 =
            ITEMS.registerSimpleBlockItem(OCBlocks.SCREEN_TIER1);
    public static final DeferredItem<BlockItem> SCREEN_TIER2 =
            ITEMS.registerSimpleBlockItem(OCBlocks.SCREEN_TIER2);
    public static final DeferredItem<BlockItem> SCREEN_TIER3 =
            ITEMS.registerSimpleBlockItem(OCBlocks.SCREEN_TIER3);

    public static final DeferredItem<BlockItem> ROBOT =
            ITEMS.registerSimpleBlockItem(OCBlocks.ROBOT);
    public static final DeferredItem<BlockItem> MICROCONTROLLER =
            ITEMS.registerSimpleBlockItem(OCBlocks.MICROCONTROLLER);
    public static final DeferredItem<BlockItem> DISK_DRIVE =
            ITEMS.registerSimpleBlockItem(OCBlocks.DISK_DRIVE);
    public static final DeferredItem<BlockItem> RAID =
            ITEMS.registerSimpleBlockItem(OCBlocks.RAID);
    public static final DeferredItem<BlockItem> ADAPTER =
            ITEMS.registerSimpleBlockItem(OCBlocks.ADAPTER);
    public static final DeferredItem<BlockItem> CABLE =
            ITEMS.registerSimpleBlockItem(OCBlocks.CABLE);
    public static final DeferredItem<BlockItem> SWITCH =
            ITEMS.registerSimpleBlockItem(OCBlocks.SWITCH);
    public static final DeferredItem<BlockItem> ACCESS_POINT =
            ITEMS.registerSimpleBlockItem(OCBlocks.ACCESS_POINT);
    public static final DeferredItem<BlockItem> CAPACITOR =
            ITEMS.registerSimpleBlockItem(OCBlocks.CAPACITOR);
    public static final DeferredItem<BlockItem> CHARGER =
            ITEMS.registerSimpleBlockItem(OCBlocks.CHARGER);
    public static final DeferredItem<BlockItem> POWER_CONVERTER =
            ITEMS.registerSimpleBlockItem(OCBlocks.POWER_CONVERTER);
    public static final DeferredItem<BlockItem> POWER_DISTRIBUTOR =
            ITEMS.registerSimpleBlockItem(OCBlocks.POWER_DISTRIBUTOR);
    public static final DeferredItem<BlockItem> REDSTONE =
            ITEMS.registerSimpleBlockItem(OCBlocks.REDSTONE);
    public static final DeferredItem<BlockItem> MOTION_SENSOR =
            ITEMS.registerSimpleBlockItem(OCBlocks.MOTION_SENSOR);
    public static final DeferredItem<BlockItem> GEOLYZER =
            ITEMS.registerSimpleBlockItem(OCBlocks.GEOLYZER);
    public static final DeferredItem<BlockItem> TRANSPOSER =
            ITEMS.registerSimpleBlockItem(OCBlocks.TRANSPOSER);
    public static final DeferredItem<BlockItem> ASSEMBLER =
            ITEMS.registerSimpleBlockItem(OCBlocks.ASSEMBLER);
    public static final DeferredItem<BlockItem> DISASSEMBLER =
            ITEMS.registerSimpleBlockItem(OCBlocks.DISASSEMBLER);
    public static final DeferredItem<BlockItem> HOLOGRAM_TIER1 =
            ITEMS.registerSimpleBlockItem(OCBlocks.HOLOGRAM_TIER1);
    public static final DeferredItem<BlockItem> HOLOGRAM_TIER2 =
            ITEMS.registerSimpleBlockItem(OCBlocks.HOLOGRAM_TIER2);
    public static final DeferredItem<BlockItem> PRINT =
            ITEMS.registerSimpleBlockItem(OCBlocks.PRINT);
    public static final DeferredItem<BlockItem> PRINTER =
            ITEMS.registerSimpleBlockItem(OCBlocks.PRINTER);
    public static final DeferredItem<BlockItem> RACK =
            ITEMS.registerSimpleBlockItem(OCBlocks.RACK);
    public static final DeferredItem<BlockItem> WAYPOINT =
            ITEMS.registerSimpleBlockItem(OCBlocks.WAYPOINT);
    public static final DeferredItem<BlockItem> NET_SPLITTER =
            ITEMS.registerSimpleBlockItem(OCBlocks.NET_SPLITTER);
    public static final DeferredItem<BlockItem> KEYBOARD =
            ITEMS.registerSimpleBlockItem(OCBlocks.KEYBOARD);

    // ---- Media -------------------------------------------------------------
    public static final DeferredItem<Item> FLOPPY_DISK =
            ITEMS.registerSimpleItem("floppy_disk");
    public static final DeferredItem<Item> HARD_DISK_DRIVE_TIER1 =
            ITEMS.registerSimpleItem("hard_disk_drive_tier1");
    public static final DeferredItem<Item> HARD_DISK_DRIVE_TIER2 =
            ITEMS.registerSimpleItem("hard_disk_drive_tier2");
    public static final DeferredItem<Item> HARD_DISK_DRIVE_TIER3 =
            ITEMS.registerSimpleItem("hard_disk_drive_tier3");

    // ---- CPUs --------------------------------------------------------------
    public static final DeferredItem<Item> CPU_TIER1 =
            ITEMS.registerSimpleItem("cpu_tier1");
    public static final DeferredItem<Item> CPU_TIER2 =
            ITEMS.registerSimpleItem("cpu_tier2");
    public static final DeferredItem<Item> CPU_TIER3 =
            ITEMS.registerSimpleItem("cpu_tier3");

    // ---- Memory ------------------------------------------------------------
    public static final DeferredItem<Item> MEMORY_TIER1 =
            ITEMS.registerSimpleItem("memory_tier1");
    public static final DeferredItem<Item> MEMORY_TIER1_5 =
            ITEMS.registerSimpleItem("memory_tier1_5");
    public static final DeferredItem<Item> MEMORY_TIER2 =
            ITEMS.registerSimpleItem("memory_tier2");
    public static final DeferredItem<Item> MEMORY_TIER2_5 =
            ITEMS.registerSimpleItem("memory_tier2_5");
    public static final DeferredItem<Item> MEMORY_TIER3 =
            ITEMS.registerSimpleItem("memory_tier3");

    // ---- Graphics Cards ----------------------------------------------------
    public static final DeferredItem<Item> GRAPHICS_CARD_TIER1 =
            ITEMS.registerSimpleItem("graphics_card_tier1");
    public static final DeferredItem<Item> GRAPHICS_CARD_TIER2 =
            ITEMS.registerSimpleItem("graphics_card_tier2");
    public static final DeferredItem<Item> GRAPHICS_CARD_TIER3 =
            ITEMS.registerSimpleItem("graphics_card_tier3");

    // ---- Network Cards -----------------------------------------------------
    public static final DeferredItem<Item> NETWORK_CARD =
            ITEMS.registerSimpleItem("network_card");
    public static final DeferredItem<Item> WIRELESS_NETWORK_CARD_TIER1 =
            ITEMS.registerSimpleItem("wireless_network_card_tier1");
    public static final DeferredItem<Item> WIRELESS_NETWORK_CARD_TIER2 =
            ITEMS.registerSimpleItem("wireless_network_card_tier2");
    public static final DeferredItem<Item> LAN_CARD =
            ITEMS.registerSimpleItem("lan_card");
    public static final DeferredItem<Item> LINKED_CARD =
            ITEMS.registerSimpleItem("linked_card");

    // ---- Expansion Cards ---------------------------------------------------
    public static final DeferredItem<Item> REDSTONE_CARD_TIER1 =
            ITEMS.registerSimpleItem("redstone_card_tier1");
    public static final DeferredItem<Item> REDSTONE_CARD_TIER2 =
            ITEMS.registerSimpleItem("redstone_card_tier2");
    public static final DeferredItem<Item> IO_PORT =
            ITEMS.registerSimpleItem("io_port");
    public static final DeferredItem<Item> INTERNET_CARD =
            ITEMS.registerSimpleItem("internet_card");
    public static final DeferredItem<Item> DATA_CARD_TIER1 =
            ITEMS.registerSimpleItem("data_card_tier1");
    public static final DeferredItem<Item> DATA_CARD_TIER2 =
            ITEMS.registerSimpleItem("data_card_tier2");
    public static final DeferredItem<Item> DATA_CARD_TIER3 =
            ITEMS.registerSimpleItem("data_card_tier3");

    // ---- Upgrades ----------------------------------------------------------
    public static final DeferredItem<Item> UPGRADE_CHUNKLOADER =
            ITEMS.registerSimpleItem("upgrade_chunkloader");
    public static final DeferredItem<Item> UPGRADE_CONTAINER_TIER1 =
            ITEMS.registerSimpleItem("upgrade_container_tier1");
    public static final DeferredItem<Item> UPGRADE_CONTAINER_TIER2 =
            ITEMS.registerSimpleItem("upgrade_container_tier2");
    public static final DeferredItem<Item> UPGRADE_CONTAINER_TIER3 =
            ITEMS.registerSimpleItem("upgrade_container_tier3");
    public static final DeferredItem<Item> UPGRADE_CRAFTING =
            ITEMS.registerSimpleItem("upgrade_crafting");
    public static final DeferredItem<Item> UPGRADE_DATABASE_TIER1 =
            ITEMS.registerSimpleItem("upgrade_database_tier1");
    public static final DeferredItem<Item> UPGRADE_DATABASE_TIER2 =
            ITEMS.registerSimpleItem("upgrade_database_tier2");
    public static final DeferredItem<Item> UPGRADE_DATABASE_TIER3 =
            ITEMS.registerSimpleItem("upgrade_database_tier3");
    public static final DeferredItem<Item> UPGRADE_EXPERIENCE =
            ITEMS.registerSimpleItem("upgrade_experience");
    public static final DeferredItem<Item> UPGRADE_GENERATOR =
            ITEMS.registerSimpleItem("upgrade_generator");
    public static final DeferredItem<Item> UPGRADE_INVENTORY_CONTROLLER =
            ITEMS.registerSimpleItem("upgrade_inventory_controller");
    public static final DeferredItem<Item> UPGRADE_INVENTORY =
            ITEMS.registerSimpleItem("upgrade_inventory");
    public static final DeferredItem<Item> UPGRADE_LEASH =
            ITEMS.registerSimpleItem("upgrade_leash");
    public static final DeferredItem<Item> UPGRADE_NAVIGATION =
            ITEMS.registerSimpleItem("upgrade_navigation");
    public static final DeferredItem<Item> UPGRADE_PISTON =
            ITEMS.registerSimpleItem("upgrade_piston");
    public static final DeferredItem<Item> UPGRADE_SIGN =
            ITEMS.registerSimpleItem("upgrade_sign");
    public static final DeferredItem<Item> UPGRADE_SOLAR_GENERATOR =
            ITEMS.registerSimpleItem("upgrade_solar_generator");
    public static final DeferredItem<Item> UPGRADE_TANK =
            ITEMS.registerSimpleItem("upgrade_tank");
    public static final DeferredItem<Item> UPGRADE_TANK_CONTROLLER =
            ITEMS.registerSimpleItem("upgrade_tank_controller");
    public static final DeferredItem<Item> UPGRADE_TRACTOR_BEAM =
            ITEMS.registerSimpleItem("upgrade_tractor_beam");
    public static final DeferredItem<Item> UPGRADE_TRADING =
            ITEMS.registerSimpleItem("upgrade_trading");
    public static final DeferredItem<Item> UPGRADE_HOVER_TIER1 =
            ITEMS.registerSimpleItem("upgrade_hover_tier1");
    public static final DeferredItem<Item> UPGRADE_HOVER_TIER2 =
            ITEMS.registerSimpleItem("upgrade_hover_tier2");
    public static final DeferredItem<Item> UPGRADE_MOVE =
            ITEMS.registerSimpleItem("upgrade_move");
    public static final DeferredItem<Item> UPGRADE_FLIGHT =
            ITEMS.registerSimpleItem("upgrade_flight");
    public static final DeferredItem<Item> UPGRADE_SCREEN_CONTROLLER =
            ITEMS.registerSimpleItem("upgrade_screen_controller");

    // ---- Crafting materials ------------------------------------------------
    public static final DeferredItem<Item> ACME_CASING =
            ITEMS.registerSimpleItem("acme_casing");
    public static final DeferredItem<Item> ALU =
            ITEMS.registerSimpleItem("alu");
    public static final DeferredItem<Item> ARITHMETIC_LOGIC_UNIT = ALU;
    public static final DeferredItem<Item> CARD_BASE =
            ITEMS.registerSimpleItem("card_base");
    public static final DeferredItem<Item> CIRCUIT_BOARD =
            ITEMS.registerSimpleItem("circuit_board");
    public static final DeferredItem<Item> CONTROL_UNIT =
            ITEMS.registerSimpleItem("control_unit");
    public static final DeferredItem<Item> DISK_PLATTER =
            ITEMS.registerSimpleItem("disk_platter");
    public static final DeferredItem<Item> DRONE_CASE_TIER1 =
            ITEMS.registerSimpleItem("drone_case_tier1");
    public static final DeferredItem<Item> DRONE_CASE_TIER2 =
            ITEMS.registerSimpleItem("drone_case_tier2");
    public static final DeferredItem<Item> INTERWEB =
            ITEMS.registerSimpleItem("interweb");
    public static final DeferredItem<Item> MICROCHIP_TIER1 =
            ITEMS.registerSimpleItem("microchip_tier1");
    public static final DeferredItem<Item> MICROCHIP_TIER2 =
            ITEMS.registerSimpleItem("microchip_tier2");
    public static final DeferredItem<Item> MICROCHIP_TIER3 =
            ITEMS.registerSimpleItem("microchip_tier3");
    public static final DeferredItem<Item> RAW_CIRCUIT_BOARD =
            ITEMS.registerSimpleItem("raw_circuit_board");
    public static final DeferredItem<Item> TRANSISTOR =
            ITEMS.registerSimpleItem("transistor");
    public static final DeferredItem<Item> TABLET_CASE_TIER1 =
            ITEMS.registerSimpleItem("tablet_case_tier1");
    public static final DeferredItem<Item> TABLET_CASE_TIER2 =
            ITEMS.registerSimpleItem("tablet_case_tier2");
    public static final DeferredItem<Item> TABLET_CASE_CREATIVE =
            ITEMS.registerSimpleItem("tablet_case_creative");

    // ---- Misc handheld items -----------------------------------------------
    public static final DeferredItem<Item> LOOT_DISK =
            ITEMS.registerSimpleItem("loot_disk");
    public static final DeferredItem<Item> MANUAL =
            ITEMS.registerSimpleItem("manual");
    public static final DeferredItem<Item> NANO_MACHINES =
            ITEMS.registerSimpleItem("nano_machines");
    public static final DeferredItem<Item> SERVER =
            ITEMS.registerSimpleItem("server");
    public static final DeferredItem<Item> TABLET =
            ITEMS.registerSimpleItem("tablet");
    public static final DeferredItem<Item> TERMINAL_SERVER =
            ITEMS.registerSimpleItem("terminal_server");
    public static final DeferredItem<Item> TEXT_BUFFER =
            ITEMS.registerSimpleItem("text_buffer");
    public static final DeferredItem<Item> DRONE =
            ITEMS.registerSimpleItem("drone");
    public static final DeferredItem<Item> EEPROM =
            ITEMS.registerSimpleItem("eeprom");
}
