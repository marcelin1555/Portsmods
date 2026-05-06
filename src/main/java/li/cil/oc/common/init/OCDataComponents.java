package li.cil.oc.common.init;

import com.mojang.serialization.Codec;
import li.cil.oc.OpenComputers;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.UUID;

/**
 * DataComponentType registrations — replaces raw NBT on items (1.20.5+).
 *
 * Migration note:
 *   1.12.2: stack.getTagCompound().getInteger("tier")
 *   1.21.1: stack.getOrDefault(OCDataComponents.TIER.get(), 1)
 *
 * For complex data that doesn't fit a primitive, use a custom Codec record.
 * For data that must remain backward-compatible with old NBT saves,
 * use CustomData component and migrate on first read.
 */
public class OCDataComponents {

    public static final DeferredRegister<net.minecraft.core.component.DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, OpenComputers.ID);

    /** Hardware tier (1–4). Used on CPUs, memory, GPUs, cases, screens, etc. */
    public static final DeferredHolder<net.minecraft.core.component.DataComponentType<?>,
            net.minecraft.core.component.DataComponentType<Integer>> TIER =
            DATA_COMPONENTS.register("tier",
                    () -> net.minecraft.core.component.DataComponentType.<Integer>builder()
                            .persistent(Codec.intRange(1, 4))
                            .networkSynchronized(ByteBufCodecs.VAR_INT)
                            .build());

    /** Unique address string assigned to every OC component (UUID-like). */
    public static final DeferredHolder<net.minecraft.core.component.DataComponentType<?>,
            net.minecraft.core.component.DataComponentType<String>> NODE_ADDRESS =
            DATA_COMPONENTS.register("node_address",
                    () -> net.minecraft.core.component.DataComponentType.<String>builder()
                            .persistent(Codec.STRING)
                            .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                            .build());

    /** EEPROM label (max 24 chars). */
    public static final DeferredHolder<net.minecraft.core.component.DataComponentType<?>,
            net.minecraft.core.component.DataComponentType<String>> EEPROM_LABEL =
            DATA_COMPONENTS.register("eeprom_label",
                    () -> net.minecraft.core.component.DataComponentType.<String>builder()
                            .persistent(Codec.string(0, 24))
                            .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                            .build());

    /** Whether a floppy/HDD is read-only. */
    public static final DeferredHolder<net.minecraft.core.component.DataComponentType<?>,
            net.minecraft.core.component.DataComponentType<Boolean>> READ_ONLY =
            DATA_COMPONENTS.register("read_only",
                    () -> net.minecraft.core.component.DataComponentType.<Boolean>builder()
                            .persistent(Codec.BOOL)
                            .networkSynchronized(ByteBufCodecs.BOOL)
                            .build());

    /** Nano-machine controller data (complex NBT blob — keep as CustomData until full migration). */
    public static final DeferredHolder<net.minecraft.core.component.DataComponentType<?>,
            net.minecraft.core.component.DataComponentType<CustomData>> NANO_MACHINES_DATA =
            DATA_COMPONENTS.register("nano_machines_data",
                    () -> net.minecraft.core.component.DataComponentType.<CustomData>builder()
                            .persistent(CustomData.CODEC)
                            .networkSynchronized(CustomData.STREAM_CODEC)
                            .build());

    /** Robot name string. */
    public static final DeferredHolder<net.minecraft.core.component.DataComponentType<?>,
            net.minecraft.core.component.DataComponentType<String>> ROBOT_NAME =
            DATA_COMPONENTS.register("robot_name",
                    () -> net.minecraft.core.component.DataComponentType.<String>builder()
                            .persistent(Codec.STRING)
                            .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                            .build());

    /** Inventory contents for robot/tablet (uses vanilla ItemContainerContents). */
    public static final DeferredHolder<net.minecraft.core.component.DataComponentType<?>,
            net.minecraft.core.component.DataComponentType<ItemContainerContents>> ROBOT_INVENTORY =
            DATA_COMPONENTS.register("robot_inventory",
                    () -> net.minecraft.core.component.DataComponentType.<ItemContainerContents>builder()
                            .persistent(ItemContainerContents.CODEC)
                            .networkSynchronized(ItemContainerContents.STREAM_CODEC)
                            .build());

    /** Energy buffer stored on chargeable items (double, milli-OC-energy). */
    public static final DeferredHolder<net.minecraft.core.component.DataComponentType<?>,
            net.minecraft.core.component.DataComponentType<Double>> ENERGY_BUFFER =
            DATA_COMPONENTS.register("energy_buffer",
                    () -> net.minecraft.core.component.DataComponentType.<Double>builder()
                            .persistent(Codec.DOUBLE)
                            .networkSynchronized(ByteBufCodecs.DOUBLE)
                            .build());
}
