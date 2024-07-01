package me.guy.dbca.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.DBCA;
import me.guy.dbca.ExtendedPlayer;


import me.guy.dbca.particles.BloodParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;


public class ExtendedPlayerEvents {




    @SubscribeEvent
    public void onEntityConstruct(EntityEvent.EntityConstructing event){

        if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity, null) == null) {
            ExtendedPlayer.register((EntityPlayer) event.entity);

        }

    }


    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
            if (event.entity != null && event.source.getEntity() != null){
                if (event.source.getEntity() instanceof EntityPlayer && !event.entityLiving.isPotionActive(DBCA.bleedEffect.id)){
                    event.entityLiving.addPotionEffect(new PotionEffect(DBCA.bleedEffect.id, 600, 1));
                }
            }



    }




    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer) {


            EntityPlayer player = (EntityPlayer) event.entity;

            ExtendedPlayer.PlayerData extendedPlayer = ExtendedPlayer.get(player, null);
            if (extendedPlayer != null) {
                if (!event.entity.worldObj.isRemote){
                    ExtendedPlayer.setInventory();
                    ExtendedPlayer.nullInv();
                }

                extendedPlayer.loadNBTData(player.getEntityData());
            }

        }

    }
    private static NBTTagCompound playerData;
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event){

        if (event.entity instanceof  EntityPlayer){
            EntityPlayer player = (EntityPlayer) event.entity;
            if (player.getEntityData().getTag("DBCAP") != null){
                playerData = new NBTTagCompound();
                playerData.setTag("DBCAP", player.getEntityData().getTag("DBCAP"));
                playerData.setBoolean("DBCAP_Core" + player.worldObj.getWorldInfo().getWorldName(),player.getEntityData().getBoolean("DBCAP_Core" + player.worldObj.getWorldInfo().getWorldName()));
                //player.getEntityData().setTag("DBCAPR", playerData);
                System.out.println(playerData);
            }

        }
    }

    public static NBTTagCompound getPlayerData(){
        return playerData;
    }



/*

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP){
            PacketHandler.sendTo(new PlayerSyncMsg(event.player), (EntityPlayerMP) event.player);
        }

    }



    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event){
        System.out.println("dsafk;hasdflkjasdlf;kasj");
        if (ExtendedPlayer.get(event.player) != null){
            ExtendedPlayer.get(event.player).saveNBTData(event.player.getEntityData());
        }





    }
*/

}
