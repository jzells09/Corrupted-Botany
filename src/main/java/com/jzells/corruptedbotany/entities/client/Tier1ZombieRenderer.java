package com.jzells.corruptedbotany.entities.client;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.entities.Tier1ZombieEntity;
import com.jzells.corruptedbotany.entities.client.model.Tier1ZombieModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class Tier1ZombieRenderer extends MobRenderer<Tier1ZombieEntity, Tier1ZombieModel<Tier1ZombieEntity>> {
    public Tier1ZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Tier1ZombieModel<>(pContext.bakeLayer(ModelLayers.TIER_1_ZOMBIE)), .5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Tier1ZombieEntity tier1ZombieEntity) {
        return new ResourceLocation(CorruptedBotany.MODID,"textures/entities/tier_1_zombie.png");
    }

    @Override
    public void render(Tier1ZombieEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
