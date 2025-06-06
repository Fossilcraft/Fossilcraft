package mod.fossilcraft.render;

import mod.fossilcraft.entity.DinosaurEntity;
import mod.fossilcraft.model.DinosaurModel;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class DinosaurRenderer<R extends LivingEntityRenderState & GeoRenderState>
        extends GeoEntityRenderer<DinosaurEntity, R> {

    public DinosaurRenderer(Context ctx, String dinosaurId) {
        super(ctx, new DinosaurModel(dinosaurId));
        this.shadowRadius = 0.8f;
    }

}
