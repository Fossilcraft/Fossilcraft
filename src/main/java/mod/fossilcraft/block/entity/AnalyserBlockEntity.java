package mod.fossilcraft.block.entity;

import mod.fossilcraft.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class AnalyserBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory< {

    public AnalyserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ANALYSER, pos, state);
    }

}
