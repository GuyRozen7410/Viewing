package me.guy.dbca.proxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import me.guy.dbca.DBCA;
import me.guy.dbca.events.ExtendedPlayerEvents;
import me.guy.dbca.gui.AndroidGuiHandler;
import me.guy.dbca.particles.BloodParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy extends CommonProxy{

    @Mod.EventHandler
    public void preInit(){
        super.preInit();
    }
    @Mod.EventHandler
    public void Init(){

        super.Init();

    }

    @Mod.EventHandler
    public void postInit(){
        super.postInit();
    }

    @Override
    public void spawnParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {

    }


    @Override
    public void registerKeyBindings() {

    }

    @Override
    public void registerEntity() {

    }




}
