package net.voyager.createportals;

import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.voyager.createportals.content.brassPortal.brassPortalCoreBlock;



import static net.voyager.createportals.CreatePortals.REGISTRATE;

public class AllBlockTypes {
    static {
        REGISTRATE.setCreativeTab(AllCreativeModeTabs.BASE_CREATIVE_TAB);
    }

    public static final BlockEntry<brassPortalCoreBlock> BRASS_PORTAL_CORE_BLOCK =
            REGISTRATE.block("brass_portal_core", brassPortalCoreBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .simpleItem()
                    .register();

    public static void register() {

    }
}
