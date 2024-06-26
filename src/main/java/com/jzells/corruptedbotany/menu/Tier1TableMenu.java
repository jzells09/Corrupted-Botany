package com.jzells.corruptedbotany.menu;

import com.jzells.corruptedbotany.registries.MenuRegistries;
import com.jzells.corruptedbotany.registries.Registries;
import com.jzells.corruptedbotany.registries.blocks.blockentity.Tier1TableBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class Tier1TableMenu extends AbstractContainerMenu {
    private final Tier1TableBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;

    // Client Constructor
    public Tier1TableMenu(int containerID, Inventory playerInventory, FriendlyByteBuf data){
        this(containerID, playerInventory, playerInventory.player.level().getBlockEntity(data.readBlockPos()));
    }

    // Server Constructor
    public Tier1TableMenu(int containerID, Inventory playerInventory, BlockEntity blockEntity){
        super(MenuRegistries.TIER_1_TABLE_MENU.get(), containerID);
        if(blockEntity instanceof  Tier1TableBlockEntity tier1TableBlockEntity){
            this.blockEntity = tier1TableBlockEntity;
        } else {
            throw new IllegalStateException("Incorrect block entity class (%s) passed inti Tier1TableMenu!".formatted(blockEntity.getClass().getCanonicalName()));
        }

        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        createPlayerHotbar(playerInventory);
        createPlayerInventory(playerInventory);
        createBlockEntityInventory(tier1TableBlockEntity);
    }

    private void createBlockEntityInventory(Tier1TableBlockEntity blockEntity) {
        blockEntity.getOptional().ifPresent(inventory -> {
            for(int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    addSlot(new SlotItemHandler(inventory, col + (row * 5), 26 + (col * 18), 34 + (row * 18)));
                }
            }
            addSlot(new SlotItemHandler(inventory,26,144,70){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }
            });
            addSlot(new SlotItemHandler(inventory,25,26,141){
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return stack.is((Registries.T0ESSENCE.get()));
                }
            });
        });
    }

    private void createPlayerInventory(Inventory playerInventory) {
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 9; col++){
                addSlot(new Slot(playerInventory, 9+ col + (row * 9), 8 + (col*18), 169 + (row*18)));
            }
        }
    }

    private void createPlayerHotbar(Inventory playerInventory) {
        for(int col = 0; col < 9; col++){
            addSlot(new Slot(playerInventory, col, 8 + (col* 18), 227));
        }
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 27;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.levelAccess, player, Registries.TIER_1_TABLE.get());

    }

    public Tier1TableBlockEntity getBlockEntity(){
        return  this.blockEntity;
    }
}
