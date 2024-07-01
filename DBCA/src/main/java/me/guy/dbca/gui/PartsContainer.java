package me.guy.dbca.gui;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import me.guy.dbca.DBCA;

import me.guy.dbca.blocks.ModificationPartBuilderTile;

import me.guy.dbca.items.Tiers;
import me.guy.dbca.network.PacketHandler;
import me.guy.dbca.network.PacketUpdateNBT;

import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;

import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;

import net.minecraftforge.common.util.Constants;


import java.util.*;

public class PartsContainer extends Container {

    private final ModificationPartBuilderTile modificationPartBuilderTile;

    private final EntityPlayer player;

    private final Item[] allowedItemsCore = {Items.milk_bucket, ItemsDBC.ItemMedMoss, Item.getItemFromBlock(Blocks.ice), ItemsDBC.ItemNamekDragonBlock};
    private final Item[] allowedItemsLimb = {ItemsDBC.ItemPP};


    public PartsContainer (ModificationPartBuilderTile tile, EntityPlayer Player){

        this.modificationPartBuilderTile = tile;
        this.player = Player;

        this.addSlotToContainer(new Slot(tile, 0, 26, 23) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }

                }
                if (p_75214_1_ != null && !Unallowed(p_75214_1_.getItem())){
                    return false;
                }
                return false;

            }

            @Override
            public int getSlotStackLimit() {
                return 1;
            }
            @Override
            public ItemStack decrStackSize(int amount) {
                if (getStack() != null) {
                    // Create a new stack with a quantity of 1
                    ItemStack singleStack = getStack().copy();
                    singleStack.stackSize = 1;
                    // Remove one item from the slot
                    super.decrStackSize(1);
                    // Notify listeners of the slot change
                    onSlotChanged();
                    // Return the single stack
                    return singleStack;
                }
                return null;
            }
        });

        this.addSlotToContainer(new Slot(tile, 1, 68, 23) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null && !Unallowed(p_75214_1_.getItem())){
                    return false;
                }
                return false;

            }
            @Override
            public int getSlotStackLimit() {
                return 1;
            }
            @Override
            public ItemStack decrStackSize(int amount) {
                if (getStack() != null) {
                    // Create a new stack with a quantity of 1
                    ItemStack singleStack = getStack().copy();
                    singleStack.stackSize = 1;
                    // Remove one item from the slot
                    super.decrStackSize(1);
                    // Notify listeners of the slot change
                    onSlotChanged();
                    // Return the single stack
                    return singleStack;
                }
                return null;
            }

        });
        this.addSlotToContainer(new Slot(tile, 2, 111, 23) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null && !Unallowed(p_75214_1_.getItem())){
                    return false;
                }
                return false;
            }
            @Override
            public int getSlotStackLimit() {
                return 1;
            }
            @Override
            public ItemStack decrStackSize(int amount) {
                if (getStack() != null) {
                    // Create a new stack with a quantity of 1
                    ItemStack singleStack = getStack().copy();
                    singleStack.stackSize = 1;
                    // Remove one item from the slot
                    super.decrStackSize(1);
                    // Notify listeners of the slot change
                    onSlotChanged();
                    // Return the single stack
                    return singleStack;
                }
                return null;
            }

        });
        this.addSlotToContainer(new Slot(tile, 3, 26, 66) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null && !Unallowed(p_75214_1_.getItem())){
                    return false;
                }
                return false;

            }
            @Override
            public int getSlotStackLimit() {
                return 1;
            }
            @Override
            public ItemStack decrStackSize(int amount) {
                if (getStack() != null) {
                    // Create a new stack with a quantity of 1
                    ItemStack singleStack = getStack().copy();
                    singleStack.stackSize = 1;
                    // Remove one item from the slot
                    super.decrStackSize(1);
                    // Notify listeners of the slot change
                    onSlotChanged();
                    // Return the single stack
                    return singleStack;
                }
                return null;
            }

        });
        this.addSlotToContainer(new Slot(tile, 4, 68, 66) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                return p_75214_1_ != null && isAllowedItemTypeAndroid(p_75214_1_.getItem());
            }
            @Override
            public int getSlotStackLimit() {
                return 1;
            }

            @Override
            public ItemStack decrStackSize(int amount) {
                if (getStack() != null) {
                    // Create a new stack with a quantity of 1
                    ItemStack singleStack = getStack().copy();
                    singleStack.stackSize = 1;
                    // Remove one item from the slot
                    super.decrStackSize(1);
                    // Notify listeners of the slot change
                    onSlotChanged();
                    // Return the single stack
                    return singleStack;
                }
                return null;
            }

            @Override
            public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_75141_2_) {
                int uid = getUniqueID(p_75141_2_);
                super.onPickupFromSlot(p_82870_1_, p_75141_2_);
                if (Tiers.getItemInTier(p_75141_2_.getItem(), (byte) 1)) {
                    if (p_75141_2_.hasTagCompound() && p_75141_2_.getTagCompound().hasKey("Effects")) {
//                        System.out.println("adfsfasfadsf");
//                        NBTTagList effectsList = p_75141_2_.getTagCompound().getTagList("Effects", 10);
//                        for (int i = 0; i < effectsList.tagCount(); i++) {
//                            for (int j = 0; j < modificationPartBuilderTile.getInventory().length; j++) {
//                                if (modificationPartBuilderTile.getInventory()[j] != null){
//                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == Items.milk_bucket) {
//                                        if (!effectsList.getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + j)) {
//
//                                            effectsList.getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                            PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_, uid));
//                                        }
//                                    }
//                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemMedMoss) {
//                                        if (!effectsList.getCompoundTagAt(i).getBoolean(ItemsDBC.ItemMedMoss.getUnlocalizedName() + j)) {
//
//                                            effectsList.getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                            PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_, uid));
//                                        }
//                                    }
//                                }else{
//
//                                    effectsList.getCompoundTagAt(i).setBoolean(Items.milk_bucket.getUnlocalizedName() + j, false);
//                                    effectsList.getCompoundTagAt(i).setBoolean(ItemsDBC.ItemMedMoss.getUnlocalizedName() + j, false);
//                                    PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(),  p_75141_2_, uid));
//
//                                    System.out.println("falseeeee12222");
//                                }
//                                if (i >= effectsList.tagCount() - 1 && j == 8) {
//                                    if (modificationPartBuilderTile.getInventory()[j] != null && modificationPartBuilderTile.getInventory()[j].getItem() != Items.milk_bucket) {
//                                        if (effectsList.getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + j)) {
//
//                                            effectsList.getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, false);
//                                            PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_, uid));
//
//                                        }
//                                    }
//                                    if (modificationPartBuilderTile.getInventory()[j] != null &&  modificationPartBuilderTile.getInventory()[j].getItem() == Items.milk_bucket) {
//
//                                        if (!effectsList.getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + j)) {
//
//                                            effectsList.getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                            PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_, uid));
//
//                                        }
//
//                                    }
//
//
//                                    for (int n = 0; n < modificationPartBuilderTile.getInventory().length; n++){
//                                        if (n != 4){
//                                            modificationPartBuilderTile.setInventorySlotContents(n, null);
//                                        }
//
//                                    }
//
//
//                                }
//
//                            }
//                        }
                    }else{
                        if (p_75141_2_.getItem() == DBCA.potatoCore){
                            NBTTagCompound value = new NBTTagCompound();
                            NBTTagList effectsList = new NBTTagList();
                            NBTTagCompound compound = new NBTTagCompound();
                            for (int j = 0; j < modificationPartBuilderTile.getInventory().length; j++) {

                                if (modificationPartBuilderTile.getInventory()[j] != null) {
                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == Items.milk_bucket){
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();

                                        //PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_,uid));

                                    }
                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemMedMoss){
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();

                                        //PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_,uid));


                                    }
                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == Item.getItemFromBlock(Blocks.ice)){
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();

                                        //PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_,uid));
                                    }
                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemNamekDragonBlock){
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();

                                        //PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_,uid));

                                    }
                                }
//                            if (j == 8) {
//                                if (modificationPartBuilderTile.getInventory()[j] != null) {
//                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == Items.milk_bucket){
//                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                        effectsList.appendTag(value);
//                                        compound.setTag("Effects", effectsList);
//                                        p_75141_2_.setTagCompound(compound);
//                                        value = new NBTTagCompound();
//
//
//                                        //PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(),  p_75141_2_, uid));
//
//
//                                    }
//                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemMedMoss){
//                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                        effectsList.appendTag(value);
//                                        compound.setTag("Effects", effectsList);
//                                        p_75141_2_.setTagCompound(compound);
//                                        value = new NBTTagCompound();
//
//                                        //PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_,uid));
//
//                                    }
//                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == Item.getItemFromBlock(Blocks.ice)){
//                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                        effectsList.appendTag(value);
//                                        compound.setTag("Effects", effectsList);
//                                        p_75141_2_.setTagCompound(compound);
//                                        value = new NBTTagCompound();
//
//                                        //PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_,uid));
//                                    }
//                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemNamekDragonBlock){
//                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                        effectsList.appendTag(value);
//                                        compound.setTag("Effects", effectsList);
//                                        p_75141_2_.setTagCompound(compound);
//                                        value = new NBTTagCompound();
//
//                                        //PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_,uid));
//
//
//                                    }
//                                }
//
//
//
//                            }

                            }
                        }else if(p_75141_2_.getItem() == DBCA.potatoLimb){
                            NBTTagCompound value = new NBTTagCompound();
                            NBTTagList effectsList = new NBTTagList();
                            NBTTagCompound compound = new NBTTagCompound();

                            for (int j = 0; j < modificationPartBuilderTile.getInventory().length; j++) {

                                if (modificationPartBuilderTile.getInventory()[j] != null) {
                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemPP) {
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        compound.setBoolean("Arm", true);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();

                                        //PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_,uid));

                                    }
                                }

                            }
                        }


                        PacketHandler.sendToServer(new PacketUpdateNBT(p_75141_2_.getTagCompound(), p_75141_2_.getItem().getUnlocalizedName(), p_75141_2_,uid));

                        for (int i = 0; i < modificationPartBuilderTile.getInventory().length; i++){
                            if (i != 4){
                                modificationPartBuilderTile.setInventorySlotContents(i, null);
                            }
                        }
                    }




                }

            }
//                    }else{
//
//                        NBTTagCompound newEffects = new NBTTagCompound();
//                        NBTTagCompound value = new NBTTagCompound();
//                        NBTTagList effectsList = new NBTTagList();
//                        for (int j = 0; j < modificationPartBuilderTile.getInventory().length; j++) {
//
//                                if (!p_75141_2_.hasTagCompound()){
//                                    System.out.println("aaaaaaaa");
//                                    if (modificationPartBuilderTile.getInventory()[j] != null && modificationPartBuilderTile.getInventory()[j].getItem() == Items.milk_bucket) {
//                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//
//                                        effectsList.appendTag(value);
//                                        newEffects.setTag("Effects", effectsList);
//                                        System.out.println(newEffects.getTagList("Effects", 10).getCompoundTagAt(0));
//                                        p_75141_2_.setTagCompound(newEffects);
//                                        System.out.println(p_75141_2_.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(0));
//                                        MilkUp = true;
//                                        p_75141_2_.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
//
//
//
//                                        System.out.println("bbbbbbb");
//                                    }
//                                    if (j == 8) {
//                                        if (modificationPartBuilderTile.getInventory()[j] != null && modificationPartBuilderTile.getInventory()[j].getItem() == Items.milk_bucket) {
//                                            value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                            effectsList.appendTag(value);
//                                            newEffects.setTag("Effects", effectsList);
//                                            p_75141_2_.setTagCompound(newEffects);
//                                            MilkUp = true;
//                                            System.out.println("ddddddd");
//                                            p_75141_2_.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
//
//                                        }
//
//
//                                    }
//
//                                }
//
//                                if (j == 8){
//                                    for (int i = 0; i < modificationPartBuilderTile.getInventory().length; i++){
//                                        if (i != 4){
//                                            modificationPartBuilderTile.setInventorySlotContents(i, null);
//                                        }
//
//                                    }
//                                    System.out.println("ccccc");
//                                }
//
//
//
//
//
//                        }
//                    }
//
//                }
//                if (Tiers.getItemInTier(p_75141_2_.getItem(), (byte) 1)){
//                    for (int i = 0; i < modificationPartBuilderTile.getInventory().length; i++){
//                        if (modificationPartBuilderTile.getInventory()[i] != null && modificationPartBuilderTile.getInventory()[i].getItem() == Items.milk_bucket){
//                            NBTTagCompound compound;
//                            if (p_75141_2_.hasTagCompound()){
//                               compound = p_75141_2_.getTagCompound();
//                            }else {
//                                compound = new NBTTagCompound();
//                            }
//
//                            NBTTagList tagList;
//                            if (!compound.hasKey("Effects")) {
//                                tagList = new NBTTagList();
//                            } else {
//                                tagList = p_75141_2_.getTagCompound().getTagList("Effects", 10);
//                            }
//                            NBTTagCompound Item = new NBTTagCompound();
//
//                            Item.setBoolean(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i, true);
//
//
//
//
//
//                            tagList.appendTag(Item);
//
//                            compound.setTag("Effects", tagList);
//
//                            // Set the compound to the ItemStack
//                            p_75141_2_.setTagCompound(compound);
//
//                            /*
//                            if (tagList.tagCount() >= 2 && tagList.getCompoundTagAt(i).hasKey(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i)){
//                                if (!tagList.getCompoundTagAt(i).getBoolean(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i)){
//                                    tagList.getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i, true);
//                                    System.out.println("ad;sjfalsdlgfaksdg");
//                                }
//                            }
//                            if (tagList.tagCount() <= 1){
//                                System.out.println("ad;sjfalsdlgfaksdg222222222");
//                                compound.setTag("Effects", tagList);
//                                p_75141_2_.setTagCompound(compound);
//                                System.out.println(p_75141_2_.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i));
//                            }
//
//                             */
//
//
//
//
//                            if (!p_75141_2_.isItemEnchanted()){
//                                p_75141_2_.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
//                            }
//                            ExtendedPlayer.get(p_82870_1_, null).saveNBTData(p_82870_1_.getEntityData());
//
//                            MilkUp = true;
//
//
//
//                        }
//                        if (i != 4){
//                            if (modificationPartBuilderTile.getInventory()[i] == null){
//
//                                if (p_75141_2_.hasTagCompound() && p_75141_2_.getTagCompound().getTagList("Effects", 10) != null){
//                                    for (int j = 0; j < p_75141_2_.getTagCompound().getTagList("Effects", 10).tagCount(); j++) {
//
//                                        if (p_75141_2_.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(j).getBoolean(Items.milk_bucket.getUnlocalizedName() + i)) {
//                                            System.out.println("Works");
//                                            MilkUp = false;
//                                            p_75141_2_.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(j).setBoolean(Items.milk_bucket.getUnlocalizedName() + i, false);
//                                        }
//
//
//
//                                    }
//                                    if (i == 8){
//                                        if (p_75141_2_.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(p_75141_2_.getTagCompound().getTagList("Effects", 10).tagCount() - 1).getBoolean(Items.milk_bucket.getUnlocalizedName() + i)) {
//                                            System.out.println("Works");
//                                            MilkUp = false;
//                                            p_75141_2_.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(p_75141_2_.getTagCompound().getTagList("Effects", 10).tagCount() - 1).setBoolean(Items.milk_bucket.getUnlocalizedName() + i, false);
//                                           removeEnchantment(p_75141_2_, DBCA.ENCHANTMENT_GLOWING);
//
//
//                                    }
//                                }
//                            }
//                        }
//
//                        if (i != 4){
//                            modificationPartBuilderTile.setInventorySlotContents(i, null);
//                        }
//                    }
//                }



            @Override
            public void onSlotChanged() {
                if (tile.getInventory()[4] != null){
                    if (getSlotFromItemPartTile(tile.getInventory()[4]) != null){
                        String[] NamesOfEffects = getSlotFromItemPartTile(tile.getInventory()[4]);
                        for (int j = 0; j < 9; j++){
                            for (int i = 0; i < NamesOfEffects.length; i++){
                                if (NamesOfEffects[i] != null){
                                    if (NamesOfEffects[i].contains(Items.milk_bucket.getUnlocalizedName() + j) ){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(Items.milk_bucket.getUnlocalizedName().length(), Items.milk_bucket.getUnlocalizedName().length() + 1)), new ItemStack(Items.milk_bucket, 1));
                                    }
                                    if (NamesOfEffects[i].contains(ItemsDBC.ItemMedMoss.getUnlocalizedName() + j) ){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(ItemsDBC.ItemMedMoss.getUnlocalizedName().length(), ItemsDBC.ItemMedMoss.getUnlocalizedName().length() + 1)), new ItemStack(ItemsDBC.ItemMedMoss, 1));
                                    }
                                    if (NamesOfEffects[i].contains(Item.getItemFromBlock(Blocks.ice).getUnlocalizedName() + j) ){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(Item.getItemFromBlock(Blocks.ice).getUnlocalizedName().length(), Item.getItemFromBlock(Blocks.ice).getUnlocalizedName().length() + 1)), new ItemStack(Item.getItemFromBlock(Blocks.ice), 1));
                                    }
                                    if (NamesOfEffects[i].contains(ItemsDBC.ItemNamekDragonBlock.getUnlocalizedName() + j) ){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(ItemsDBC.ItemNamekDragonBlock.getUnlocalizedName().length(), ItemsDBC.ItemNamekDragonBlock.getUnlocalizedName().length() + 1)), new ItemStack(ItemsDBC.ItemNamekDragonBlock, 1));
                                    }
                                    if (NamesOfEffects[i].contains(ItemsDBC.ItemPP.getUnlocalizedName() + j) ){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(ItemsDBC.ItemPP.getUnlocalizedName().length(), ItemsDBC.ItemPP.getUnlocalizedName().length() + 1)), new ItemStack(ItemsDBC.ItemPP, 1));
                                    }

                                }
                            }
                        }
                        tile.getInventory()[4].setTagCompound(new NBTTagCompound());
                    }
                    if (!player.worldObj.isRemote){
                        if (getUniqueID(tile.getInventory()[4]) == -1){
                            setUniqueID(tile.getInventory()[4]);

                        }else{

                        }
                    }
                }


                super.onSlotChanged();
            }
        });
        this.addSlotToContainer(new Slot(tile, 5, 111, 66) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unallowed(p_75214_1_.getItem())){
                    System.out.println("true");
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null && !Unallowed(p_75214_1_.getItem())){
                    return false;
                }
                return false;
            }
            @Override
            public int getSlotStackLimit() {
                return 1;
            }
            @Override
            public ItemStack decrStackSize(int amount) {
                if (getStack() != null) {
                    // Create a new stack with a quantity of 1
                    ItemStack singleStack = getStack().copy();
                    singleStack.stackSize = 1;
                    // Remove one item from the slot
                    super.decrStackSize(1);
                    // Notify listeners of the slot change
                    onSlotChanged();
                    // Return the single stack
                    return singleStack;
                }
                return null;
            }

        });
        this.addSlotToContainer(new Slot(tile, 6, 26, 106) {

            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null && !Unallowed(p_75214_1_.getItem())){
                    return false;

                }
                return false;
            }
            @Override
            public int getSlotStackLimit() {
                return 1;
            }
            @Override
            public void putStack(ItemStack stack) {
                if (stack != null) {
                    // Create a new stack with a quantity of 1
                    ItemStack singleStack = stack.copy();
                    singleStack.stackSize = 1;
                    // Try to add the single stack to the slot
                    super.putStack(singleStack);
                } else {
                    super.putStack(null);
                }
                onSlotChanged();
            }

        });
        this.addSlotToContainer(new Slot(tile, 7, 68, 106) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null && !Unallowed(p_75214_1_.getItem())){
                    return false;
                }
                return false;

            }
            @Override
            public int getSlotStackLimit() {
                return 1;
            }
            @Override
            public ItemStack decrStackSize(int amount) {
                if (getStack() != null) {
                    // Create a new stack with a quantity of 1
                    ItemStack singleStack = getStack().copy();
                    singleStack.stackSize = 1;
                    // Remove one item from the slot
                    super.decrStackSize(1);
                    // Notify listeners of the slot change
                    onSlotChanged();
                    // Return the single stack
                    return singleStack;
                }
                return null;
            }

        });
        this.addSlotToContainer(new Slot(tile, 8, 111, 106) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }

                }
                if (p_75214_1_ != null && !Unallowed(p_75214_1_.getItem())){
                    //return !isAllowedItemTypeAndroid(p_75214_1_.getItem())&& getTierSlots() >= 2;
                    return false;
                }
                return false;
            }
            @Override
            public int getSlotStackLimit() {
                return 1;
            }
            @Override
            public ItemStack decrStackSize(int amount) {
                if (getStack() != null) {
                    // Create a new stack with a quantity of 1
                    ItemStack singleStack = getStack().copy();
                    singleStack.stackSize = 1;
                    // Remove one item from the slot
                    super.decrStackSize(1);
                    // Notify listeners of the slot change
                    onSlotChanged();
                    // Return the single stack
                    return singleStack;
                }
                return null;
            }

        });
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int xPos = 12 + col * 18;  // Adjust based on your layout
                int yPos = 156 + row * 18; // Adjust based on your layout
                this.addSlotToContainer(new Slot(Player.inventory, col + row * 9 + 9, xPos, yPos));
            }
        }

        for (int col = 0; col < 9; ++col) {
            int xPos = 12 + col * 18;  // Adjust based on your layout
            int yPos = 214;           // Adjust based on your layout
            this.addSlotToContainer(new Slot(Player.inventory, col, xPos, yPos));
        }


    }

    private boolean Unallowed(Item item){
        if (modificationPartBuilderTile.getInventory()[4] != null){
            if (modificationPartBuilderTile.getInventory()[4].getItem() == DBCA.potatoCore){
                for (int i = 0; i < allowedItemsCore.length; i++){
                    if (item == allowedItemsCore[i]){

                        return true;

                    }
                    if (i == allowedItemsCore.length - 1 && item != allowedItemsCore[i]){

                        return false;
                    }
                }
                return false;
            }else if (modificationPartBuilderTile.getInventory()[4].getItem() == DBCA.potatoLimb){
                for (int i = 0; i < allowedItemsLimb.length; i++){
                    if (item == allowedItemsLimb[i]){

                        return true;

                    }
                    if (i == allowedItemsLimb.length - 1 && item != allowedItemsLimb[i]){

                        return false;
                    }
                }
                return false;
            }
        }

        return false;
    }
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;

    }

    private boolean isAllowedItemTypeAndroid(Item item) {
        // Define the allowed item types here
        // You can use any method to determine if the item is allowed

        if (item != DBCA.potatoCore && item != DBCA.potatoLimb) {
            return false;
        } else {
            return true;
        }

    }

//    private byte getTierSlots(){
//        if (modificationPartBuilderTile.getInventory()[4] == null){
//            return 0;
//        }
//        if (modificationPartBuilderTile.getInventory()[4] != null && Tiers.getItemInTier(modificationPartBuilderTile.getInventory()[4].getItem(), (byte) 1)){
//            return 1;
//        }
//        if (modificationPartBuilderTile.getInventory()[4] != null && Tiers.getItemInTier(modificationPartBuilderTile.getInventory()[4].getItem(), (byte) 2)){
//            return 2;
//        }
//        if (modificationPartBuilderTile.getInventory()[4] != null && Tiers.getItemInTier(modificationPartBuilderTile.getInventory()[4].getItem(), (byte) 3)){
//            return 3;
//        }
//        return 0;
//    }



    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int slotIndex) {


        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {

            ItemStack stackInSlot = slot.getStack();
            itemstack = stackInSlot.copy();

            int playerInventoryStart = 9; // Start of player's inventory slots
            int playerInventoryEnd = 44; // End of player's inventory slots, including hotbar

//            if (slotIndex < playerInventoryStart) { // Transfer from custom tile entity to player inventory
//
//                if (modificationPartBuilderTile.getInventory()[4] != null){
//                    if (slotIndex == 4){
//                        for (int i = 0; i < modificationPartBuilderTile.getInventory().length; i++){
//                            if (modificationPartBuilderTile.getInventory()[i] != null && modificationPartBuilderTile.getInventory()[i].getItem() == Items.milk_bucket){
//                                NBTTagCompound compound;
//                                if (stackInSlot.hasTagCompound()){
//                                    compound = stackInSlot.getTagCompound();
//                                }else {
//                                    compound = new NBTTagCompound();
//                                }
//
//                                NBTTagList tagList;
//                                if (!compound.hasKey("Effects")) {
//                                    tagList = new NBTTagList();
//                                } else {
//                                    tagList = stackInSlot.getTagCompound().getTagList("Effects", 10);
//                                }
//                                NBTTagCompound Item = new NBTTagCompound();
//
//                                Item.setBoolean(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i, true);
//
//
//
//
//
//                                tagList.appendTag(Item);
//
//                                compound.setTag("Effects", tagList);
//
//                                // Set the compound to the ItemStack
//                                stackInSlot.setTagCompound(compound);
//
//                            /*
//                            if (tagList.tagCount() >= 2 && tagList.getCompoundTagAt(i).hasKey(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i)){
//                                if (!tagList.getCompoundTagAt(i).getBoolean(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i)){
//                                    tagList.getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i, true);
//                                    System.out.println("ad;sjfalsdlgfaksdg");
//                                }
//                            }
//                            if (tagList.tagCount() <= 1){
//                                System.out.println("ad;sjfalsdlgfaksdg222222222");
//                                compound.setTag("Effects", tagList);
//                                p_75141_2_.setTagCompound(compound);
//                                System.out.println(p_75141_2_.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i));
//                            }
//
//                             */
//
//
//
//
//                                if (!stackInSlot.isItemEnchanted()){
//                                    stackInSlot.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
//                                }
//
//
//                                ExtendedPlayer.get(p_82846_1_, null).saveNBTData(p_82846_1_.getEntityData());
//
//                                if (i != 4){
//                                    modificationPartBuilderTile.setInventorySlotContents(i, null);
//                                }
//                                if (!MilkUp){
//
//                                    MilkUp = true;
//                                }
//
//
//
//                            }
//
//                        }
//
//
//                    }else {
//
//                        if (modificationPartBuilderTile.getInventory()[4].hasTagCompound() && modificationPartBuilderTile.getInventory()[4].getTagCompound().getTagList("Effects", 10) != null){
//
//
//                            if (modificationPartBuilderTile.getInventory()[4].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(slotIndex).getBoolean(modificationPartBuilderTile.getInventory()[slotIndex].getItem().getUnlocalizedName() + slotIndex)) {
//                                System.out.println("Works");
//                                MilkUp = false;
//                                modificationPartBuilderTile.getInventory()[4].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(slotIndex).setBoolean(modificationPartBuilderTile.getInventory()[slotIndex].getItem().getUnlocalizedName() + slotIndex, false);
//                            }
//
//
//                        }
//
//
//                    }
//
//                }else{
//                    return null;
//                }
            if (slotIndex < playerInventoryStart) {
                if (!mergeItemStack(stackInSlot, playerInventoryStart, playerInventoryEnd, true)) {
                    return null;
                }

//                if (Tiers.getItemInTier(stackInSlot.getItem(), (byte) 1) && !stackInSlot.hasTagCompound()) {
//                    NBTTagCompound value = new NBTTagCompound();
//                    NBTTagList effectsList = new NBTTagList();
//                    for (int j = 0; j < modificationPartBuilderTile.getInventory().length; j++) {
//                        System.out.println("aaaaaaaa");
//                        stackInSlot.setTagCompound(new NBTTagCompound());
//                        if (modificationPartBuilderTile.getInventory()[j] != null && modificationPartBuilderTile.getInventory()[j].getItem() == Items.milk_bucket) {
//                            value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                            effectsList.appendTag(value);
//                            stackInSlot.getTagCompound().setTag("Effects", effectsList);
//                            System.out.println(stackInSlot.getItem().getUnlocalizedName());
//                            System.out.println(stackInSlot.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(0));
//                            MilkUp = true;
//                            stackInSlot.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
//                            System.out.println("bbbbbbb");
//                        }
//                        if (j == 8) {
//                            if (modificationPartBuilderTile.getInventory()[j] != null && modificationPartBuilderTile.getInventory()[j].getItem() == Items.milk_bucket) {
//                                value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                effectsList.appendTag(value);
//                                stackInSlot.getTagCompound().setTag("Effects", effectsList);
//                                MilkUp = true;
//                                System.out.println("ddddddd");
//                                stackInSlot.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
//
//                            }
//                        }
//
//                        if (j == 8) {
//                            for (int i = 0; i < modificationPartBuilderTile.getInventory().length; i++) {
//                                if (i != 4) {
//                                    // modificationPartBuilderTile.setInventorySlotContents(i, null);
//                                }
//
//                            }
//                            System.out.println("ccccc");
//                        }
//
//
//                    }
//                } else {
//                    for (int j = 0; j < modificationPartBuilderTile.getInventory().length; j++) {
//
//                        if (modificationPartBuilderTile.getInventory()[j] != null) {
//                            for (int i = 0; i < stackInSlot.getTagCompound().getTagList("Effects", 10).tagCount(); i++) {
//                                if (modificationPartBuilderTile.getInventory()[j].getItem() == Items.milk_bucket) {
//                                    if (!stackInSlot.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j)) {
//                                        stackInSlot.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                        MilkUp = true;
//                                        stackInSlot.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
//                                    }
//                                }else{
//                                    stackInSlot.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, false);
//                                    MilkUp = false;
//
//                                }
//                            }
//
//                        }
//                        if (j == 8) {
//                            for ( int i = 0; i < modificationPartBuilderTile.getInventory()[j].getTagCompound().getTagList("Effects", 10).tagCount(); i++){
//                                if (modificationPartBuilderTile.getInventory()[j].getItem() != Items.milk_bucket) {
//
//                                    stackInSlot.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, false);
//                                    MilkUp = false;
//                                    removeEnchantment(stackInSlot, DBCA.ENCHANTMENT_GLOWING);
//                                }else {
//                                    stackInSlot.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
//                                    MilkUp = true;
//                                    stackInSlot.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
//                                }
//                            }
//
//                        }
//
//                    }
//                }
//
//
////
////
////                        if (j == 8) {
////                            for (int i = 0; i < modificationPartBuilderTile.getInventory().length; i++) {
////                                if (i != 4) {
////                                    modificationPartBuilderTile.setInventorySlotContents(i, null);
////                                }
////
////                            }
////                            System.out.println("ccccc");
////
////                        }
////
////                    }
////
////                }
//
//                if (stackInSlot.hasTagCompound()){
//                    System.out.println(stackInSlot.getTagCompound().getTagList("Effects", 10).getCompoundTagAt(0));
//
//
//                    slot.onSlotChanged();
//                    slot.onPickupFromSlot(p_82846_1_, stackInSlot);
//                    return stackInSlot;
//                }else{
//                    slot.onSlotChange(stackInSlot, itemstack);
//                }
                slot.onSlotChange(stackInSlot, itemstack);
            }


            if (slotIndex >= playerInventoryStart) { // Transfer from player inventory to custom tile entity

                if (Tiers.getItemInTier(stackInSlot.getItem(), (byte) 1)) {
                    // Adjust the range parameters according to the slots in your custom tile entity
                    if (!mergeItemStack(stackInSlot, 4, 5, false)) {
                        return null;
                    }
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        if (getSlotFromItemPartTile(stackInSlot) != null){
                            String[] NamesOfEffects = getSlotFromItemPartTile(stackInSlot);
                            for (int j = 0; j < 9; j++){
                                for (int i = 0; i < NamesOfEffects.length; i++){
                                    if (NamesOfEffects[i] != null && NamesOfEffects[i].contains(Items.milk_bucket.getUnlocalizedName() + j)){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(Items.milk_bucket.getUnlocalizedName().length(), Items.milk_bucket.getUnlocalizedName().length() + 1)), new ItemStack(Items.milk_bucket, 1));
                                    }
                                    if (NamesOfEffects[i] != null && NamesOfEffects[i].contains(ItemsDBC.ItemMedMoss.getUnlocalizedName() + j)){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(ItemsDBC.ItemMedMoss.getUnlocalizedName().length(), ItemsDBC.ItemMedMoss.getUnlocalizedName().length() + 1)), new ItemStack(ItemsDBC.ItemMedMoss, 1));
                                    }
                                    if (NamesOfEffects[i] != null && NamesOfEffects[i].contains(Item.getItemFromBlock(Blocks.ice).getUnlocalizedName() + j)){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(Item.getItemFromBlock(Blocks.ice).getUnlocalizedName().length(), Item.getItemFromBlock(Blocks.ice).getUnlocalizedName().length() + 1)), new ItemStack(Item.getItemFromBlock(Blocks.ice), 1));
                                    }
                                    if (NamesOfEffects[i] != null && NamesOfEffects[i].contains(ItemsDBC.ItemNamekDragonBlock.getUnlocalizedName() + j)){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(ItemsDBC.ItemNamekDragonBlock.getUnlocalizedName().length(), ItemsDBC.ItemNamekDragonBlock.getUnlocalizedName().length() + 1)), new ItemStack(ItemsDBC.ItemNamekDragonBlock, 1));
                                    }
                                    if (NamesOfEffects[i] != null && NamesOfEffects[i].contains(ItemsDBC.ItemPP.getUnlocalizedName() + j)){
                                        modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(NamesOfEffects[i].substring(ItemsDBC.ItemPP.getUnlocalizedName().length(), ItemsDBC.ItemPP.getUnlocalizedName().length() + 1)), new ItemStack(ItemsDBC.ItemPP, 1));
                                    }
                                }
                            }

                        }
                    }
                }else{
                    if (modificationPartBuilderTile.getInventory()[4] != null){

                        if (Tiers.getItemInTier(modificationPartBuilderTile.getInventory()[4].getItem(), (byte) 1)) {
                            if (modificationPartBuilderTile.getInventory()[4].getItem() == DBCA.potatoCore) {
                                if (this.inventorySlots.get(3) != null && this.inventorySlots.get(5) != null) {
                                    if (stackInSlot.getItem() == Items.milk_bucket) {
                                        if (stackInSlot.stackSize > 1) {
                                            stackInSlot.stackSize = 1;
                                        }
                                        if (!mergeItemStack(stackInSlot, 3, 6, false) || mergeItemStack(stackInSlot, 4, 5, false)) {
                                            return null;
                                        }
                                    }
                                    if (stackInSlot.getItem() == ItemsDBC.ItemMedMoss) {

                                        if (((Slot) this.inventorySlots.get(3)).getStack() == null) {
                                            if (stackInSlot.stackSize > 1) {
                                                stackInSlot.stackSize = 1;
                                            }
                                            if (!mergeItemStack(stackInSlot, 3, 4, false) || mergeItemStack(stackInSlot, 4, 5, false)) {
                                                return null;
                                            }
                                        } else if (((Slot) this.inventorySlots.get(5)).getStack() == null) {
                                            if (stackInSlot.stackSize > 1) {
                                                stackInSlot.stackSize = 1;
                                            }
                                            if (!mergeItemStack(stackInSlot, 5, 6, false) || mergeItemStack(stackInSlot, 4, 5, false)) {

                                                return null;
                                            }
                                        } else {
                                            return null;
                                        }

                                    }
                                    if (stackInSlot.getItem() == Item.getItemFromBlock(Blocks.ice)) {
                                        if (((Slot) this.inventorySlots.get(3)).getStack() == null) {
                                            if (stackInSlot.stackSize > 1) {
                                                stackInSlot.stackSize = 1;
                                            }
                                            if (!mergeItemStack(stackInSlot, 3, 4, false) || mergeItemStack(stackInSlot, 4, 5, false)) {
                                                return null;
                                            }
                                        } else if (((Slot) this.inventorySlots.get(5)).getStack() == null) {
                                            if (stackInSlot.stackSize > 1) {
                                                stackInSlot.stackSize = 1;
                                            }
                                            if (!mergeItemStack(stackInSlot, 5, 6, false) || mergeItemStack(stackInSlot, 4, 5, false)) {

                                                return null;
                                            }
                                        } else {
                                            return null;
                                        }
                                    }
                                    if (stackInSlot.getItem() == ItemsDBC.ItemNamekDragonBlock) {

                                        if (((Slot) this.inventorySlots.get(3)).getStack() == null) {
                                            if (stackInSlot.stackSize > 1) {
                                                stackInSlot.stackSize = 1;
                                            }
                                            if (!mergeItemStack(stackInSlot, 3, 4, false) || mergeItemStack(stackInSlot, 4, 5, false)) {
                                                return null;
                                            }
                                        } else if (((Slot) this.inventorySlots.get(5)).getStack() == null) {
                                            if (stackInSlot.stackSize > 1) {
                                                stackInSlot.stackSize = 1;
                                            }
                                            if (!mergeItemStack(stackInSlot, 5, 6, false) || mergeItemStack(stackInSlot, 4, 5, false)) {

                                                return null;
                                            }
                                        } else {
                                            return null;
                                        }

                                    }
                                    if (stackInSlot.getItem() != Items.milk_bucket && stackInSlot.getItem() != ItemsDBC.ItemMedMoss && stackInSlot.getItem() != Item.getItemFromBlock(Blocks.ice) && stackInSlot.getItem() != ItemsDBC.ItemNamekDragonBlock) {
                                        return null;
                                    }
                                    if (stackInSlot.getItem() == ItemsDBC.ItemPP) {
                                        return null;
                                    }

                                } else {
                                    return null;
                                }

                            } else if (modificationPartBuilderTile.getInventory()[4].getItem() == DBCA.potatoLimb) {
                                if (modificationPartBuilderTile.getInventory()[4] != null) {
                                    if (Tiers.getItemInTier(modificationPartBuilderTile.getInventory()[4].getItem(), (byte) 1)) {
                                        if (this.inventorySlots.get(3) != null && this.inventorySlots.get(5) != null) {
                                            if (stackInSlot.getItem() == ItemsDBC.ItemPP) {
                                                if (stackInSlot.stackSize > 1) {
                                                    stackInSlot.stackSize = 1;
                                                }
                                                if (!mergeItemStack(stackInSlot, 3, 6, false) || mergeItemStack(stackInSlot, 4, 5, false)) {
                                                    return null;
                                                }
                                            }
                                        }
                                    }
                                    if (stackInSlot.getItem() == Items.milk_bucket && stackInSlot.getItem() == ItemsDBC.ItemMedMoss && stackInSlot.getItem() == Item.getItemFromBlock(Blocks.ice) && stackInSlot.getItem() == ItemsDBC.ItemNamekDragonBlock) {
                                        return null;
                                    }
                                    if (stackInSlot.getItem() != ItemsDBC.ItemPP) {
                                        return null;
                                    }
                                }else{
                                    return null;
                                }
                            } else {
                                return null;
                            }


                        }else{
                            return null;
                        }
                    }


                }

            }
            if (slotIndex >= playerInventoryStart){
                if (itemstack.stackSize > 1){

                    this.putStackInSlot(slotIndex,itemstack.splitStack(itemstack.stackSize - 1));
                    return null;
                }else{
                    if (stackInSlot.stackSize == 0) {
                        slot.putStack(null);
                    } else {
                        slot.onSlotChanged();
                    }
                }

            }
            if (stackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }


            slot.onPickupFromSlot(p_82846_1_, stackInSlot);







//                if (slotIndex == 4 && stackInSlot.hasTagCompound()){
//                    //PacketHandler.sendToServer(new PacketUpdateNBT(stackInSlot.getTagCompound(), stackInSlot.getItem().getUnlocalizedName(), stackInSlot, getUniqueID(stackInSlot)));
//                    //System.out.println(stackInSlot.getTagCompound().getTagList("Effects",10).getCompoundTagAt(0));
//                    return PacketUpdateNBT.PacketHandlerUpdateNBT.getItemStack();
//                }

        }


        return itemstack;


    }

    @Override
    public void onContainerClosed(EntityPlayer player) {


        for (int i = 0; i < modificationPartBuilderTile.getInventory().length; i++){
            if (modificationPartBuilderTile.getInventory()[i] != null){
                if (i == 4){
                    player.dropPlayerItemWithRandomChoice(new ItemStack(modificationPartBuilderTile.getInventory()[4].getItem()), false);
                }
                else{
                    player.dropPlayerItemWithRandomChoice(modificationPartBuilderTile.getInventory()[i], false);
                }

                modificationPartBuilderTile.setInventorySlotContents(i, null);
            }
        }


        super.onContainerClosed(player);

    }

    private String[] getSlotFromItemPartTile(ItemStack itemStack){
        String[] effects = new String[9];
        if (itemStack != null && itemStack.hasTagCompound()){
            if (itemStack.getTagCompound().getTagList("Effects", 10) != null){
                NBTTagList effectsList = itemStack.getTagCompound().getTagList("Effects", 10);
                for(int i = 0; i < effectsList.tagCount(); i++){
                    for (int j = 0; j < 9; j++){
                        if (effectsList.getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + j)){
                            effects[j] = Items.milk_bucket.getUnlocalizedName() + j;
                        }
                        if (effectsList.getCompoundTagAt(i).getBoolean(ItemsDBC.ItemMedMoss.getUnlocalizedName() + j)){
                            effects[j] = ItemsDBC.ItemMedMoss.getUnlocalizedName() + j;
                        }
                        if (effectsList.getCompoundTagAt(i).getBoolean(Item.getItemFromBlock(Blocks.ice).getUnlocalizedName() + j)){
                            effects[j] = Item.getItemFromBlock(Blocks.ice).getUnlocalizedName() + j;
                        }
                        if (effectsList.getCompoundTagAt(i).getBoolean(ItemsDBC.ItemNamekDragonBlock.getUnlocalizedName() + j)){
                            effects[j] = ItemsDBC.ItemNamekDragonBlock.getUnlocalizedName() + j;
                        }
                        if (effectsList.getCompoundTagAt(i).getBoolean(ItemsDBC.ItemPP.getUnlocalizedName() + j)){
                            effects[j] = ItemsDBC.ItemPP.getUnlocalizedName() + j;
                            System.out.println("work1");
                        }

                    }
                    if (i >= effectsList.tagCount() - 1){
                        return effects;
                    }
                }
            }
            return null;
        }
        return null;

    }

/*
    @Override
    public void putStackInSlot(int p_75141_1_, ItemStack p_75141_2_) {
        super.putStackInSlot(p_75141_1_, p_75141_2_);
        if (p_75141_1_ == 4){
            if (Tiers.getItemInTier(p_75141_2_.getItem(), (byte) 1)){
                if (Tiers.getItemInTier(p_75141_2_.getItem(), (byte) 1)) {
                    // Adjust the range parameters according to the slots in your custom tile entity


                        if (getSlotFromPart(2) != null){
                            String[] NamesOfEffects = getSlotFromPart(2);
                            assert NamesOfEffects != null;
                            for (String namesOfEffect : NamesOfEffects) {
                                if (namesOfEffect.contains(Items.milk_bucket.getUnlocalizedName())) {
                                    modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(namesOfEffect.substring(Items.milk_bucket.getUnlocalizedName().length(), Items.milk_bucket.getUnlocalizedName().length() + 1)), new ItemStack(Items.milk_bucket, 1));
                                }
                            }
                        }

                }
            }
        }
        if (p_75141_1_ >= 9 && p_75141_1_ < 44){
            if (modificationPartBuilderTile.getInventory()[4] == null){
                if (Tiers.getItemInTier(p_75141_2_.getItem(), (byte) 1)){
                    for (int i = 0; i < modificationPartBuilderTile.getInventory().length; i++){
                        if (modificationPartBuilderTile.getInventory()[i] != null && modificationPartBuilderTile.getInventory()[i].getItem() == Items.milk_bucket){
                            NBTTagCompound compound = p_75141_2_.getTagCompound();
                            NBTTagList tagList = new NBTTagList();;
                            NBTTagCompound Item = new NBTTagCompound();

                            Item.setBoolean(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i, true);
                            if (compound == null){
                                compound = new NBTTagCompound();
                            }else{
                                if (!compound.hasKey("Effects")){

                                    System.out.println("tAGFASDASD222222222");

                                }else {
                                    tagList = p_75141_2_.getTagCompound().getTagList("Effects", 10);
                                    System.out.println("tAGFASDASD");
                                }
                            }


                            tagList.appendTag(Item);



                            if (tagList.tagCount() >= 2 && tagList.getCompoundTagAt(i).hasKey(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i)){
                                if (!tagList.getCompoundTagAt(i).getBoolean(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i)){
                                    tagList.getCompoundTagAt(i).setBoolean(modificationPartBuilderTile.getInventory()[i].getItem().getUnlocalizedName() + i, true);
                                    System.out.println("ad;sjfalsdlgfaksdg");
                                }
                            }
                            if (tagList.tagCount() <= 1){
                                System.out.println("ad;sjfalsdlgfaksdg222222222");
                                tagList.appendTag(Item);
                                compound.setTag("Effects", tagList);
                                p_75141_2_.setTagCompound(compound);
                            }




                            if (!p_75141_2_.isItemEnchanted()){
                                p_75141_2_.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
                            }

                            System.out.println("3333");


                        }





                    }
                }
            }

        }
    }

*/




    private static final Map<Item, Integer> nextID = new HashMap<>();

    public static void init() {
        // Initialize the next available ID for each item type
        for (Object item : Item.itemRegistry) {
            if (item == DBCA.potatoCore || item == DBCA.potatoLimb){
                nextID.put((Item) item, 1);
            }

        }
    }

    public static void setUniqueID(ItemStack stack) {
        Item item = stack.getItem();
        int id = nextID.getOrDefault(item, 1);
        stack.setTagInfo("UniqueID", new NBTTagInt(id));
        nextID.put(item, id + 1);

    }

    public static int getUniqueID(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null || !tag.hasKey("UniqueID", Constants.NBT.TAG_INT)) {
            return -1;
        }
        return tag.getInteger("UniqueID");
    }




}


