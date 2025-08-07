package net.voyager.createportals;

import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.world.level.block.Blocks;
import net.voyager.createportals.content.brassPortal.BrassPortalCoreBlock;



import static net.voyager.createportals.CreatePortals.REGISTRATE;

public class AllBlockTypes {

    public static final BlockEntry<BrassPortalCoreBlock> BRASS_PORTAL_CORE_BLOCK =
            REGISTRATE.block("brass_portal_core", BrassPortalCoreBlock::new)
                    .initialProperties(() -> Blocks.AMETHYST_BLOCK)
                    .simpleItem()
                    .register();

    public static void register() {

    }
}
