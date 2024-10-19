package mod.fossilcraft.entity;

import mod.fossilcraft.item.FossilItems;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public abstract class DinosaurEntity extends AnimalEntity {

    public enum Diet {
        CARNIVORE,
        HERBIVORE
    }

    public interface Morph {
        String name();

        int ordinal();
    }

    private static final TrackedData<Integer> MORPH = DataTracker.registerData(DinosaurEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Float> GROWTH = DataTracker.registerData(DinosaurEntity.class, TrackedDataHandlerRegistry.FLOAT);

    protected DinosaurEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(MORPH, 0);
        builder.add(GROWTH, 0f);
    }

    public abstract Morph[] getMorphs();

    public abstract Diet getDietType();

    public abstract float getMinSize();

    public abstract float getMaxSize();

    public float getCurrentSize() {
//        float growth = this.dataTracker.get(GROWTH);
        float growth = 0.8f;
        return getMinSize() + (getMaxSize() - getMinSize()) * growth;
    }

    public Morph getMorph() {
        return getMorphs()[this.dataTracker.get(MORPH)];
    }

    public void setMorph(Morph morph) {
        this.dataTracker.set(MORPH, morph.ordinal());
    }

    public void setRandomMorph(Random random) {
        Morph[] morphs = getMorphs();
        setMorph(morphs[random.nextInt(morphs.length)]);
    }

    public boolean isCarnivore() {
        return getDietType() == Diet.HERBIVORE;
    }

    public boolean isHerbivore() {
        return getDietType() == Diet.CARNIVORE;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        if (item == FossilItems.DINOPEDIA) {
            if (!player.getWorld().isClient) {
                player.sendMessage(
                        Text.literal(
                                String.format("This %s is %s day old",
                                        this.getName().getString(),
                                        this.age
                                )
                        )
                );
            }

            return ActionResult.success(player.getWorld().isClient);
        }

        return super.interactMob(player, hand);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData entityData) {
        EntityData data = super.initialize(world, difficulty, reason, entityData);
        this.setRandomMorph(world.getRandom());
        return data;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));

        this.goalSelector.add(1, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(2, new LookAroundGoal(this));

        if (this.isHerbivore()) {
            this.goalSelector.add(3, new EatGrassGoal(this));
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack item) {
        return false;
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public static DefaultAttributeContainer.Builder createDinosaurAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D);
    }
}
