package mod.fossilcraft;

import mod.fossilcraft.block.FossilBlockEntityType;
import mod.fossilcraft.block.FossilBlocks;
import mod.fossilcraft.entity.TriceratopsEntity;
import mod.fossilcraft.gui.CultivatorScreenHandler;
import mod.fossilcraft.item.FossilItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import java.util.logging.Logger;

public class FossilCraft implements ModInitializer {

    public static final Logger LOGGER = Logger.getLogger("FossilCraft");
    public static final String MODID = "fossilcraft";

    public static final EntityType<TriceratopsEntity> Triceratops = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(FossilCraft.MODID, "triceratops"),
            EntityType.Builder.create(TriceratopsEntity::new, SpawnGroup.CREATURE).dimensions(0.75f, 0.75f).build("triceratops")
    );

    public static final ScreenHandlerType<CultivatorScreenHandler> CULTIVATOR_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MODID, "cultivator"), new ScreenHandlerType<>(CultivatorScreenHandler::new, FeatureSet.empty()));

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(Triceratops, TriceratopsEntity.createDinosaurAttributes());

        FossilItems.initialize();
        FossilBlocks.initialize();
        FossilBlockEntityType.initialize();
    }
}
