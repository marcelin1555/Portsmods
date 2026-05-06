package li.cil.oc;

import li.cil.oc.common.event.CommonEvents;
import li.cil.oc.common.init.*;
import li.cil.oc.common.network.packet.PacketHandler;
import li.cil.oc.server.event.ServerEvents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(OpenComputers.ID)
public class OpenComputers {

    public static final String ID = "opencomputers";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    public OpenComputers(IEventBus modBus) {
        // Register all DeferredRegisters
        OCBlocks.BLOCKS.register(modBus);
        OCItems.ITEMS.register(modBus);
        OCBlockEntities.BLOCK_ENTITIES.register(modBus);
        OCMenuTypes.MENUS.register(modBus);
        OCSounds.SOUND_EVENTS.register(modBus);
        OCCreativeModeTabs.TABS.register(modBus);
        OCDataComponents.DATA_COMPONENTS.register(modBus);

        // Mod bus listeners
        modBus.addListener(this::commonSetup);
        modBus.addListener(PacketHandler::registerPayloads);

        // Game event bus listeners
        CommonEvents.register();
        ServerEvents.register();

        // Client-only setup — must be wrapped to avoid class loading on server
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
            () -> () -> li.cil.oc.client.init.ClientSetup.register(modBus));
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("OpenComputers common setup — Minecraft 1.21.1 / NeoForge");
    }
}
