package net.voyager.createportals.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voyager.createportals.CreatePortals;

public class modItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreatePortals.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
