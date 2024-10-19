package mod.fossilcraft.block;

import mod.fossilcraft.FossilCraft;
import mod.fossilcraft.block.entity.CultivatorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FossilBlockEntityType {

    public static final BlockEntityType<CultivatorBlockEntity> CULTIVATOR = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(FossilCraft.MODID, "cultivator"),
            FabricBlockEntityTypeBuilder.create(CultivatorBlockEntity::new, FossilBlocks.CULTIVATOR).build());

    public static void initialize() {
    }
}
