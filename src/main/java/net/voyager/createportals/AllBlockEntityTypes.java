package net.voyager.createportals;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import com.tterrag.registrate.util.entry.BlockEntityEntry;




import static net.voyager.createportals.CreatePortals.REGISTRATE;


public class AllBlockEntityTypes {

    public static final BlockEntityEntry<KineticBlockEntity> BRASS_PORTAL_CORE_BE = REGISTRATE
            .blockEntity("brass_portal_core", KineticBlockEntity::new)
            .validBlocks(AllBlockTypes.BRASS_PORTAL_CORE_BLOCK)
            .register();

    public static void register() {

    }
}