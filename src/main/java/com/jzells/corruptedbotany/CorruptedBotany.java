package com.jzells.corruptedbotany;

import com.jzells.corruptedbotany.entities.client.Tier1ZombieRenderer;
import com.jzells.corruptedbotany.entities.client.Tier2ZombieRenderer;
import com.jzells.corruptedbotany.registries.BlockEntityRegistries;
import com.jzells.corruptedbotany.registries.EntityRegistries;
import com.jzells.corruptedbotany.registries.Registries;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CorruptedBotany.MODID)
public class CorruptedBotany
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "corruptedbotany";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public CorruptedBotany()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Registries.register(modEventBus);
        EntityRegistries.register(modEventBus);
        BlockEntityRegistries.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

        ItemBlockRenderTypes.setRenderLayer(Registries.T1ZOMBIE_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Registries.T2ZOMBIE_CROP.get(), RenderType.cutout());

        // Some common setup code
        LOGGER.info("Hello from Corrupted Botany!");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    { // tdl -> make creative tab lol

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(EntityRegistries.TIER_1_ZOMBIE.get(), Tier1ZombieRenderer::new);
            EntityRenderers.register(EntityRegistries.TIER_2_ZOMBIE.get(), Tier2ZombieRenderer::new);
        }
    }
}
