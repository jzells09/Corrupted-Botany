package com.jzells.corruptedbotany.entities.client.renderers;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.entities.Tier3ZombieSpiderEntity;
import com.jzells.corruptedbotany.entities.client.ModelLayers;
import com.jzells.corruptedbotany.entities.client.model.Tier3ZombieSpiderModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Tier3ZombieSpiderRenderer extends MobRenderer<Tier3ZombieSpiderEntity, Tier3ZombieSpiderModel<Tier3ZombieSpiderEntity>>{
    public Tier3ZombieSpiderRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Tier3ZombieSpiderModel<>(pContext.bakeLayer(ModelLayers.TIER_3_ZOMBIESPIDER_H)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Tier3ZombieSpiderEntity tier3ZombieSpiderEntity) {
        return new ResourceLocation(CorruptedBotany.MODID,"textures/entities/tier_3_zombie_spider_hybrid.png");
    }

    @Override
    public void render(Tier3ZombieSpiderEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
