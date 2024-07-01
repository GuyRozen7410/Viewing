package me.guy.dbca.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import me.guy.dbca.ExtendedPlayer;
import net.minecraft.client.Minecraft;

public class PartsDisplayMsg implements IMessage {
    private boolean core;
    private boolean head;
    private boolean leftarm;
    private boolean rightarm;
    public PartsDisplayMsg(){

    }

    public PartsDisplayMsg(boolean core, boolean head, boolean rightarm, boolean leftarm, boolean rightleg, boolean leftleg){
        this.core = core;
        this.head = head;
        this. rightarm = rightarm;
        this.leftarm = leftarm;

    }
    @Override
    public void fromBytes(ByteBuf buf) {
        core = buf.readBoolean();
        head = buf.readBoolean();
        rightarm = buf.readBoolean();
        leftarm = buf.readBoolean();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(core);
        buf.writeBoolean(head);
        buf.writeBoolean(rightarm);
        buf.writeBoolean(leftarm);
    }

    public static class PartsMsgHandler implements IMessageHandler<PartsDisplayMsg, IMessage>{

        @Override
        public IMessage onMessage(PartsDisplayMsg message, MessageContext ctx) {
            ExtendedPlayer.get(ctx.getServerHandler().playerEntity, null).saveNBTData(ctx.getServerHandler().playerEntity.getEntityData());
            return null;
        }
    }
}
