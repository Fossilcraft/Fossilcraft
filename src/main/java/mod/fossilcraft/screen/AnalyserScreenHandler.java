package mod.fossilcraft.screen;

import mod.fossilcraft.registry.ModScreenHandlers;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public class AnalyserScreenHandler extends ScreenHandler {
    public AnalyserScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ModScreenHandlers.ANALYSER, syncId);
    }
}
