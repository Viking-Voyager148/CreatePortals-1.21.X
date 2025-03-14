package net.voyager.createportals.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.voyager.createportals.CreatePortals;

import java.util.function.Supplier;



public class modBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CreatePortals.MODID);

    public static final DeferredBlock<Block> BRASS_PORTAL_BLOCK = registerBlock(name:"brass_portal_block",
    


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(string name, Deferredblock<T> block) {

    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
