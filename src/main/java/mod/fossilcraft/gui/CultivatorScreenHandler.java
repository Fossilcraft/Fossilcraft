package mod.fossilcraft.gui;

import mod.fossilcraft.FossilCraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CultivatorScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public CultivatorScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3));
    }

    public CultivatorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(FossilCraft.CULTIVATOR_SCREEN_HANDLER, syncId);

        this.inventory = inventory;

        // Input
        this.addSlot(new Slot(inventory, 0, 49, 20));

        // Fuel
        this.addSlot(new Slot(inventory, 1, 81, 54));

        // Output
        this.addSlot(new Slot(inventory, 2, 116, 21));

        int slotCount = 3;

        // Player Inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                slotCount++;
            }
        }

        // Hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
