package mod.fossilcraft.entity;

import mod.fossilcraft.entity.shared.Age;
import mod.fossilcraft.entity.shared.Diet;
import mod.fossilcraft.entity.shared.DinosaurProperties;
import mod.fossilcraft.entity.shared.Morph;
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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public abstract class DinosaurEntity extends AnimalEntity {

    public static final int TICKS_PER_DAY = 24000;

    private static final TrackedData<Integer> MORPH = DataTracker.registerData(DinosaurEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> TICKS_SINCE_LAST_AGE_UPDATE = DataTracker.registerData(DinosaurEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> AGE = DataTracker.registerData(DinosaurEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private final DinosaurProperties properties;

    protected DinosaurEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.properties = createProperties();
    }


    @Override
    public void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(MORPH, 0);
        builder.add(TICKS_SINCE_LAST_AGE_UPDATE, 0);
        builder.add(AGE, 0);
    }

    protected abstract DinosaurProperties createProperties();

    public float getCurrentSize() {
        if (this.properties == null) {
            return 1.0f; // Default size if properties are not initialized
        }

        float scaledSize = (float) this.dataTracker.get(AGE) / this.properties.adultAge;
        return Math.min(this.properties.minSize + (this.properties.maxSize - this.properties.minSize) * scaledSize, this.properties.maxSize);
    }

    public Age getAge() {
        int age = this.dataTracker.get(AGE);
        if (age < this.properties.babyAge) {
            return Age.BABY;
        } else if (age < this.properties.adultAge) {
            return Age.JUVENILE;
        } else {
            return Age.ADULT;
        }
    }

    public Morph getMorph() {
        return this.properties.morphs[this.dataTracker.get(MORPH)];
    }

    public void setMorph(Morph morph) {
        this.dataTracker.set(MORPH, morph.ordinal());
    }

    public void setRandomMorph(Random random) {
        setMorph(this.properties.morphs[random.nextInt(this.properties.morphs.length)]);
    }

    public boolean isCarnivore() {
        return this.properties.diet == Diet.CARNIVORE;
    }

    public boolean isHerbivore() {
        return this.properties.diet == Diet.HERBIVORE;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("morph", this.dataTracker.get(MORPH));
        nbt.putInt("age", this.dataTracker.get(AGE));
        nbt.putInt("ticksSinceLastAgeUpdate", this.dataTracker.get(TICKS_SINCE_LAST_AGE_UPDATE));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(MORPH, nbt.getInt("morph"));
        this.dataTracker.set(AGE, nbt.getInt("age"));
        this.dataTracker.set(TICKS_SINCE_LAST_AGE_UPDATE, nbt.getInt("ticksSinceLastAgeUpdate"));
    }

    @Override
    public void tick() {
        super.tick();

        int ticksSinceLastAgeUpdate = this.dataTracker.get(TICKS_SINCE_LAST_AGE_UPDATE);

        this.dataTracker.set(TICKS_SINCE_LAST_AGE_UPDATE, ticksSinceLastAgeUpdate + 1);

        if (!this.getWorld().isClient) {
            int age = this.dataTracker.get(AGE);
            if (ticksSinceLastAgeUpdate > TICKS_PER_DAY) {
                this.dataTracker.set(AGE, age + 1);
                this.dataTracker.set(TICKS_SINCE_LAST_AGE_UPDATE, 0);
            }
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        if (item == FossilItems.DINOPEDIA) {
            if (!player.getWorld().isClient) {
                int age = this.dataTracker.get(AGE);

                player.sendMessage(
                        Text.literal(
                                String.format("This %s is %s days old",
                                        this.getName().getString(),
                                        age
                                )
                        )
                );
            }

            return ActionResult.success(player.getWorld().isClient);
        }

        if (item == FossilItems.CHICKEN_ESSENCE) {
            if (!player.getWorld().isClient) {
                int age = this.dataTracker.get(AGE);
                this.dataTracker.set(AGE, age + 1);
                itemStack.decrement(1);
                this.updateHitbox();
            }

            return ActionResult.success(player.getWorld().isClient);
        }

        return super.interactMob(player, hand);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData entityData) {
        EntityData data = super.initialize(world, difficulty, reason, entityData);
        this.setRandomMorph(world.getRandom());
        this.setupGoals();
        return data;
    }

    protected void setupGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new LookAroundGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));

        this.goalSelector.add(2, new WanderAroundFarGoal(this, 0.8D));

        if (this.isHerbivore()) {
            this.goalSelector.add(3, new EatGrassGoal(this));
        }

    }

    @Override
    protected Box calculateBoundingBox() {
        float size = getCurrentSize();
        float width = 0.75f * size;
        float height = 0.75f * size;

        return new Box(
                this.getX() - width / 2.0,
                this.getY(),
                this.getZ() - width / 2.0,
                this.getX() + width / 2.0,
                this.getY() + height,
                this.getZ() + width / 2.0
        );
    }

    public void updateHitbox() {
        this.calculateBoundingBox();
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
