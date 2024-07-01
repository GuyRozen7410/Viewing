package me.guy.dbca.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import me.guy.dbca.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketHandler {
    private static byte packetId = 0;

    private static byte syncitemsid = 1;

    private static byte syncDisplay = 2;

    private static byte syncNBTItems = 3;

    private static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static final void registerPackets(){
        network.registerMessage(OpenGuiMsgHandler.class, OpenGuiMessage.class, packetId, Side.SERVER);
        network.registerMessage(SyncPlayerPropsMessage.Handler.class, SyncPlayerPropsMessage.class, syncitemsid, Side.CLIENT);
        network.registerMessage(PartsDisplayMsg.PartsMsgHandler.class, PartsDisplayMsg.class, syncDisplay, Side.SERVER);
        network.registerMessage(PacketUpdateNBT.PacketHandlerUpdateNBT.class, PacketUpdateNBT.class, syncNBTItems, Side.SERVER);


    }



    public static final void sendTo(IMessage message, EntityPlayerMP player) {
        network.sendTo(message, player);
    }


    public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
        network.sendToAllAround(message, point);
    }

    public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
        network.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
    }

    public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) {
        sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, player.posY, player.posZ, range);
    }

    public static final void sendToDimension(IMessage message, int dimensionId) {
        network.sendToDimension(message, dimensionId);
    }
    public static final void sendToServer(IMessage message) {
        network.sendToServer(message);
    }
}
