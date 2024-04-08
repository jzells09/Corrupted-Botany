package com.jzells.corruptedbotany.entities.client;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.entities.Tier1ZombieEntity;
import com.jzells.corruptedbotany.entities.Tier2ZombieEntity;
import com.jzells.corruptedbotany.entities.client.model.Tier1ZombieModel;
import com.jzells.corruptedbotany.entities.client.model.Tier2ZombieModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Tier2ZombieRenderer extends MobRenderer<Tier2ZombieEntity, Tier2ZombieModel<Tier2ZombieEntity>> {
    public Tier2ZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Tier2ZombieModel<>(pContext.bakeLayer(ModelLayers.TIER_2_ZOMBIE)), .5f);
    }


    @Override
    public ResourceLocation getTextureLocation(Tier2ZombieEntity tier1ZombieEntity) {
        return new ResourceLocation(CorruptedBotany.MODID,"textures/entities/tier_2_zombie.png");
    }

    @Override
    public void render(Tier2ZombieEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
