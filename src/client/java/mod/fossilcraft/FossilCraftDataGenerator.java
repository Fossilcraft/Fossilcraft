package mod.fossilcraft;

import mod.fossilcraft.registry.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.Item;

public class FossilCraftDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(DnaItemModelProvider::new);
    }

    private static class DnaItemModelProvider extends FabricModelProvider {
        public DnaItemModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateItemModels(ItemModelGenerator generator) {
            for (Item item : ModItems.ALL.values()) {
                generator.register(item, Models.GENERATED);
            }
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator generator) {

        }
    }
}
