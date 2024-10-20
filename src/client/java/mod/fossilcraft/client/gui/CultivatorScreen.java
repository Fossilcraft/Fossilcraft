package mod.fossilcraft.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import mod.fossilcraft.FossilCraft;
import mod.fossilcraft.gui.CultivatorScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CultivatorScreen extends HandledScreen<CultivatorScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(FossilCraft.MODID, "textures/gui/cultivator.png");

    public CultivatorScreen(CultivatorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if (handler.isBurning()) {
            int burnProgress = handler.getFuelProgress();
            context.drawTexture(TEXTURE, x + 82, y + 36 + 12 - burnProgress, 176, 12 - burnProgress, 14, burnProgress + 2);
        }
        
        // Draw the cooking progress bar
        int cookProgress = handler.getCookProgress();
        context.drawTexture(TEXTURE, x + 79, y + 18, 176, 14, cookProgress + 1, 16);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
