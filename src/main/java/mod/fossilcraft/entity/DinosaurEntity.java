package mod.fossilcraft.entity;

import mod.fossilcraft.FossilCraftMod;
import mod.fossilcraft.dinosaur.Dinosaur;
import mod.fossilcraft.registry.DinosaurRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.util.GeckoLibUtil;

public class DinosaurEntity extends MobEntity implements GeoEntity {

    private final String dinosaurId;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public DinosaurEntity(EntityType<? extends DinosaurEntity> type, World world) {
        super(type, world);

        this.dinosaurId = EntityType.getId(type).getPath();
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25);
    }

    public String getDinosaurId() {
        return dinosaurId;
    }

    public Dinosaur getDinosaur() {
        return DinosaurRegistry.get(Identifier.of(FossilCraftMod.MOD_ID, dinosaurId));
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}
