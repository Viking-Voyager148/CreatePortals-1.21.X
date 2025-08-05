package net.voyager.createportals;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.createmod.catnip.lang.FontHelper;
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

    // Create's Registrate instance for registering content
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID)
            .defaultCreativeTab((ResourceKey<CreativeModeTab>) null)
            .setTooltipModifierFactory(item ->
                    new ItemDescription.Modifier(item, FontHelper.Palette.BLUE));

    public CreatePortals(IEventBus modEventBus, ModContainer modContainer) {
        CreatePortals.init(); // ✅ CALL THIS or nothing registers
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
