package com.jzells.corruptedbotany.registries;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.menu.Tier1TableMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuRegistries {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, CorruptedBotany.MODID);

    public static final RegistryObject<MenuType<Tier1TableMenu>> TIER_1_TABLE_MENU = MENU_TYPES.register("tier_1_taable_menu",
            () -> IForgeMenuType.create(Tier1TableMenu::new));

    public static void register(IEventBus iEventBus){
        MENU_TYPES.register(iEventBus);
    }
}
