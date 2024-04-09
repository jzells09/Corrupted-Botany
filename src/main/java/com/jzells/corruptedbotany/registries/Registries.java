package com.jzells.corruptedbotany.registries;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.registries.blocks.Tier1Table;
import com.jzells.corruptedbotany.registries.crops.T1ZombieCrop;
import com.jzells.corruptedbotany.registries.crops.T2ZombieCrop;
import com.jzells.corruptedbotany.registries.items.BoneAshItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Registries {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CorruptedBotany.MODID);
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CorruptedBotany.MODID);

    //ITEMS


    public static final RegistryObject<Item> T1ESSENCE = ITEMS.register("tier_1_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> T2ESSENCE = ITEMS.register("tier_2_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> T0ESSENCE = ITEMS.register("tier_0_essence",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> BONE_DUST = ITEMS.register("bone_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BONE_ASH = ITEMS.register("bone_ash",
            () -> new BoneAshItem(new Item.Properties()));

    //SEEDS


    public static final RegistryObject<Item> T1ZSEED = ITEMS.register("tier_1_zombie_seed",
            () -> new ItemNameBlockItem(Registries.T1ZOMBIE_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> T2ZSEED = ITEMS.register("tier_2_zombie_seed",
            () -> new ItemNameBlockItem(Registries.T2ZOMBIE_CROP.get(), new Item.Properties()));


    //BLOCKS

    public static final RegistryObject<Block> T1ZOMBIE_CROP = BLOCKS.register("tier_1_zombie_crop",
            ()->new T1ZombieCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).sound(SoundType.CHAIN).noOcclusion().noCollission()));
    public static final RegistryObject<Block> T2ZOMBIE_CROP = BLOCKS.register("tier_2_zombie_crop",
            ()->new T2ZombieCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).sound(SoundType.CHAIN).noOcclusion().noCollission()));

    public static final RegistryObject<Tier1Table> TIER_1_TABLE = registerBlock("tier_1_table",
            ()-> new Tier1Table(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.ANVIL)));


    public static void register(IEventBus iEventBus){
        ITEMS.register(iEventBus);
        BLOCKS.register(iEventBus);
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return Registries.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
