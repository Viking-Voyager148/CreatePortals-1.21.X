package net.voyager.createportals;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.voyager.createportals.content.brassPortal.BrassPortalCoreBlockEntity;


import static net.voyager.createportals.CreatePortals.REGISTRATE;


public class AllBlockEntityTypes {

    public static final BlockEntityEntry<BrassPortalCoreBlockEntity> BRASS_PORTAL_CORE_BE = REGISTRATE
            .blockEntity("brass_portal_core", BrassPortalCoreBlockEntity::new)
            .validBlocks(AllBlockTypes.BRASS_PORTAL_CORE_BLOCK)
            .register();

    public static void register() {

    }
}