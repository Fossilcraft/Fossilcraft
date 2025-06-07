package mod.fossilcraft.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import mod.fossilcraft.FossilCraftMod;
import mod.fossilcraft.block.AnalyserBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public final class ModBlocks {

        public static Map<RegistryKey<Block>, Block> ALL = new HashMap<>();

        public static final Block FOSSIL = register(
                        "fossil",
                        Block::new,
                        AbstractBlock.Settings.copy(Blocks.STONE));

        public static final Block ANALYSER = register(
                        "analyser",
                        AnalyserBlock::new,
                        AbstractBlock.Settings.copy(Blocks.IRON_BLOCK));

        private static Block register(
                        String path,
                        Function<AbstractBlock.Settings, Block> factory,
                        AbstractBlock.Settings settings) {
                RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK,
                                Identifier.of(FossilCraftMod.MOD_ID, path));

                Block block = Blocks.register(registryKey, factory, settings);
                Items.register(block);

                ALL.put(registryKey, block);
                return block;
        }

        public static void init() {
                // This method is intentionally left empty.
        }
}
