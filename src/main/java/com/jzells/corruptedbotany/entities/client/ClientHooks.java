package com.jzells.corruptedbotany.entities.client;

import com.jzells.corruptedbotany.entities.client.screens.Tier1TableBlockScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public class ClientHooks {
    public static void openTier1TableBlockScreen(BlockPos pos){
        Minecraft.getInstance().setScreen(new Tier1TableBlockScreen(pos));
    }
}
