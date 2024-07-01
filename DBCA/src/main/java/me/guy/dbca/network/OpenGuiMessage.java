package me.guy.dbca.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import me.guy.dbca.blocks.AndroidTableTile;
import net.minecraft.world.World;
import scala.tools.nsc.transform.patmat.Logic;


public class OpenGuiMessage implements IMessage {

    private int guiID;


    public OpenGuiMessage(){}
    public OpenGuiMessage(int id){
        this.guiID = id;

    }
    @Override
    public void fromBytes(ByteBuf buf) {
        guiID = buf.readInt();



    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(guiID);


    }

    public int getGuiID(){
        return guiID;
    }




}
