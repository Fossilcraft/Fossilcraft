package mod.fossilcraft.registry;

import mod.fossilcraft.FossilCraftMod;
import mod.fossilcraft.screen.AnalyserScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public final class ModScreenHandlers {

    private static ScreenHandlerType<AnalyserScreenHandler> ANALYSER = Registry.register(Registries.SCREEN_HANDLER,
            Identifier.of(FossilCraftMod.MOD_ID),
            new ScreenHandlerType<>(AnalyserScreenHandler::new));

    public static void init() {
        // This method is intentionally left empty.
    }
}
