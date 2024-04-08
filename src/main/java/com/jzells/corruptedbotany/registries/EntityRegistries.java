package com.jzells.corruptedbotany.registries;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.entities.Tier1ZombieEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistries {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CorruptedBotany.MODID);

    public static final RegistryObject<EntityType<Tier1ZombieEntity>> TIER_1_ZOMBIE =
            ENTITIES.register("tier_1_zombie", ()-> EntityType.Builder.of(Tier1ZombieEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F).build("tier_1_zombie"));


    public static void register(IEventBus iEventBus){
        ENTITIES.register(iEventBus);
    }
}
