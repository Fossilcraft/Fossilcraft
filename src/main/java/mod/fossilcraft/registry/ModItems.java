package mod.fossilcraft.registry;

import java.util.function.Function;

import mod.fossilcraft.FossilCraftMod;
import mod.fossilcraft.dinosaur.Dinosaur;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public final class ModItems {

    public static final Item BIO_FOSSIL = register("bio_fossil");

    public static void registerDinosaurItems() {
        for (Dinosaur d : DinosaurRegistry.all().values()) {

            // Register the DNA item
            register(d.id + "_dna");

            // Register the egg item
            register(d.id + "_egg", Item::new, new Item.Settings().maxCount(1));
        }
    }

    private static Item register(String path) {
        return register(path, null, null);
    }

    private static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(FossilCraftMod.MOD_ID, path));

        // If the factory or settings are null, use default values

        if (factory == null) {
            factory = Item::new;
        }

        if (settings == null) {
            settings = new Item.Settings();
        }

        return Items.register(key, factory, settings);
    }
}
