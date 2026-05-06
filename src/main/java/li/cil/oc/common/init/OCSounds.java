package li.cil.oc.common.init;

import li.cil.oc.OpenComputers;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.resources.ResourceLocation;

/**
 * Sound event registrations.
 * Sound files belong in assets/opencomputers/sounds/*.ogg.
 * Register entries in assets/opencomputers/sounds.json.
 */
public class OCSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, OpenComputers.ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> COMPUTER_RUNNING =
            register("computer_running");
    public static final DeferredHolder<SoundEvent, SoundEvent> DISK_DRIVE_ACTIVITY =
            register("disk_drive_activity");
    public static final DeferredHolder<SoundEvent, SoundEvent> DISK_DRIVE_INSERT =
            register("disk_drive_insert");
    public static final DeferredHolder<SoundEvent, SoundEvent> DISK_DRIVE_EJECT =
            register("disk_drive_eject");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOT_MOVE =
            register("robot_move");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOT_TURN =
            register("robot_turn");
    public static final DeferredHolder<SoundEvent, SoundEvent> ROBOT_SWING =
            register("robot_swing");
    public static final DeferredHolder<SoundEvent, SoundEvent> KEYBOARD_CLICK =
            register("keyboard_click");
    public static final DeferredHolder<SoundEvent, SoundEvent> CHARGER_ACTIVE =
            register("charger_active");
    public static final DeferredHolder<SoundEvent, SoundEvent> ASSEMBLER_ACTIVE =
            register("assembler_active");
    public static final DeferredHolder<SoundEvent, SoundEvent> PRINTER_ACTIVE =
            register("printer_active");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () ->
                SoundEvent.createVariableRangeEvent(
                        ResourceLocation.fromNamespaceAndPath(OpenComputers.ID, name)));
    }
}
