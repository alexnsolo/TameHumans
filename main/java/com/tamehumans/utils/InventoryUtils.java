package com.tamehumans.utils;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class InventoryUtils {

    public static void addToInventory(ItemStack itemStack, InventoryBasic inventory) {
        if (itemStack != null && itemStack.stackSize != 0 && itemStack.getItem() != null) {
            int emptySlot = getFirstEmptyStack(inventory);

            if (itemStack.isItemDamaged()) {
                if (emptySlot > -1) {
                    inventory.setInventorySlotContents(emptySlot, itemStack);
                }
            }
            else {
                int compatibleSlot = getExistingCompatibleStack(itemStack, inventory);
                if (compatibleSlot > -1) {
                    ItemStack existingStack = inventory.getStackInSlot(compatibleSlot);
                    existingStack.stackSize += itemStack.stackSize;
                }
                else if (emptySlot > -1) {
                    inventory.setInventorySlotContents(emptySlot, itemStack);
                }
            }
        }
    }

    public static boolean canFitItem(ItemStack itemStack, InventoryBasic inventory) {
        int emptyStack = getFirstEmptyStack(inventory);
        if (emptyStack > -1) {
            return true;
        }
        else {
            int compatibleStack = getExistingCompatibleStack(itemStack, inventory);
            if (compatibleStack > -1) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasRoomInInventory(InventoryBasic inventory) {
        return getFirstEmptyStack(inventory) > -1;
    }

    private static int getFirstEmptyStack(InventoryBasic inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); ++i) {
            if (inventory.getStackInSlot(i) == null) {
                return i;
            }
        }

        return -1;
    }

    private static int getExistingCompatibleStack(ItemStack itemStack, InventoryBasic inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); ++i) {
            ItemStack existingStack = inventory.getStackInSlot(i);
            if (existingStack != null
                    && existingStack.getItem() == itemStack.getItem()
                    && existingStack.isStackable()
                    && existingStack.stackSize + itemStack.stackSize <= existingStack.getMaxStackSize()
                    && existingStack.stackSize < inventory.getInventoryStackLimit()
                    && (!existingStack.getHasSubtypes() || existingStack.getItemDamage() == itemStack.getItemDamage())
                    && ItemStack.areItemStackTagsEqual(existingStack, itemStack)) {
                return i;
            }
        }
        return -1;
    }
}
