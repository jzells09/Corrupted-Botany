package com.jzells.corruptedbotany.entities.client;

import com.jzells.corruptedbotany.CorruptedBotany;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModelLayers {
    public static final ModelLayerLocation TIER_1_ZOMBIE = new ModelLayerLocation(
            new ResourceLocation(CorruptedBotany.MODID, "tier_1_zombie_layer"),"main");
    public static final ModelLayerLocation TIER_2_ZOMBIE = new ModelLayerLocation(
            new ResourceLocation(CorruptedBotany.MODID, "tier_2_zombie_layer"),"main");

    public static final ModelLayerLocation TIER_3_ZOMBIESPIDER_H = new ModelLayerLocation(
            new ResourceLocation(CorruptedBotany.MODID, "tier_3_zombie_spider_hybrid_layer"),"main");


}
