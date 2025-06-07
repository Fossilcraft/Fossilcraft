package mod.fossilcraft.registry;

import mod.fossilcraft.FossilCraftMod;
import mod.fossilcraft.block.entity.AnalyserBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModBlockEntities {

    public static final BlockEntityType<AnalyserBlockEntity> ANALYSER = register(
            "analyser",
            FabricBlockEntityTypeBuilder.create(AnalyserBlockEntity::new, ModBlocks.ANALYSER).build());

    private static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(FossilCraftMod.MOD_ID, path),
                blockEntityType);
    }

    public static void init() {
        // This method is intentionally left empty.
    }
}
