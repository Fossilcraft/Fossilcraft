package mod.fossilcraft.client.renderer;

import mod.fossilcraft.FossilCraft;
import mod.fossilcraft.client.FossilCraftClient;
import mod.fossilcraft.client.model.TriceratopsModel;
import mod.fossilcraft.entity.TriceratopsEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TriceratopsRenderer extends MobEntityRenderer<TriceratopsEntity, TriceratopsModel> {

    public TriceratopsRenderer(EntityRendererFactory.Context context) {
        super(context, new TriceratopsModel(context.getPart(FossilCraftClient.MODEL_TRICERATOPS_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(TriceratopsEntity entity) {
        return Identifier.of(FossilCraft.MODID, "textures/dinosaur/triceratops/" + entity.getMorph().name().toLowerCase() + "_baby.png");
    }

    @Override
    protected void scale(TriceratopsEntity entity, MatrixStack matrixStack, float partialTickTime) {
        float scale = entity.getCurrentSize();
        matrixStack.scale(scale, scale, scale);
        matrixStack.translate(0f, 1f, 0f);
    }
}
