package mod.fossilcraft;

import org.slf4j.Logger;

import mod.fossilcraft.data.DinosaurJsonLoader;
import mod.fossilcraft.registry.DinosaurRegistry;
import mod.fossilcraft.registry.ModBlockEntities;
import mod.fossilcraft.registry.ModBlocks;
import mod.fossilcraft.registry.ModEntities;
import mod.fossilcraft.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class FossilCraftMod implements ModInitializer {

	public static final String MOD_ID = "fossilcraft";
	public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		DinosaurJsonLoader.load().forEach(d -> DinosaurRegistry.register(Identifier.of(MOD_ID, d.id), d));

		if (!DinosaurRegistry.all().isEmpty()) {
			ModItems.registerDinosaurItems();
			ModEntities.registerDinosaurEntities();

			LOGGER.info("FossilCraft initialized with {} dinosaurs.", DinosaurRegistry.all().size());
		} else {
			LOGGER.warn("FossilCraft initialized but no dinosaurs were loaded.");
		}

		ModBlocks.init();
		ModBlockEntities.init();
	}
}