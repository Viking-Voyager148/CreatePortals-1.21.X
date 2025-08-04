package net.voyager.createportals.content.item;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voyager.createportals.CreatePortals;

public class modItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreatePortals.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
