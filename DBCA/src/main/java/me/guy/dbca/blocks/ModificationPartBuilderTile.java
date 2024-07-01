package me.guy.dbca.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ModificationPartBuilderTile extends TileEntity implements IInventory {

    private final ItemStack[] inventory = new ItemStack[9];
    public ModificationPartBuilderTile(){

    }
    @Override
    public int getSizeInventory() {
        return 9;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return inventory[p_70301_1_];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int count) {
        if (inventory[slotIndex] != null) {
            ItemStack stack;
            if (inventory[slotIndex].stackSize <= count) {
                stack = inventory[slotIndex];
                inventory[slotIndex] = null;
                this.markDirty();
                return stack;
            } else {
                stack = inventory[slotIndex].splitStack(count);
                if (inventory[slotIndex].stackSize == 0) {
                    inventory[slotIndex] = null;
                }
                this.markDirty();
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (inventory[slot] != null) {
            ItemStack stack = inventory[slot];
            inventory[slot] = null;
            return stack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;

        markDirty();
    }

    @Override
    public String getInventoryName() {
        return "Modification Part Builder";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this &&
                player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }


    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    public ItemStack[] getInventory(){
        return inventory;
    }




}
