package com.jzells.corruptedbotany.registries.blocks.blockentity;

import com.jzells.corruptedbotany.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// what the shit how do i make block entities 😭 << WIP << need to do more research.

public class Tier1TableBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(27);
    private static final int OUTPUT_SLOT = 1;
    private static final int INPUT_SLOT = 0;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private int essenceBarValue = 0;
    private int maxEssenceBarValue = 10;

    public Tier1TableBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap== ForgeCapabilities.ITEM_HANDLER){
            return  lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        lazyItemHandler = LazyOptional.of(()->itemStackHandler);
        super.onLoad();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }

    private void essenceBarValueAdd(){
        essenceBarValue++;
    }
    private boolean hasEssence(){
        boolean hasEssenceInSlot = this.itemStackHandler.getStackInSlot(INPUT_SLOT).getItem() == Registries.T0ESSENCE.get();
        if (essenceBarValue < maxEssenceBarValue){
            return hasEssenceInSlot;
        } else {
            return false;
        }
    }
    private boolean isEssenceFull(){
        if(essenceBarValue == maxEssenceBarValue){
            return true;
        } else{
            return false;
        }
    }
    private void essenceBarValueRemove(){
        essenceBarValue--;
    }
    private void fillBar(){
        this.itemStackHandler.extractItem(INPUT_SLOT,1,false);
        if(!isEssenceFull()){
            essenceBarValueAdd();
        }
    }

}
