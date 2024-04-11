package com.jzells.corruptedbotany.registries;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.registries.blocks.blockentity.Tier1TableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistries {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CorruptedBotany.MODID);

    public static final RegistryObject<BlockEntityType<Tier1TableBlockEntity>> TIER_1_TABLE_ENTITY = BLOCK_ENTITIES.register("tier_1_table_entity",
            () -> BlockEntityType.Builder.of(Tier1TableBlockEntity::new, Registries.TIER_1_TABLE.get()).build(null));

    public static void register(IEventBus iEventBus){
        BLOCK_ENTITIES.register(iEventBus);
    }
}
