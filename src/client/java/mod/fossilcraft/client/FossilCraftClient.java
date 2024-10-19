package mod.fossilcraft.client;

import mod.fossilcraft.FossilCraft;
import mod.fossilcraft.client.gui.CultivatorScreen;
import mod.fossilcraft.client.model.TriceratopsModel;
import mod.fossilcraft.client.renderer.TriceratopsRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class FossilCraftClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_TRICERATOPS_LAYER = new EntityModelLayer(Identifier.of(FossilCraft.MODID, "triceratops"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(FossilCraft.Triceratops, TriceratopsRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_TRICERATOPS_LAYER, TriceratopsModel::getTexturedModelData);
        HandledScreens.register(FossilCraft.CULTIVATOR_SCREEN_HANDLER, CultivatorScreen::new);
    }
}
