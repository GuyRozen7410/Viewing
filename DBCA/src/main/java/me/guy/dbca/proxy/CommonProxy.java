package me.guy.dbca.proxy;


import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;

import me.guy.dbca.blocks.ModificationPartBuilderTile;
import me.guy.dbca.blocks.PartsTableFillTile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public abstract class CommonProxy implements IProxy {
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }

    public void preInit(){

    }

    public void Init(){

    }

    public void postInit(){

    }

    public void registerTiles(){
        GameRegistry.registerTileEntity(ModificationPartBuilderTile.class, "modification_part_builder_tile");
        GameRegistry.registerTileEntity(PartsTableFillTile.class, "parts_table_tile");
    }


    public abstract void spawnParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ);
}
