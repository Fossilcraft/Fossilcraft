package mod.fossilcraft.entity;

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

    public float getMinSize() {
        return 1.2f;
    }

    public float getMaxSize() {
        return 7.0f;
    }

    @Override
    public Morph[] getMorphs() {
        return TriceratopsMorph.values();
    }


    @Override
    public Diet getDietType() {
        return Diet.HERBIVORE;
    }
}
