package com.jzells.corruptedbotany.registries.blocks.blockentity;

import com.jzells.corruptedbotany.registries.BlockEntityRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Tier1TableBlockEntity extends BlockEntity {
    public Tier1TableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistries.TIER_1_TABLE_ENTITY.get(), pPos, pBlockState);
    }
}
