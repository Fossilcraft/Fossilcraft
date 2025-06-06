package mod.fossilcraft.model;

import mod.fossilcraft.FossilCraftMod;
import mod.fossilcraft.entity.DinosaurEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class DinosaurModel extends GeoModel<DinosaurEntity> {

    private final String dinosaurId;

    public DinosaurModel(String dinosaurId) {
        super();
        this.dinosaurId = dinosaurId;
    }

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of(FossilCraftMod.MOD_ID, dinosaurId);
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.of(FossilCraftMod.MOD_ID, dinosaurId);
    }

    @Override
    public Identifier getAnimationResource(DinosaurEntity animatable) {
        return Identifier.of(FossilCraftMod.MOD_ID, dinosaurId);
    }

}
