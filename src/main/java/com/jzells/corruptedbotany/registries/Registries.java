package com.jzells.corruptedbotany.registries;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.registries.blocks.Tier1TableBlock;
import com.jzells.corruptedbotany.registries.crops.T1ZombieCrop;
import com.jzells.corruptedbotany.registries.crops.T2ZombieCrop;
import com.jzells.corruptedbotany.registries.items.BoneAshItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
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

    public static final RegistryObject<Item> T0ESSENCE = ITEMS.register("tier_0_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> T1ESSENCE = ITEMS.register("tier_1_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> T2ESSENCE = ITEMS.register("tier_2_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> T3ESSENCE = ITEMS.register("tier_3_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> T4ESSENCE = ITEMS.register("tier_4_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> T5ESSENCE = ITEMS.register("tier_5_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TE6SSENCE = ITEMS.register("tier_6_essence",
            () -> new Item(new Item.Properties()));
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            // forge registries, NOT my registries. use different name for class next time!!! OOPS
            DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, CorruptedBotany.MODID);



    public static final RegistryObject<Item> BONE_DUST = ITEMS.register("bone_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BONE_ASH = ITEMS.register("bone_ash",
            () -> new BoneAshItem(new Item.Properties()));

    public static final RegistryObject<Item> ZOMBIE_DNA = ITEMS.register("zombie_dna",
            () -> new BoneAshItem(new Item.Properties()));
    public static final RegistryObject<Item> SKELETON_DNA = ITEMS.register("skeleton_dna",
            () -> new BoneAshItem(new Item.Properties()));
    public static final RegistryObject<Item> SPIDER_DNA = ITEMS.register("spider_dna",
            () -> new BoneAshItem(new Item.Properties()));
    public static final RegistryObject<Item> CREEPER_DNA = ITEMS.register("creeper_dna",
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

    public static final RegistryObject<Tier1TableBlock> TIER_1_TABLE = registerBlock("tier_1_table",
            ()-> new Tier1TableBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.ANVIL)));


    //CREATIVE MODE TABS

    public static final RegistryObject<CreativeModeTab> CB_TAB = CREATIVE_MODE_TAB.register("corruptedbotany_tab",
            ()-> CreativeModeTab.builder().icon(() -> new ItemStack(Registries.T2ESSENCE.get()))
                    .title(Component.translatable("creativetab.corruptedbotany_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(Registries.T0ESSENCE.get());
                        output.accept(Registries.T1ESSENCE.get());
                        output.accept(Registries.T2ESSENCE.get());
                        output.accept(Registries.T3ESSENCE.get());
                        output.accept(Registries.T4ESSENCE.get());
                        output.accept(Registries.T5ESSENCE.get());
                        output.accept(Registries.TE6SSENCE.get());

                        output.accept(Registries.CREEPER_DNA.get());
                        output.accept(Registries.SKELETON_DNA.get());
                        output.accept(Registries.ZOMBIE_DNA.get());
                        output.accept(Registries.SPIDER_DNA.get());

                        output.accept(Registries.T1ZSEED.get());
                        output.accept(Registries.T2ZSEED.get());

                        output.accept(Registries.BONE_DUST.get());
                        output.accept(Registries.BONE_ASH.get());

                        output.accept(Registries.TIER_1_TABLE.get());


                    }).build());

    public static void register(IEventBus iEventBus){
        ITEMS.register(iEventBus);
        BLOCKS.register(iEventBus);
        CREATIVE_MODE_TAB.register(iEventBus);
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
