package me.guy.dbca.blocks;

import me.guy.dbca.DBCA;
import me.guy.dbca.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.WorldInfo;

import javax.swing.*;
import java.util.Objects;

public class AndroidTableTile extends TileEntity implements IInventory {
    private ItemStack[] inventory = new ItemStack[6];

    public AndroidTableTile(){

        //this.customInv = ExtendedPlayer.get(player).customInv;

    }


    @Override
    public int getSizeInventory() {
        return 6;
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
        return "Android Table";
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


    @Override
    public void readFromNBT(NBTTagCompound compound) {


        NBTTagList itemList = compound.getTagList("DBCAINV" + worldObj.getWorldInfo().getWorldName(), 10);
        ItemStack[] grinderItemStackArray = new ItemStack[getSizeInventory()];

        for (int i = 0; i < itemList.tagCount(); ++i) {
            NBTTagCompound itemTag = itemList.getCompoundTagAt(i);
            byte slot = itemTag.getByte("Slot" + worldObj.getWorldInfo().getWorldName());

            if (slot >= 0 && slot < getSizeInventory()) {
                grinderItemStackArray[slot] = ItemStack.loadItemStackFromNBT(itemTag);
            }
        }

        inventory = grinderItemStackArray;
    }



    @Override
    public void writeToNBT(NBTTagCompound compound) {


        NBTTagList itemList = new NBTTagList();
        ItemStack[] grinderItemStackArray = inventory;

        for (int i = 0; i < getSizeInventory(); ++i) {
            ItemStack stack = grinderItemStackArray[i];
                if (stack != null){
                    NBTTagCompound itemTag = new NBTTagCompound();
                    itemTag.setByte("Slot" + worldObj.getWorldInfo().getWorldName(), (byte)i);
                    stack.writeToNBT(itemTag);
                    itemList.appendTag(itemTag);
                }

        }

        compound.setTag("DBCAINV" + worldObj.getWorldInfo().getWorldName(), itemList);
    }

}
