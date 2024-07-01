package me.guy.dbca.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.DBCA;

import me.guy.dbca.ExtendedPlayer;
import me.guy.dbca.blocks.AndroidTableTile;
import me.guy.dbca.blocks.ModificationPartBuilderTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;


@SideOnly(Side.CLIENT)
public class AndroidContainer extends Container {


    private final AndroidTableTile androidTableTile;


    public AndroidContainer(InventoryPlayer playerInventor, EntityPlayer entityPlayer, AndroidTableTile androidTableTile) {

        if (ExtendedPlayer.PlayerData.getInventory()[2] != null){
            androidTableTile.setInventorySlotContents(2, ExtendedPlayer.PlayerData.getInventory()[2]);
        }

        if (ExtendedPlayer.PlayerData.getInventory()[0] != null){

            androidTableTile.setInventorySlotContents(0, ExtendedPlayer.PlayerData.getInventory()[0]);
        }if (ExtendedPlayer.PlayerData.getInventory()[1] != null){

            androidTableTile.setInventorySlotContents(1, ExtendedPlayer.PlayerData.getInventory()[1]);
        }if (ExtendedPlayer.PlayerData.getInventory()[3] != null){

            androidTableTile.setInventorySlotContents(3, ExtendedPlayer.PlayerData.getInventory()[3]);
        }if (ExtendedPlayer.PlayerData.getInventory()[4] != null){

            androidTableTile.setInventorySlotContents(4, ExtendedPlayer.PlayerData.getInventory()[4]);
        }if (ExtendedPlayer.PlayerData.getInventory()[5] != null){

            androidTableTile.setInventorySlotContents(5, ExtendedPlayer.PlayerData.getInventory()[5]);
        }






        this.androidTableTile = androidTableTile;

        this.addSlotToContainer(new Slot(androidTableTile, 0, 117, 13) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                return p_75214_1_ != null && isAllowedItemTypeHead(p_75214_1_.getItem());

            }

        });

        this.addSlotToContainer(new Slot(androidTableTile, 1, 96, 35) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                return p_75214_1_ != null && isAllowedItemTypeArm(p_75214_1_.getItem());
            }

        });
        this.addSlotToContainer(new Slot(androidTableTile, 2, 117, 35) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                return p_75214_1_ != null && isAllowedItemTypeCore(p_75214_1_.getItem());
            }
            @Override
            public void onPickupFromSlot(EntityPlayer player, ItemStack pickedUpStack) {
                super.onPickupFromSlot(player, pickedUpStack);

                int playerInventoryStart = 6; // Start of player's inventory slots
                int playerInventoryEnd = 41; // End of player's inventory slots, including hotbar

                if (pickedUpStack.getItem() == DBCA.potatoCore) {
                    for (int i = 0; i < playerInventoryStart; i++) {
                        Slot slot = (Slot) inventorySlots.get(i);
                        ItemStack stackInSlot = slot.getStack();

                        if (stackInSlot != null && stackInSlot.getItem() == DBCA.potatoLimb) {
                            boolean merged = false;

                            // Try to merge the item stack into the player's inventory
                            for (int j = playerInventoryStart; j <= playerInventoryEnd; j++) {
                                Slot targetSlot = (Slot) inventorySlots.get(j);

                                if (targetSlot.getStack() == null) {
                                    // If the target slot is empty, try to merge the stack
                                    ItemStack stack = stackInSlot.copy();
                                    stack.stackSize = stackInSlot.stackSize;

                                    if (mergeItemStack(stack, playerInventoryStart, playerInventoryEnd, true)) {
                                        slot.putStack(null);
                                        merged = true;
                                        break;
                                    }
                                } else if (targetSlot.getStack().getItem() == stackInSlot.getItem()) {
                                    // If the target slot has the same item, try to merge stacks
                                    int maxSize = targetSlot.getStack().getMaxStackSize();
                                    int combinedSize = targetSlot.getStack().stackSize + stackInSlot.stackSize;

                                    if (combinedSize <= maxSize) {
                                        targetSlot.getStack().stackSize = combinedSize;
                                        slot.putStack(null);
                                        merged = true;
                                        break;
                                    }
                                }
                            }

                            // If not merged and inventory is full, drop the item
                            if (!merged) {
                                ItemStack remainingStack = slot.getStack();

                                player.dropPlayerItemWithRandomChoice(remainingStack, false);

                                slot.putStack(null);
                            }
                        }
                    }
                }
            }

        });
        this.addSlotToContainer(new Slot(androidTableTile, 3, 138, 35) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                return p_75214_1_ != null && isAllowedItemTypeArm(p_75214_1_.getItem());
            }

        });
        this.addSlotToContainer(new Slot(androidTableTile, 4, 104, 57) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                return p_75214_1_ != null && isAllowedItemTypeArm(p_75214_1_.getItem());
            }

        });
        this.addSlotToContainer(new Slot(androidTableTile, 5, 129, 57) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                return p_75214_1_ != null && isAllowedItemTypeArm(p_75214_1_.getItem());
            }

        });

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int xPos = 8 + col * 18;  // Adjust based on your layout
                int yPos = 84 + row * 18; // Adjust based on your layout
                this.addSlotToContainer(new Slot(playerInventor, col + row * 9 + 9, xPos, yPos));
            }
        }

        for (int col = 0; col < 9; ++col) {
            int xPos = 8 + col * 18;  // Adjust based on your layout
            int yPos = 142;           // Adjust based on your layout
            this.addSlotToContainer(new Slot(playerInventor, col, xPos, yPos));
        }


    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return androidTableTile.isUseableByPlayer(player);

    }

    private boolean isAllowedItemTypeCore(Item item) {
        // Define the allowed item types here
        // You can use any method to determine if the item is allowed

        if (item != DBCA.potatoCore) {
            return false;
        } else {
            return true;
        }

    }

    private boolean isAllowedItemTypeHead(Item item) {
        // Define the allowed item types here
        // You can use any method to determine if the item is allowed

        return false;

    }

    private boolean isAllowedItemTypeArm(Item item) {
        // Define the allowed item types here
        // You can use any method to determine if the item is allowed
        if (item != DBCA.potatoLimb){
            return false;
        }else return androidTableTile.getInventory()[2] != null;
    }
    /*
    @Override
    public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_) {
        if (p_75144_1_ >= 0 && p_75144_1_ < 44){
            return super.slotClick(p_75144_1_, p_75144_2_, p_75144_3_, p_75144_4_);
        }
        else {
            return null;
        }
    }
    */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int slotIndex) {


        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {

            ItemStack stackInSlot = slot.getStack();
            itemstack = stackInSlot.copy();


            int playerInventoryStart = 6; // Start of player's inventory slots
            int playerInventoryEnd = 41; // End of player's inventory slots, including hotbar

            if (slotIndex < playerInventoryStart) { // Transfer from custom tile entity to player inventory
                if (!mergeItemStack(stackInSlot, playerInventoryStart, playerInventoryEnd, true)) {
                    return null;
                }

                if (stackInSlot.getItem() == DBCA.potatoCore){
                    for (int i = 0; i < playerInventoryStart; i++){
                        if (((Slot)this.inventorySlots.get(i)).getStack() != null && ((Slot)this.inventorySlots.get(i)).getStack().getItem() == DBCA.potatoLimb){
                            for (int j = playerInventoryStart; j < playerInventoryEnd; j++){

                                if (((Slot) this.inventorySlots.get(j)).getStack() == null) {
                                    ItemStack stack = ((Slot) this.inventorySlots.get(i)).getStack();
                                    if (stack != null){
                                        if (!mergeItemStack(stack, playerInventoryStart, playerInventoryEnd, true)) {
                                            p_82846_1_.dropPlayerItemWithRandomChoice(stackInSlot, false);
                                        }

                                        ((Slot) this.inventorySlots.get(i)).putStack(null);
                                        ((Slot) this.inventorySlots.get(slot.slotNumber)).putStack(null);
                                    }
                                }

                                if (i == playerInventoryStart - 1 && j == playerInventoryEnd - 1){
                                    return null;
                                }

                            }
                        }

                    }
                }
                slot.onSlotChange(stackInSlot, itemstack);


            } else { // Transfer from player inventory to custom tile entity
                if (androidTableTile.getInventory()[2] == null){
                    if (stackInSlot.getItem() == DBCA.potatoCore) {

                        // Adjust the range parameters according to the slots in your custom tile entity

                        if (!mergeItemStack(stackInSlot, 2, 3, false)) {

                            return null;
                        }
                    }else{
                        return null;
                    }
                }else{
                    if (stackInSlot.getItem() == DBCA.potatoLimb){
                        // Adjust the range parameters according to the slots in your custom tile entity
                        if (stackInSlot.hasTagCompound()){
                            if (stackInSlot.getTagCompound().getBoolean("Arm")){

                                if (!mergeItemStack(stackInSlot, 1, 2, false) && !mergeItemStack(stackInSlot, 3, 4, false)) {

                                    return null;
                                }
                            }
                            else if (stackInSlot.getTagCompound().getBoolean("Leg")){
                                if (!mergeItemStack(stackInSlot, 4, 6, false)) {

                                    return null;
                                }
                            }

                        }else{

                            if (!mergeItemStack(stackInSlot, 1, 6, false)) {

                                return null;
                            }
                        }
                    }else{

                        return null;
                    }
                }








            }




            if (stackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }


            slot.onPickupFromSlot(p_82846_1_, stackInSlot);



        }


        return itemstack;


    }

    /*
    private boolean step = false;
    Slot slot;
    ItemStack heldStack;
    InventoryPlayer inventoryPlayer;
    @Override
    public ItemStack slotClick(int slotIndex, int mouseButton, int modifier, EntityPlayer player) {
            System.out.println("1111");
            inventoryPlayer = player.inventory;
            heldStack = inventoryPlayer.getItemStack();
            slot = slotIndex >= 0 ? ((Slot)this.inventorySlots.get(slotIndex)) : null;

            if (slot != null) {
                // Handle left-clicking (normal clicking)

                if (mouseButton == 0 && modifier != 1) {
                    // If the player is holding an item
                    if (heldStack != null && !step) {
                        // Attempt to transfer the held item to the clicked slot
                        if (slot.isItemValid(heldStack)) {

                            if (slot.getStack() != null){
                                if (slot.getStack().getMaxStackSize() <= 1){
                                    step = true;
                                    return null;
                                }
                                else {

                                    System.out.println("123123");
                                    slot.putStack(new ItemStack(heldStack.getItem(), heldStack.stackSize + slot.getStack().stackSize));
                                    inventoryPlayer.setItemStack(null);
                                    step = true;
                                }

                            }else{
                                slot.putStack(heldStack.copy());
                                inventoryPlayer.setItemStack(null);
                                step = true;
                            }




                        }


                    } else if (!step){ // If the player is not holding an item, try to pick up an item from the clicked slot
                        ItemStack stackInSlot = slot.getStack();
                        if (stackInSlot != null) {
                            // Pick up the item from the clicked slot
                            inventoryPlayer.setItemStack(stackInSlot.copy());
                            slot.putStack(null); // Clear the slot
                        }
                        step = true;

                    }

                }
                // Handle right-clicking (shift-clicking)
                 if (modifier == 1) {
                    // Implement shift-click behavior here (if needed)

                    return this.transferStackInSlot(player, slotIndex);
                 }
            }

            return heldStack;


    }
    */

    @Override
    public void onContainerClosed(EntityPlayer player) {
        ExtendedPlayer.get(player, androidTableTile).saveNBTData(player.getEntityData());
        /*
        if (ExtendedPlayer.PlayerData.getInventory()[2] != null){

            if (ExtendedPlayer.PlayerData.getInventory()[2].getTagCompound() != null){

                if (ExtendedPlayer.PlayerData.getInventory()[2].getTagCompound().getTagList("Effects", 10) != null){

                    NBTTagList effectsList = ExtendedPlayer.PlayerData.getInventory()[2].getTagCompound().getTagList("Effects", 10);


                                for (int i = 0; i < effectsList.tagCount(); i++){
                                    for (int j = 0; j < 9; j++){
                                        if (effectsList.getCompoundTagAt(i) != null && effectsList.getCompoundTagAt(i).hasKey(Items.milk_bucket.getUnlocalizedName() + j)){
                                            MilkUp = true;
                                            System.out.println(MilkUp);
                                        }

                                        if (j == 8){
                                            if (!MilkUp){
                                                if(effectsList.getCompoundTagAt(i) != null && effectsList.getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + j) ){
                                                    MilkUp = true;
                                                    System.out.println(MilkUp);
                                                }else {
                                                    MilkUp = false;
                                                    System.out.println(MilkUp);
                                                }
                                            }else {
                                                if(effectsList.getCompoundTagAt(i) != null && !effectsList.getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + j)){
                                                    MilkUp = false;
                                                    System.out.println(MilkUp);
                                                }
                                            }

                                        }
                                    }
                                }







                }
            }
        }
        */
        super.onContainerClosed(player);

    }


}
