package com.jzells.corruptedbotany.events;


import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.entities.Tier1ZombieEntity;
import com.jzells.corruptedbotany.entities.Tier2ZombieEntity;
import com.jzells.corruptedbotany.entities.Tier3ZombieSpiderEntity;
import com.jzells.corruptedbotany.registries.EntityRegistries;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CorruptedBotany.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(EntityRegistries.TIER_1_ZOMBIE.get(), Tier1ZombieEntity.createAttributes().build());
        event.put(EntityRegistries.TIER_2_ZOMBIE.get(), Tier2ZombieEntity.createAttributes().build());
        event.put(EntityRegistries.TIER_3_ZOMBIESPIDER_H.get(), Tier3ZombieSpiderEntity.createAttributes().build());
    }

}
