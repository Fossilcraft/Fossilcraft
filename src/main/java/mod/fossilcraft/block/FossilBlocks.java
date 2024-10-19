package mod.fossilcraft.block;

import mod.fossilcraft.FossilCraft;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FossilBlocks {

    public static final Block FOSSIL = register(new Block(AbstractBlock.Settings.copy(Blocks.STONE)), "fossil");
    public static final Block CULTIVATOR = register(new CultivatorBlock(), "cultivator");

    public static Block register(Block block, String name) {
        Identifier id = Identifier.of(FossilCraft.MODID, name);

        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, id, blockItem);

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void initialize() {
    }
}

