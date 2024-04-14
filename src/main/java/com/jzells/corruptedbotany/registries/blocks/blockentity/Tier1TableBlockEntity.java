package com.jzells.corruptedbotany.registries.blocks.blockentity;

import com.jzells.corruptedbotany.CorruptedBotany;
import com.jzells.corruptedbotany.menu.Tier1TableMenu;
import com.jzells.corruptedbotany.recipe.custom.Tier1TableRecipie;
import com.jzells.corruptedbotany.registries.BlockEntityRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Tier1TableBlockEntity extends BlockEntity implements MenuProvider {
    public Tier1TableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistries.TIER_1_TABLE_ENTITY.get(), pPos, pBlockState);

    }
    private static final int OUTPUT_SLOT = 26;

    private static final Component TITLE = Component.translatable("container.corruptedbotany.tier_1_table_block");



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

    public LazyOptional<ItemStackHandler> getOptional(){
        return  this.optional;
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

    @Override
    public @NotNull Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerID, @NotNull Inventory inventory, Player player) {
        return new Tier1TableMenu(containerID, inventory, this);
    }


    public void craftItem(){
        Optional<Tier1TableRecipie> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        for(int i = 0; i < 25; i++){
            this.inventory.extractItem(i,1,false);
        }
        this.inventory.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.inventory.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    public boolean hasRecipe(){
        Optional<Tier1TableRecipie> recipe = getCurrentRecipe();

        if(recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.inventory.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.inventory.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.inventory.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.inventory.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private Optional<Tier1TableRecipie> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(25); //this.inventory.getSlots()
        for(int i = 0; i < 25; i++ ){
            inventory.setItem(i, this.inventory.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(Tier1TableRecipie.Type.INSTANCE, inventory, level);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()){
            setChanged(pLevel,pPos,pState);

            craftItem();
        }

    }
}
