package me.guy.dbca.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.DBCA;
import net.minecraft.entity.player.EntityPlayer;


public abstract class AbstractMessageHandler<T extends IMessage> implements IMessageHandler<T, IMessage>{



    public abstract IMessage handlMessage(EntityPlayer player, T message, MessageContext ctx);



    @Override
    public IMessage onMessage(T message, MessageContext ctx) {

        return handlMessage(ctx.getServerHandler().playerEntity, message, ctx);



    }
}

