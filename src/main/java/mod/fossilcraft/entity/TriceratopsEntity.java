package mod.fossilcraft.entity;

import mod.fossilcraft.entity.shared.Diet;
import mod.fossilcraft.entity.shared.DinosaurProperties;
import mod.fossilcraft.entity.shared.Morph;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;

public class TriceratopsEntity extends DinosaurEntity {

    public enum TriceratopsMorph implements Morph {
        GREEN,
        BROWN,
        GREY
    }

    public TriceratopsEntity(EntityType<? extends TriceratopsEntity> entityType, World world) {
        super(entityType, world);

        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25D);
    }


    @Override
    protected DinosaurProperties createProperties() {
        return new DinosaurProperties.Builder()
                .diet(Diet.HERBIVORE)
                .size(1.2f, 6.0f)
                .morphs(TriceratopsMorph.values())
                .ageStages(3, 10)
                .build();
    }

}
