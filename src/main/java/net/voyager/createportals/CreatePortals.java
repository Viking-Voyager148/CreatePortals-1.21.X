package net.voyager.createportals;

import com.simibubi.create.Create;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.ModLoadingContext;
import net.voyager.createportals.AllBlockTypes;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(CreatePortals.MODID)
public class CreatePortals {

    public static final String MODID = "createportals";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(MOD_ID));

    public CreatePortals(IEventBus modEventBus, ModContainer modContainer) {
        CreatePortals.init(); 
        modEventBus.addListener(this::onCommonSetup);
    }

    public static void init() {
        AllBlockTypes.register();
        AllBlockEntityTypes.register();
        AllItems.register();
    }
    private void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Common setup for Create Portals initialized.");
    }

    public static class ClientModEvents {
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Client setup for Create Portals initialized.");
        }
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
