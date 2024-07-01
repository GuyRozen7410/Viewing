package me.guy.dbca.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import me.guy.dbca.DBCA;

import me.guy.dbca.gui.PartsContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.lwjgl.Sys;


public class PacketUpdateNBT implements IMessage {

    // The new NBT data
    private NBTTagCompound nbtData;
    private String ItemName;
    private ItemStack itemStack;

    private int uid;

    // Constructor
    public PacketUpdateNBT(NBTTagCompound nbtData, String itemName, ItemStack itemStack, int uid) {
        this.nbtData = nbtData;
        this.ItemName = itemName;

        this.itemStack = itemStack;
        this.uid = uid;
    }

    // Empty constructor required for the Forge message system
    public PacketUpdateNBT() {}

    // Method to handle data reading and writing
    @Override
    public void fromBytes(ByteBuf buf) {
        nbtData = ByteBufUtils.readTag(buf);
        ItemName = ByteBufUtils.readUTF8String(buf);
        itemStack = ByteBufUtils.readItemStack(buf);
        uid = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, nbtData);
        ByteBufUtils.writeUTF8String(buf, ItemName);
        ByteBufUtils.writeItemStack(buf, itemStack);
        buf.writeInt(uid);
    }

    // Getter for the NBT data
    public NBTTagCompound getNBTData() {
        return nbtData;
    }




    public ItemStack getItemStack1(){
        return itemStack;
    }

    public int getUid(){
        return uid;
    }

    public static class PacketHandlerUpdateNBT implements IMessageHandler<PacketUpdateNBT, IMessage> {


        private static ItemStack itemS;
        @Override
        public IMessage onMessage(PacketUpdateNBT message, MessageContext ctx) {
            // Get the player from the context
            EntityPlayer player = ctx.getServerHandler().playerEntity;



                    // Do something with the NBT data
            //for (int i = 0; i < player.inventory.getSizeInventory(); i++){

            // Get the item from the player's inventory based on the unique ID
            for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++) {
                ItemStack stack = player.inventory.getStackInSlot(slot);
                if (stack != null && PartsContainer.getUniqueID(stack) == message.getUid()) {
                    // Update the NBT data for the found item
                    NBTTagCompound compound = message.getNBTData();
                    stack.setTagCompound(compound);
                    System.out.println(compound);
                    // Mark the player's inventory as dirty so the changes get saved
                    player.inventory.markDirty();
                    return null; // Return null since this is a one-way message
                }
            }

            return null;
//                        for (int j = 0; j < player.inventory.getSizeInventory(); j++) {
//                            if (player.inventory.getStackInSlot(j) != null) {
//                                if (message.getNBTData() != null){
//                                    if (message.getNBTData().hasKey("Effects")) {
//                                        System.out.println(message.getUid());
//                                        if (player.inventory.getStackInSlot(j).getItem() == message.getItemStack1().getItem() && PartsContainer.getUniqueID(player.inventory.getStackInSlot(j)) == message.getUid()) {
//                                            System.out.println("1111");
//                                            for (int i = 0; i < message.getNBTData().getTagList("Effects", 10).tagCount(); i++) {
//                                                if (player.inventory.getStackInSlot(j).getItem() == message.getItemStack1().getItem()) {
//                                                    System.out.println("1111222222");
//                                                    if (player.inventory.getStackInSlot(j).hasTagCompound()) {
//                                                        if (player.inventory.getStackInSlot(j).getTagCompound().hasKey("Effects")){
////                                                            for (int s = 0; s < 9; s++){
////                                                                if (message.getNBTData().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + s)){
////                                                                    System.out.println("111144444444444444");
////                                                                    if (!player.inventory.getStackInSlot(j).getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + s)) {
////                                                                        System.out.println("11114555555555555555555555");
////
////                                                                        NBTTagCompound compound = message.getNBTData();
////                                                                        System.out.println(compound);
////                                                                        player.inventory.getStackInSlot(j).setTagCompound(compound);
////
////                                                                        // Mark the player's inventory as dirty so the changes get saved
////                                                                        player.inventory.markDirty();
////                                                                        itemS = player.inventory.getStackInSlot(j);
////                                                                        System.out.println("!!!!!!!!!!!!!");
////                                                                        return null;
////
////                                                                    }
////
////
////                                                                }else{
////
////                                                                    if (player.inventory.getStackInSlot(j).getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + s)) {
////
////                                                                        player.inventory.getStackInSlot(j).getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).setBoolean(Items.milk_bucket.getUnlocalizedName() + s, false);
////
////                                                                        // Mark the player's inventory as dirty so the changes get saved
////                                                                        player.inventory.markDirty();
////                                                                        itemS = player.inventory.getStackInSlot(j);
////                                                                        System.out.println("!!!!!!!!!!!!!");
////                                                                        return null;
////                                                                    }
////
////
////                                                                }
////                                                            }
//
//                                                        }else{
//                                                            System.out.println("11114555555555555555555555");
//
//                                                            NBTTagCompound compound = message.getNBTData();
//                                                            System.out.println(compound);
//                                                            player.inventory.getStackInSlot(j).setTagCompound(compound);
//
//                                                            // Mark the player's inventory as dirty so the changes get saved
//                                                            player.inventory.markDirty();
//                                                            itemS = player.inventory.getStackInSlot(j);
//                                                            System.out.println("!!!!!!!!!!!!!");
//                                                            return null;
//                                                        }
//
//
//
//                                                    }
//
//
//                                                }
//
//                                            }
//                                        }
//
//                                    }
//
//                                }
//                            }
//
//                        }














                    /*
                    if (player.inventory.getStackInSlot(i) != null && Objects.equals(message.getItemName(), player.inventory.getStackInSlot(i).getItem().getUnlocalizedName()) && player.inventory.getStackInSlot(i).hasTagCompound()){
                        System.out.println("222222");
                        for (int j = 0; j < player.inventory.getStackInSlot(i).getTagCompound().getTagList("Effects", 10).tagCount(); j++){

                                if (!player.inventory.getStackInSlot(i).getTagCompound().getTagList("Effects", 10).getCompoundTagAt(j).getBoolean(Items.milk_bucket.getUnlocalizedName() + message.getSlotIndex())){
                                    NBTTagCompound nbtData = message.getNBTData();
                                    // Modify the item's NBT tag compound
                                    player.inventory.getStackInSlot(i).setTagCompound(nbtData);

                                    // Mark the player's inventory as dirty so the changes get saved
                                    player.inventory.markDirty();
                                    itemStack =player.inventory.getStackInSlot(i);
                                    itemStack.addEnchantment(DBCA.ENCHANTMENT_GLOWING, 1);
                                    System.out.println(player.inventory.getStackInSlot(i).getTagCompound().getTagList("Effects", 10).getCompoundTagAt(0));
                                    break;
                                }

                        }
                        */





            // Return null since this is a one-way message
            //return null;
        }

//        public static ItemStack getItemStack(){
//            return itemS;
//        }
    }
}
