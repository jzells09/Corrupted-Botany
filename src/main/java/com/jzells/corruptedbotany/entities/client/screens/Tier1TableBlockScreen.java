package com.jzells.corruptedbotany.entities.client.screens;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.registries.blocks.blockentity.Tier1TableBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class Tier1TableBlockScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui.corruptedbotany.tier_1_table_screen");

    private static final ResourceLocation TEXTURE = new ResourceLocation(CorruptedBotany.MODID, "textures/gui/tier_1_table_gui.png");

    private final BlockPos pos;
    private final int imageWidth, imageHeight;

    private Tier1TableBlockEntity blockEntity;
    private int leftPos, topPos;


    public Tier1TableBlockScreen(BlockPos position) {
        super(TITLE);
        this.imageHeight = 251;
        this.imageWidth = 176;
        this.pos = position;

    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        //this is a safeguard
        if(this.minecraft == null) return;

        Level level = this.minecraft.level;
        //another safeguard (just safe to always have even though for a blockentity it should be fine.)
        if(level == null) return;

        BlockEntity bEntity = level.getBlockEntity(this.pos);
        if(bEntity instanceof Tier1TableBlockEntity tier1TableBlockEntity){
            this.blockEntity = tier1TableBlockEntity;
        } else{
            System.err.printf("BlockEntity at %s is not of type Tier1TableBlockEntity!\n", this.pos);
            return;
        }
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(graphics);
        graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        graphics.drawString(this.font, TITLE, this.leftPos+8, this.topPos+8, 0x404040, false);
        super.render(graphics, pMouseX, pMouseY, pPartialTick);

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
