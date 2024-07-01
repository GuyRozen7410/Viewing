package me.guy.dbca.gui;

import cpw.mods.fml.common.network.IGuiHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.ExtendedPlayer;

import me.guy.dbca.blocks.AndroidTableTile;
import me.guy.dbca.blocks.ModificationPartBuilderTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;



public class AndroidGuiHandler implements IGuiHandler {



    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == 0) {

            AndroidTableTile tileEntity = (AndroidTableTile) world.getTileEntity(x, y, z);
            if (tileEntity != null) {
                return new AndroidContainer(player.inventory, player,tileEntity);
            }
        }
        if (ID == 1){
            ModificationPartBuilderTile tile = (ModificationPartBuilderTile) world.getTileEntity(x,y,z);
            if (tile != null){
                return new PartsContainer(tile, player);
            }
        }
        return null;

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0){

            AndroidTableTile androidTableTile = (AndroidTableTile) world.getTileEntity(x, y, z);
            if (androidTableTile != null){
                return new AndroidGui(player, androidTableTile);
            }

        }
        if (ID == 1){
            ModificationPartBuilderTile tile = (ModificationPartBuilderTile) world.getTileEntity(x,y,z);
            if (tile != null){
                return new PartsContainerGui(tile, player);
            }
        }
        return null;
    }

}
