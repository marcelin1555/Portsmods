package li.cil.oc.common.init;

import li.cil.oc.OpenComputers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OCMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, OpenComputers.ID);

    // Placeholder entries — each screen/container needs its own MenuType.
    // Use IMenuTypeExtension.create() for menus that need extra data from the server.
    // Example:
    // public static final DeferredHolder<MenuType<?>, MenuType<ComputerCaseMenu>> CASE =
    //     MENUS.register("case", () -> IMenuTypeExtension.create(ComputerCaseMenu::new));
}
