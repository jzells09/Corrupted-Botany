package com.jzells.corruptedbotany.entities.client.screens;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.menu.Tier1TableMenu;
import com.jzells.corruptedbotany.registries.blocks.blockentity.Tier1TableBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class Tier1TableBlockScreen extends AbstractContainerScreen<Tier1TableMenu> {
    //private static final Component TITLE = Component.translatable("gui.corruptedbotany.tier_1_table_screen");

    private static final ResourceLocation TEXTURE = new ResourceLocation(CorruptedBotany.MODID, "textures/gui/tier_1_table_gui.png");

    private final int imageWidth, imageHeight;





    public Tier1TableBlockScreen(Tier1TableMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.imageHeight = 251;
        this.imageWidth = 176;

    }



    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        renderBackground(guiGraphics);
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
