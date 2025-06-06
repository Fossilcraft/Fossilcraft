package mod.fossilcraft.registry;

import java.util.HashMap;
import java.util.Map;

import mod.fossilcraft.FossilCraftMod;
import mod.fossilcraft.dinosaur.Dinosaur;
import mod.fossilcraft.entity.DinosaurEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public final class ModEntities {
    public static final Map<String, EntityType<DinosaurEntity>> TYPES = new HashMap<>();

    public static void registerDinosaurEntities() {
        for (Dinosaur d : DinosaurRegistry.all().values()) {
            RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                    Identifier.of(FossilCraftMod.MOD_ID, d.id));

            EntityType<DinosaurEntity> type = EntityType.Builder
                    .create(DinosaurEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.75f, 0.75f)
                    .build(key);

            Registry.register(Registries.ENTITY_TYPE, key, type);
            FabricDefaultAttributeRegistry.register(type, DinosaurEntity.createMobAttributes());

            TYPES.put(d.id, type);
        }
    }

}
