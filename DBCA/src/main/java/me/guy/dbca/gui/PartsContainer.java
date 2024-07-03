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
    private final Item[] allowedItemsLimb = {ItemsDBC.ItemPP, ItemsDBC.ItemBraveSword, ItemsDBC.ItemKatana, ItemsDBC.ItemZSword};



    public PartsContainer (ModificationPartBuilderTile tile, EntityPlayer Player){

        this.modificationPartBuilderTile = tile;
        this.player = Player;

        this.addSlotToContainer(new Slot(tile, 0, 26, 23) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unhallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }

                }
                if (p_75214_1_ != null) {
                    Unhallowed(p_75214_1_.getItem());
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
                if (p_75214_1_ != null && Unhallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null) {
                    Unhallowed(p_75214_1_.getItem());
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
                if (p_75214_1_ != null && Unhallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null) {
                    Unhallowed(p_75214_1_.getItem());
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
                if (p_75214_1_ != null && Unhallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null) {
                    Unhallowed(p_75214_1_.getItem());
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
                    if (!(p_75141_2_.hasTagCompound() && p_75141_2_.getTagCompound().hasKey("Effects"))) {
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

                                    }
                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemMedMoss){
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();

                                    }
                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == Item.getItemFromBlock(Blocks.ice)){
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();
                                    }
                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemNamekDragonBlock){
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();
                                    }
                                }

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
                                    }
                                    if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemKatana) {
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        compound.setBoolean("Arm", true);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();


                                    }if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemBraveSword) {
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        compound.setBoolean("Arm", true);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();


                                    }if (modificationPartBuilderTile.getInventory()[j].getItem() == ItemsDBC.ItemZSword) {
                                        value.setBoolean(modificationPartBuilderTile.getInventory()[j].getItem().getUnlocalizedName() + j, true);
                                        effectsList.appendTag(value);
                                        compound.setTag("Effects", effectsList);
                                        compound.setBoolean("Arm", true);
                                        p_75141_2_.setTagCompound(compound);
                                        value = new NBTTagCompound();


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
            @Override
            public void onSlotChanged() {
                if (tile.getInventory()[4] != null){
                    LoadItems(tile.getInventory(), getSlotFromItemPartTile(tile.getInventory()[4]));
                    tile.getInventory()[4].setTagCompound(new NBTTagCompound());
                    if (!player.worldObj.isRemote){
                        if (getUniqueID(tile.getInventory()[4]) == -1){
                            setUniqueID(tile.getInventory()[4]);
                        }
                    }
                }
                super.onSlotChanged();
            }
        });
        this.addSlotToContainer(new Slot(tile, 5, 111, 66) {
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                if (p_75214_1_ != null && Unhallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null) {
                    Unhallowed(p_75214_1_.getItem());
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
                if (p_75214_1_ != null && Unhallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null) {
                    Unhallowed(p_75214_1_.getItem());
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
                if (p_75214_1_ != null && Unhallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null) {
                    Unhallowed(p_75214_1_.getItem());
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
                if (p_75214_1_ != null && Unhallowed(p_75214_1_.getItem())){
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        return true;
                    }
                }
                if (p_75214_1_ != null) {
                    Unhallowed(p_75214_1_.getItem());
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

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int slotIndex) {

        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {

            ItemStack stackInSlot = slot.getStack();
            itemstack = stackInSlot.copy();

            int playerInventoryStart = 9; // Start of player's inventory slots
            int playerInventoryEnd = 44; // End of player's inventory slots, including hot bar
            if (slotIndex < playerInventoryStart) {
                if (!mergeItemStack(stackInSlot, playerInventoryStart, playerInventoryEnd, true)) {
                    return null;
                }
                slot.onSlotChange(stackInSlot, itemstack);
            }
            else{ // Transfer from player inventory to custom tile entity
                if (Tiers.getItemInTier(stackInSlot.getItem(), (byte) 1)) {
                    // Adjust the range parameters according to the slots in your custom tile entity
                    if (!mergeItemStack(stackInSlot, 4, 5, false)) {
                        return null;
                    }
                    if (modificationPartBuilderTile.getInventory()[4] != null){
                        LoadItems(modificationPartBuilderTile.getInventory(), getSlotFromItemPartTile(stackInSlot));
                    }
                }else{
                    processInventoryItem(modificationPartBuilderTile, stackInSlot);
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
                        }if (effectsList.getCompoundTagAt(i).getBoolean(ItemsDBC.ItemKatana.getUnlocalizedName() + j)){
                            effects[j] = ItemsDBC.ItemKatana.getUnlocalizedName() + j;
                        }if (effectsList.getCompoundTagAt(i).getBoolean(ItemsDBC.ItemBraveSword.getUnlocalizedName() + j)){
                            effects[j] = ItemsDBC.ItemBraveSword.getUnlocalizedName() + j;
                        }if (effectsList.getCompoundTagAt(i).getBoolean(ItemsDBC.ItemZSword.getUnlocalizedName() + j)){
                            effects[j] = ItemsDBC.ItemZSword.getUnlocalizedName() + j;
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
    private boolean isAllowedItemTypeAndroid(Item item) {
        // Define the allowed item types here
        // You can use any method to determine if the item is allowed
        return item == DBCA.potatoCore || item == DBCA.potatoLimb;
    }

    private void LoadItems(ItemStack[] InventoryItems, String[] Effects){
        if (InventoryItems[4] != null){
            if (Effects != null){
                for (int j = 0; j < 9; j++){
                    for (String namesOfEffect : Effects) {
                        if (InventoryItems[4].getItem() == DBCA.potatoCore){
                            for (Item item : allowedItemsCore){
                                if (namesOfEffect != null && namesOfEffect.contains(item.getUnlocalizedName() + j)) {
                                    modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(namesOfEffect.substring(item.getUnlocalizedName().length(), item.getUnlocalizedName().length() + 1)), new ItemStack(item, 1));
                                }
                            }
                        } else if (InventoryItems[4].getItem() == DBCA.potatoLimb) {
                            for (Item item : allowedItemsLimb){
                                if (namesOfEffect != null && namesOfEffect.contains(item.getUnlocalizedName() + j)) {
                                    modificationPartBuilderTile.setInventorySlotContents(Integer.parseInt(namesOfEffect.substring(item.getUnlocalizedName().length(), item.getUnlocalizedName().length() + 1)), new ItemStack(item, 1));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public ItemStack processInventoryItem(ModificationPartBuilderTile modificationPartBuilderTile, ItemStack stackInSlot) {
        if (modificationPartBuilderTile.getInventory()[4] != null && stackInSlot != null) {
            if (Tiers.getItemInTier(modificationPartBuilderTile.getInventory()[4].getItem(), (byte) 1)) {
                if (modificationPartBuilderTile.getInventory()[4].getItem() == DBCA.potatoCore) {
                    if (this.inventorySlots.get(3) != null && this.inventorySlots.get(5) != null) {
                        boolean foundCoreItem = false;
                        for (Item item : allowedItemsCore) {
                            if (stackInSlot.getItem() == item) {
                                foundCoreItem = true;
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
                        }
                        if (!foundCoreItem) {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else if (modificationPartBuilderTile.getInventory()[4].getItem() == DBCA.potatoLimb) {
                    if (modificationPartBuilderTile.getInventory()[4] != null) {
                        if (this.inventorySlots.get(3) != null && this.inventorySlots.get(5) != null) {
                            boolean foundLimbItem = false;
                            for (Item item : allowedItemsLimb) {
                                if (stackInSlot.getItem() == item) {
                                    foundLimbItem = true;
                                    if (stackInSlot.stackSize > 1) {
                                        stackInSlot.stackSize = 1;
                                    }
                                    if (!mergeItemStack(stackInSlot, 3, 6, false) || mergeItemStack(stackInSlot, 4, 5, false)) {
                                        return null;
                                    }
                                }
                            }
                            if (!foundLimbItem) {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
        return stackInSlot;
    }
    private boolean Unhallowed(Item item){
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
}


