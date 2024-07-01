package me.guy.dbca;


import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import me.guy.dbca.blocks.AndroidTableTile;
import me.guy.dbca.events.EventsHandler;
import me.guy.dbca.gui.PlayerAndroidMenu;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.*;


public class ExtendedPlayer  {
    private static final String PROPERTY_NAME = "DBCAP";
    private static AndroidTableTile customInv = null;

    private static ItemStack[] inventory = new ItemStack[6];

    public static void nullInv(){
        customInv = null;
    }

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(PROPERTY_NAME, new PlayerData(player));

    }

    public static PlayerData get(EntityPlayer player, AndroidTableTile androidTableTile) {

        customInv = androidTableTile;
        if (customInv != null){

            inventory = customInv.getInventory();

        }
        return (PlayerData) player.getExtendedProperties(PROPERTY_NAME);
    }



    public static void setInventory(){
        inventory = new ItemStack[6];

    }



    public static class PlayerData implements IExtendedEntityProperties {


        private static boolean MilkUp;
        private final EntityPlayer player;

        private static int tp = 0;
        private static NBTTagCompound nbt;

        private static boolean getTp = false;
        public PlayerData(EntityPlayer playerI){
            player = playerI;
        }
        @Override
        public void saveNBTData(NBTTagCompound compound) {

            if (customInv != null){

                NBTTagCompound proprties = new NBTTagCompound();
                customInv.writeToNBT(proprties);
                compound.setTag("DBCAP", proprties);
            }

            compound.setBoolean("DBCAP_Core" + player.worldObj.getWorldInfo().getWorldName(), PlayerAndroidMenu.core_display);
            compound.setBoolean("DBCAP_Head" + player.worldObj.getWorldInfo().getWorldName(), PlayerAndroidMenu.head_display);
            compound.setBoolean("DBCAP_LArm" + player.worldObj.getWorldInfo().getWorldName(), PlayerAndroidMenu.limb_displayLeft);
            compound.setBoolean("DBCAP_RArm" + player.worldObj.getWorldInfo().getWorldName(), PlayerAndroidMenu.limb_displayRight);




        }

        @Override
        public void loadNBTData(NBTTagCompound compound) {


                if (customInv != null){

                    NBTTagCompound props = compound.getCompoundTag("DBCAP");
                    customInv.readFromNBT(props);
                    inventory = customInv.getInventory();
                }else{


                        NBTTagList nbttaglist = compound.getCompoundTag("DBCAP").getTagList("DBCAINV" + player.worldObj.getWorldInfo().getWorldName(), 10);

                        for (int i = 0; i < nbttaglist.tagCount(); ++i)
                        {
                            NBTTagCompound nbtTagCompound = nbttaglist.getCompoundTagAt(i);
                            byte b0 = nbtTagCompound.getByte("Slot" + player.worldObj.getWorldInfo().getWorldName());

                            if (b0 == 2)
                            {

                                inventory[2] = ItemStack.loadItemStackFromNBT(
                                        nbtTagCompound);


                            }
                            if (b0 == 1){
                                inventory[1] = ItemStack.loadItemStackFromNBT(nbtTagCompound);
                            }
                            if(b0 == 3){
                                inventory[3] = ItemStack.loadItemStackFromNBT(nbtTagCompound);
                            }
                        }




                }
                if (!player.worldObj.isRemote){
                    if (inventory[2] != null && inventory[2].getItem() == DBCA.potatoCore){


                        PlayerAndroidMenu.core_display = compound.getBoolean("DBCAP_Core" + player.worldObj.getWorldInfo().getWorldName());
                    }
                    if (inventory[1] != null && inventory[1].getItem() == DBCA.potatoLimb){


                        PlayerAndroidMenu.limb_displayLeft = compound.getBoolean("DBCAP_LArm" + player.worldObj.getWorldInfo().getWorldName());
                    }
                    if (inventory[3] != null && inventory[3].getItem() == DBCA.potatoLimb){


                        PlayerAndroidMenu.limb_displayRight = compound.getBoolean("DBCAP_RArm" + player.worldObj.getWorldInfo().getWorldName());
                    }
                }



        }

        @Override
        public void init(Entity entity, World world) {

        }





        public static ItemStack[] getInventory(){
            return inventory;
        }


        public static boolean getMilkUp(){
            if (inventory[2] != null){
                if (inventory[2].hasTagCompound() && inventory[2].getTagCompound().getTagList("Effects", 10) != null){
                    for (int i = 0; i < inventory[2].getTagCompound().getTagList("Effects", 10).tagCount(); i++){
                        for (int j = 0; j < 9; j++){
                            if (inventory[2].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + j)){
                                MilkUp = true;
                                return true;
                            }
                            if (i >= inventory[2].getTagCompound().getTagList("Effects", 10).tagCount() - 1 && j == 8){

                                    if (inventory[2].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(Items.milk_bucket.getUnlocalizedName() + j)){
                                        MilkUp = true;
                                        return true;
                                    }else {
                                        MilkUp = false;
                                        return false;
                                    }


                            }

                        }
                    }

                }else{
                    MilkUp = false;
                }
                return MilkUp;
            }
            return false;
        }


        public static Map<String, Byte> getEffects(){
            if (inventory[2] != null){
                String[] Names = new String[16];
                byte[] Amount = new byte[16];
                Map<String,Byte> Effects = new HashMap<>();
                if (inventory[2].hasTagCompound() && inventory[2].getTagCompound().getTagList("Effects", 10) != null) {
                    for (int i = 0; i < inventory[2].getTagCompound().getTagList("Effects", 10).tagCount(); i++) {
                        for (int j = 0; j < 9; j++) {
                            if (inventory[2].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(ItemsDBC.ItemMedMoss.getUnlocalizedName() + j)) {

                                if (Names[0] == null){
                                    Names[0] = "Moss";
                                    Amount[0]++;

                                }else{
                                    Amount[0]++;
                                }


                            }
                            if (inventory[2].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(Item.getItemFromBlock(Blocks.ice).getUnlocalizedName() + j)) {

                                if (Names[1] == null){
                                    Names[1] = "Ice";
                                    Amount[1]++;

                                }else{
                                    Amount[1]++;
                                }


                            }
                            if (inventory[2].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(ItemsDBC.ItemNamekDragonBlock.getUnlocalizedName() + j)) {

                                if (Names[2] == null){
                                    Names[2] = "NDB";
                                    Amount[2]++;

                                }else{
                                    Amount[2]++;
                                }

                                if (EventsHandler.getEntityPlayer() != null && !getTp){
                                    nbt = JRMCoreH.nbt(EventsHandler.getEntityPlayer(), "pres");
                                    tp = nbt.getInteger("jrmcTpint");
                                    System.out.println(tp);
                                    getTp = true;
                                }



                            }
                            if (inventory[2].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(ItemsDBC.ItemPP.getUnlocalizedName() + j)) {

                                if (Names[3] == null){
                                    Names[3] = "PP";
                                    Amount[3]++;

                                }else{
                                    Amount[3]++;
                                }
                                System.out.println(Names[3]);

                            }
                            if (i >= inventory[2].getTagCompound().getTagList("Effects", 10).tagCount() - 1 && j == 8) {

//                                if (inventory[2].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(Item.getItemFromBlock(Blocks.ice).getUnlocalizedName() + j)) {
//
//                                    if (Names[1] == null){
//                                        Names[1] = "Ice";
//                                        Amount[1]++;
//
//                                    }else{
//                                        Amount[1]++;
//                                    }
//
//
//                                }
                                Effects.put(Names[0], Amount[0]);
                                Effects.put(Names[1], Amount[1]);
                                Effects.put(Names[2], Amount[2]);
                                Effects.put(Names[3], Amount[3]);

                            }


                        }
                    }
                }
                if (inventory[1] != null){

                    if (inventory[1].hasTagCompound() && inventory[1].getTagCompound().getTagList("Effects", 10) != null) {
                        for (int i = 0; i < inventory[1].getTagCompound().getTagList("Effects", 10).tagCount(); i++) {
                            for (int j = 0; j < 9; j++) {
                                if (inventory[1].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(ItemsDBC.ItemPP.getUnlocalizedName() + j)) {

                                    if (Names[4] == null){
                                        Names[4] = "PP";
                                        Amount[4]++;

                                    }else{
                                        Amount[4]++;
                                    }


                                }
                                if (i >= inventory[1].getTagCompound().getTagList("Effects", 10).tagCount() - 1 && j == 8) {

//                                if (inventory[2].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(Item.getItemFromBlock(Blocks.ice).getUnlocalizedName() + j)) {
//
//                                    if (Names[1] == null){
//                                        Names[1] = "Ice";
//                                        Amount[1]++;
//
//                                    }else{
//                                        Amount[1]++;
//                                    }
//
//
//                                }
                                    Effects.put(Names[4], Amount[4]);


                                }


                            }
                        }
                    }
                    if (inventory[3] != null) {

                        if (inventory[3].hasTagCompound() && inventory[3].getTagCompound().getTagList("Effects", 10) != null) {
                            for (int i = 0; i < inventory[3].getTagCompound().getTagList("Effects", 10).tagCount(); i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (inventory[3].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(ItemsDBC.ItemPP.getUnlocalizedName() + j)) {

                                        if (Names[4] == null) {
                                            Names[4] = "PP";
                                            Amount[4]++;

                                        } else {
                                            Amount[4]++;
                                        }


                                    }
                                    if (i >= inventory[3].getTagCompound().getTagList("Effects", 10).tagCount() - 1 && j == 8) {

//                                if (inventory[2].getTagCompound().getTagList("Effects", 10).getCompoundTagAt(i).getBoolean(Item.getItemFromBlock(Blocks.ice).getUnlocalizedName() + j)) {
//
//                                    if (Names[1] == null){
//                                        Names[1] = "Ice";
//                                        Amount[1]++;
//
//                                    }else{
//                                        Amount[1]++;
//                                    }
//
//
//                                }
                                        Effects.put(Names[4], Amount[4]);


                                    }


                                }
                            }
                        }
                    }
                }
                return Effects;

            }

            return null;
        }

        public static int getTp(){
            return tp;
        }

        public static void setGetTp(){
            getTp = false;
        }
    }
}
