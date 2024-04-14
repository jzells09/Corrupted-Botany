package com.jzells.corruptedbotany.recipe;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.recipe.custom.Tier1TableRecipie;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegistries {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, CorruptedBotany.MODID);

    public static final RegistryObject<RecipeSerializer<Tier1TableRecipie>> TIER_1_TABLE_SERIALIZER =
            SERIALIZERS.register("tier_1_table", ()-> Tier1TableRecipie.Serializer.INSTANCE);


    public static void register(IEventBus iEventBus){
        SERIALIZERS.register(iEventBus);
    }
}
