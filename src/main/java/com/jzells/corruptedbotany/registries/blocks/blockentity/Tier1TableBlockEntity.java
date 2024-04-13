package com.jzells.corruptedbotany.registries.blocks.blockentity;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.registries.BlockEntityRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class Tier1TableBlockEntity extends BlockEntity {
    public Tier1TableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistries.TIER_1_TABLE_ENTITY.get(), pPos, pBlockState);
    }
    private final ItemStackHandler inventory = new ItemStackHandler(27){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            Tier1TableBlockEntity.this.setChanged();
        }
    };



    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        CompoundTag cbModData = nbt.getCompound(CorruptedBotany.MODID);
        this.inventory.deserializeNBT(cbModData.getCompound("Inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        var cbModData = new CompoundTag();
        cbModData.put("Inventory", this.inventory.serializeNBT());

        nbt.put(CorruptedBotany.MODID, cbModData);
    }

    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return  this.optional.cast();
        }
        return super.getCapability(cap);
    }



    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.optional.invalidate();
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return  nbt;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    //NOTE:: You could return an optional and use the .orElse method if your inventory might be invalid at a given time.

    // helper methods
    public ItemStackHandler getInventory(){
        return this.inventory;
    }

    public ItemStack getStackInSlot(int slot){
        return this.inventory.getStackInSlot(slot);
    }
}
