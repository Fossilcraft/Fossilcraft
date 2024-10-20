package mod.fossilcraft.gui;

import mod.fossilcraft.FossilCraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CultivatorScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;


    public CultivatorScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(4));
    }

    public CultivatorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(FossilCraft.CULTIVATOR_SCREEN_HANDLER, syncId);

        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;

        // Input
        this.addSlot(new Slot(inventory, 0, 49, 20));

        // Fuel
        this.addSlot(new Slot(inventory, 1, 81, 54));

        // Output
        this.addSlot(new Slot(inventory, 2, 116, 21) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });


        // Player Inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        this.addProperties(propertyDelegate);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public int getCookProgress() {
        int cookTime = this.propertyDelegate.get(2);
        int cookTimeTotal = this.propertyDelegate.get(3);
        return cookTimeTotal != 0 && cookTime != 0 ? cookTime * 24 / cookTimeTotal : 0;
    }

    public int getFuelProgress() {
        int fuelTime = this.propertyDelegate.get(0);
        int fuelTimeTotal = this.propertyDelegate.get(1);
        return fuelTimeTotal != 0 ? fuelTime * 13 / fuelTimeTotal : 0;
    }

    public boolean isBurning() {
        return this.propertyDelegate.get(0) > 0;
    }

}
