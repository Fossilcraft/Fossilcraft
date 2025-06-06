package mod.fossilcraft;

import mod.fossilcraft.dinosaur.Dinosaur;
import mod.fossilcraft.registry.DinosaurRegistry;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

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
            for (Dinosaur dinosaur : DinosaurRegistry.all().values()) {
                Identifier dnaItemId = Identifier.of(FossilCraftMod.MOD_ID, dinosaur.id + "_dna");
                Identifier eggItemId = Identifier.of(FossilCraftMod.MOD_ID, dinosaur.id + "_egg");

                Item dnaItem = Registries.ITEM.get(dnaItemId);
                Item eggItem = Registries.ITEM.get(eggItemId);

                generator.register(dnaItem, Models.GENERATED);
                generator.register(eggItem, Models.GENERATED);
            }
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator generator) {

        }
    }
}
