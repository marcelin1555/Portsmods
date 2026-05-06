package li.cil.oc.common.init;

import li.cil.oc.OpenComputers;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Creative mode tab registration.
 *
 * 1.12.2 used a custom CreativeTabs subclass assigned per-item.
 * 1.21.1 uses DeferredRegister<CreativeModeTab> with an explicit item list.
 */
public class OCCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OpenComputers.ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN =
            TABS.register("main", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(OCItems.CASE_TIER1.get()))
                    .title(Component.translatable("itemGroup.opencomputers"))
                    .displayItems((params, output) -> {
                        // Blocks
                        output.accept(OCItems.CASE_TIER1.get());
                        output.accept(OCItems.CASE_TIER2.get());
                        output.accept(OCItems.CASE_TIER3.get());
                        output.accept(OCItems.CASE_CREATIVE.get());
                        output.accept(OCItems.SCREEN_TIER1.get());
                        output.accept(OCItems.SCREEN_TIER2.get());
                        output.accept(OCItems.SCREEN_TIER3.get());
                        output.accept(OCItems.KEYBOARD.get());
                        output.accept(OCItems.ROBOT.get());
                        output.accept(OCItems.MICROCONTROLLER.get());
                        output.accept(OCItems.DISK_DRIVE.get());
                        output.accept(OCItems.RAID.get());
                        output.accept(OCItems.ADAPTER.get());
                        output.accept(OCItems.CABLE.get());
                        output.accept(OCItems.SWITCH.get());
                        output.accept(OCItems.ACCESS_POINT.get());
                        output.accept(OCItems.CAPACITOR.get());
                        output.accept(OCItems.CHARGER.get());
                        output.accept(OCItems.POWER_CONVERTER.get());
                        output.accept(OCItems.POWER_DISTRIBUTOR.get());
                        output.accept(OCItems.REDSTONE.get());
                        output.accept(OCItems.MOTION_SENSOR.get());
                        output.accept(OCItems.GEOLYZER.get());
                        output.accept(OCItems.TRANSPOSER.get());
                        output.accept(OCItems.ASSEMBLER.get());
                        output.accept(OCItems.DISASSEMBLER.get());
                        output.accept(OCItems.HOLOGRAM_TIER1.get());
                        output.accept(OCItems.HOLOGRAM_TIER2.get());
                        output.accept(OCItems.PRINTER.get());
                        output.accept(OCItems.PRINT.get());
                        output.accept(OCItems.RACK.get());
                        output.accept(OCItems.WAYPOINT.get());
                        output.accept(OCItems.NET_SPLITTER.get());
                        // Components
                        output.accept(OCItems.CPU_TIER1.get());
                        output.accept(OCItems.CPU_TIER2.get());
                        output.accept(OCItems.CPU_TIER3.get());
                        output.accept(OCItems.MEMORY_TIER1.get());
                        output.accept(OCItems.MEMORY_TIER1_5.get());
                        output.accept(OCItems.MEMORY_TIER2.get());
                        output.accept(OCItems.MEMORY_TIER2_5.get());
                        output.accept(OCItems.MEMORY_TIER3.get());
                        output.accept(OCItems.GRAPHICS_CARD_TIER1.get());
                        output.accept(OCItems.GRAPHICS_CARD_TIER2.get());
                        output.accept(OCItems.GRAPHICS_CARD_TIER3.get());
                        output.accept(OCItems.NETWORK_CARD.get());
                        output.accept(OCItems.WIRELESS_NETWORK_CARD_TIER1.get());
                        output.accept(OCItems.WIRELESS_NETWORK_CARD_TIER2.get());
                        output.accept(OCItems.LAN_CARD.get());
                        output.accept(OCItems.LINKED_CARD.get());
                        output.accept(OCItems.REDSTONE_CARD_TIER1.get());
                        output.accept(OCItems.REDSTONE_CARD_TIER2.get());
                        output.accept(OCItems.IO_PORT.get());
                        output.accept(OCItems.INTERNET_CARD.get());
                        output.accept(OCItems.DATA_CARD_TIER1.get());
                        output.accept(OCItems.DATA_CARD_TIER2.get());
                        output.accept(OCItems.DATA_CARD_TIER3.get());
                        // Media
                        output.accept(OCItems.FLOPPY_DISK.get());
                        output.accept(OCItems.HARD_DISK_DRIVE_TIER1.get());
                        output.accept(OCItems.HARD_DISK_DRIVE_TIER2.get());
                        output.accept(OCItems.HARD_DISK_DRIVE_TIER3.get());
                        output.accept(OCItems.EEPROM.get());
                        // Upgrades
                        output.accept(OCItems.UPGRADE_GENERATOR.get());
                        output.accept(OCItems.UPGRADE_SOLAR_GENERATOR.get());
                        output.accept(OCItems.UPGRADE_CRAFTING.get());
                        output.accept(OCItems.UPGRADE_INVENTORY.get());
                        output.accept(OCItems.UPGRADE_INVENTORY_CONTROLLER.get());
                        output.accept(OCItems.UPGRADE_TANK.get());
                        output.accept(OCItems.UPGRADE_TANK_CONTROLLER.get());
                        output.accept(OCItems.UPGRADE_EXPERIENCE.get());
                        output.accept(OCItems.UPGRADE_CHUNKLOADER.get());
                        output.accept(OCItems.UPGRADE_PISTON.get());
                        output.accept(OCItems.UPGRADE_SIGN.get());
                        output.accept(OCItems.UPGRADE_NAVIGATION.get());
                        output.accept(OCItems.UPGRADE_TRADING.get());
                        output.accept(OCItems.UPGRADE_LEASH.get());
                        output.accept(OCItems.UPGRADE_TRACTOR_BEAM.get());
                        output.accept(OCItems.UPGRADE_HOVER_TIER1.get());
                        output.accept(OCItems.UPGRADE_HOVER_TIER2.get());
                        output.accept(OCItems.UPGRADE_MOVE.get());
                        output.accept(OCItems.UPGRADE_FLIGHT.get());
                        output.accept(OCItems.UPGRADE_SCREEN_CONTROLLER.get());
                        output.accept(OCItems.UPGRADE_DATABASE_TIER1.get());
                        output.accept(OCItems.UPGRADE_DATABASE_TIER2.get());
                        output.accept(OCItems.UPGRADE_DATABASE_TIER3.get());
                        output.accept(OCItems.UPGRADE_CONTAINER_TIER1.get());
                        output.accept(OCItems.UPGRADE_CONTAINER_TIER2.get());
                        output.accept(OCItems.UPGRADE_CONTAINER_TIER3.get());
                        // Misc
                        output.accept(OCItems.DRONE.get());
                        output.accept(OCItems.TABLET.get());
                        output.accept(OCItems.SERVER.get());
                        output.accept(OCItems.NANO_MACHINES.get());
                        output.accept(OCItems.MANUAL.get());
                        output.accept(OCItems.LOOT_DISK.get());
                        // Materials
                        output.accept(OCItems.MICROCHIP_TIER1.get());
                        output.accept(OCItems.MICROCHIP_TIER2.get());
                        output.accept(OCItems.MICROCHIP_TIER3.get());
                        output.accept(OCItems.TRANSISTOR.get());
                        output.accept(OCItems.ALU.get());
                        output.accept(OCItems.CONTROL_UNIT.get());
                        output.accept(OCItems.CARD_BASE.get());
                        output.accept(OCItems.CIRCUIT_BOARD.get());
                        output.accept(OCItems.RAW_CIRCUIT_BOARD.get());
                        output.accept(OCItems.DISK_PLATTER.get());
                        output.accept(OCItems.INTERWEB.get());
                        output.accept(OCItems.ACME_CASING.get());
                    })
                    .build());
}
