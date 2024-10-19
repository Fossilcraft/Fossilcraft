package mod.fossilcraft.block;

import com.mojang.serialization.MapCodec;
import mod.fossilcraft.block.entity.CultivatorBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CultivatorBlock extends AbstractFurnaceBlock {
    public static final MapCodec<CultivatorBlock> CODEC = createCodec(CultivatorBlock::new);

    public CultivatorBlock() {
        super(AbstractBlock.Settings.copy(Blocks.GLASS));
    }

    public CultivatorBlock(AbstractBlock.Settings settings) {
        super(AbstractBlock.Settings.copy(Blocks.GLASS));
    }


    public MapCodec<CultivatorBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CultivatorBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory) blockEntity);
        }

    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CultivatorBlockEntity(pos, state);
    }
}
