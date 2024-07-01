package me.guy.dbca.network;



import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import me.guy.dbca.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SyncPlayerPropsMessage implements IMessage {
    private NBTTagCompound data;
    private int playerid;

    public SyncPlayerPropsMessage() {

    }

    public SyncPlayerPropsMessage(EntityPlayer player, int playerid) {
        this.data = new NBTTagCompound();
        this.playerid = playerid;
        //ExtendedPlayer.get(player, null).saveNBTData(this.data);
    }

    public void fromBytes(ByteBuf buffer) {
        this.data = ByteBufUtils.readTag(buffer);
        this.playerid = buffer.readInt();
    }

    public void toBytes(ByteBuf buffer) {
        ByteBufUtils.writeTag(buffer, this.data);
        buffer.writeInt(playerid);
    }
    private EntityPlayer getPlayer(World world){
        return (EntityPlayer) world.getEntityByID(playerid);
    }
    public static class Handler implements IMessageHandler<SyncPlayerPropsMessage, IMessage> {
        public Handler() {
        }
        //@SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(SyncPlayerPropsMessage message, MessageContext ctx) {


            //ExtendedPlayer.get(message.getPlayer(ctx.getServerHandler().playerEntity.worldObj), null).loadNBTData(message.getPlayer(ctx.getServerHandler().playerEntity.worldObj).getEntityData());
            return null;
        }
    }
}