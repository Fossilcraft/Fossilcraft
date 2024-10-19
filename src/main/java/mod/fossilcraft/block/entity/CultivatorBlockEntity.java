package mod.fossilcraft.block.entity;

import mod.fossilcraft.block.FossilBlockEntityType;
import mod.fossilcraft.gui.CultivatorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class CultivatorBlockEntity extends AbstractFurnaceBlockEntity {
    public CultivatorBlockEntity(BlockPos pos, BlockState state) {
        super(FossilBlockEntityType.CULTIVATOR, pos, state, RecipeType.SMELTING);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("block.fossilcraft.cultivator");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new CultivatorScreenHandler(syncId, playerInventory);
    }
}
