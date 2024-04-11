package com.jzells.corruptedbotany.registries.blocks;

import com.jzells.corruptedbotany.registries.BlockEntityRegistries;
import com.jzells.corruptedbotany.registries.blocks.blockentity.Tier1TableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class Tier1TableBlock extends Block implements EntityBlock {
    public Tier1TableBlock(Properties pProperties) {
        super(pProperties);
    }
    private static final VoxelShape SHAPE = Shapes.join(Block.box(3, 9, 3, 13, 11, 13), Stream.of(
            Block.box(3, 0, 13, 13, 2, 15),
            Block.box(3, 0, 1, 13, 2, 3),
            Block.box(1, 0, 3, 3, 2, 13),
            Block.box(13, 0, 3, 15, 2, 13),
            Block.box(3, 10, 13, 13, 12, 15),
            Block.box(13, 10, 3, 15, 12, 13),
            Block.box(3, 10, 1, 13, 12, 3),
            Block.box(1, 10, 3, 3, 12, 13),
            Block.box(1, 0, 1, 3, 12, 3),
            Block.box(1, 0, 13, 3, 12, 15),
            Block.box(13, 0, 13, 15, 12, 15),
            Block.box(13, 0, 1, 15, 12, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("tooltip.corruptbotany.tier_1_table.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return BlockEntityRegistries.TIER_1_TABLE_ENTITY.get().create(blockPos,blockState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof Tier1TableBlockEntity tier1TableBlockEntity){
                pPlayer.sendSystemMessage(Component.literal("Block entity has been used"));
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}

