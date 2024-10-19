package mod.fossilcraft.item;

import mod.fossilcraft.FossilCraft;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FossilItems {

    public static final Item DINOPEDIA = register(new Item(new Item.Settings()), "dinopedia");

    public static Item register(Item item, String id) {
        Identifier itemId = Identifier.of(FossilCraft.MODID, id);
        return Registry.register(Registries.ITEM, itemId, item);
    }

    public static void initialize() {
    }
}
