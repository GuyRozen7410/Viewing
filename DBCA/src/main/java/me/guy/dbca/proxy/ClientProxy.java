package me.guy.dbca.proxy;


import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.DBCA;
import me.guy.dbca.blocks.AndroidTableTile;
import me.guy.dbca.blocks.ModifactionTableRender;
import me.guy.dbca.blocks.ModificationPartBuilderTile;
import me.guy.dbca.blocks.ModificationPartRender;
import me.guy.dbca.items.AndroidTableItemRender;
import me.guy.dbca.items.PartModificationItemRender;
import me.guy.dbca.keyhandler.KeyHandler;
import me.guy.dbca.particles.BloodParticle;
import me.guy.dbca.potions.BleedEffect;
import me.guy.dbca.render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return (ctx.side.isClient() ? ctx.getServerHandler().playerEntity : super.getPlayerEntity(ctx));
    }

    @Mod.EventHandler
    public void preInit(){

        super.preInit();
        //MinecraftForge.EVENT_BUS.register(new testplayerrender());


    }
    @Mod.EventHandler
    public void Init(){

        super.Init();

        //RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new testplayerrender());

        
    }

    @Mod.EventHandler
    public void postInit(){
        super.postInit();
    }

    @Override
    public void registerKeyBindings() {
        KeyHandler.init();

    }

    @Override
    public void registerEntity(){
        RenderingRegistry.registerEntityRenderingHandler(DragonEntity.class, new DragonRenderer(new DragonModel(), 0.5f));
        //RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class,  new RenderPartsJBRA());
        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderPartsJBRA());
        ClientRegistry.bindTileEntitySpecialRenderer(AndroidTableTile.class, new ModifactionTableRender());
        ClientRegistry.bindTileEntitySpecialRenderer(ModificationPartBuilderTile.class, new ModificationPartRender());
        Item Mtable = Item.getItemFromBlock(DBCA.androidTable);
        MinecraftForgeClient.registerItemRenderer(Mtable, new AndroidTableItemRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DBCA.partsTable), new PartModificationItemRender());

    }

    @Override
    public void spawnParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        if (!world.isRemote){
            Minecraft.getMinecraft().effectRenderer.addEffect(new BloodParticle(world, x, y, z, motionX, motionY, motionZ));
        }

    }


}
