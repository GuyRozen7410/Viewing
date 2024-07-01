package me.guy.dbca.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.DBCA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class OpenGuiMsgHandler extends AbstractMessageHandler<OpenGuiMessage> {


    @Override
    public IMessage handlMessage(EntityPlayer player, OpenGuiMessage message, MessageContext ctx) {


            player.openGui(DBCA.instance, message.getGuiID(), player.worldObj, player.getPlayerCoordinates().posX, player.getPlayerCoordinates().posY, player.getPlayerCoordinates().posZ);



        return null;
    }

    /*
    @Override
    public IMessage onMessage(OpenGuiMessage message, MessageContext ctx) {
        EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
        int guiID = message.getGuiID();
        playerMP.openGui(DBCA.instance, guiID, ctx.getServerHandler().playerEntity.worldObj,(int) ctx.getServerHandler().playerEntity.posX, (int) ctx.getServerHandler().playerEntity.posY, (int) ctx.getServerHandler().playerEntity.posZ);
        return null;
    }
     */
}
