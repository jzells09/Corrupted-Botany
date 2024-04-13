package com.jzells.corruptedbotany.events;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.entities.client.ModelLayers;
import com.jzells.corruptedbotany.entities.client.model.Tier1ZombieModel;
import com.jzells.corruptedbotany.entities.client.model.Tier3ZombieSpiderModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CorruptedBotany.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModelLayers.TIER_1_ZOMBIE, Tier1ZombieModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.TIER_2_ZOMBIE, Tier1ZombieModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.TIER_3_ZOMBIESPIDER_H, Tier3ZombieSpiderModel::createBodyLayer);
    }
}
