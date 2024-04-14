package com.jzells.corruptedbotany.registries.blocks;

import com.jzells.corruptedbotany.registries.BlockEntityRegistries;
import com.jzells.corruptedbotany.registries.blocks.blockentity.Tier1TableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class Tier1TableBlock extends BaseEntityBlock {
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
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

        if(!(blockEntity instanceof  Tier1TableBlockEntity tier1TableBlockEntity)){
            return  InteractionResult.PASS;
        }
        if(pLevel.isClientSide()){
            return  InteractionResult.SUCCESS;
        }

        //open screen
        if(pPlayer instanceof ServerPlayer serverPlayer){
            NetworkHooks.openScreen(((ServerPlayer) pPlayer), (Tier1TableBlockEntity)blockEntity, pPos);
        }

        return InteractionResult.CONSUME;

    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if(!pLevel.isClientSide()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof Tier1TableBlockEntity tableBlockEntity){
                ItemStackHandler inv = ((Tier1TableBlockEntity) blockEntity).getInventory();
                for(int i = 0; i < inv.getSlots(); i++){
                    ItemStack stack = inv.getStackInSlot(i);
                    var entity = new ItemEntity(pLevel, pPos.getX()+0.5D, pPos.getY()+0.5D, pPos.getZ()+0.5D, stack);
                    pLevel.addFreshEntity(entity);
                }
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    //to do gfet recipe working


    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide) {return null;}
        return createTickerHelper(pBlockEntityType, BlockEntityRegistries.TIER_1_TABLE_ENTITY.get(),
                (pLevel1, pPos, pState1, pBlockEntity)-> pBlockEntity.tick(pLevel1,pPos,pState1));
        }
}

