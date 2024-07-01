package me.guy.dbca.blocks;


import JinRyuu.DragonBC.common.Npcs.EntityDragon;
import JinRyuu.DragonBC.common.Render.DragonBlock01Block;

import me.guy.dbca.render.DragonEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.init.Blocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Random;


public class DragonBlock01Custom extends DragonBlock01Block {

    @Override
    public void breakBlock(World world, int x, int y, int z, Block i, int j) {
        super.breakBlock(world, x, y, z, i, j);
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return super.renderAsNormalBlock();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return super.hasTileEntity(metadata);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return super.getCollisionBoundingBoxFromPool(world, i, j, k);
    }

    @Override
    public boolean isOpaqueCube() {
        return super.isOpaqueCube();
    }

    @Override
    public int getRenderType() {
        return super.getRenderType();
    }

    @Override
    public int quanityDropped(Random random) {
        return super.quanityDropped(random);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return super.createNewTileEntity(p_149915_1_, p_149915_2_);
    }

    @Override
    public int tickRate() {
        return super.tickRate();
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return super.getPickBlock(target, world, x, y, z);
    }

    @Override
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return super.getItem(p_149694_1_, p_149694_2_, p_149694_3_, p_149694_4_);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return super.getItemDropped(p_149650_1_, p_149650_2_, p_149650_3_);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity) {
        super.onEntityCollidedWithBlock(par1World, par2, par3, par4, entity);
    }

    @Override
    public String getTextureFile() {
        return super.getTextureFile();
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        super.updateTick(par1World, par2, par3, par4, par5Random);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int i, float f, float g, float t) {
        TileEntity tile_entity = par1World.getTileEntity(par2, par3, par4);
        if (tile_entity != null && !player.isSneaking()) {
            if (!par1World.isRemote) {
                DragonEntity Dragon;
                if (par1World.getBlock(par2 + 1, par3, par4) == this) {
                    if (par1World.getBlock(par2 + 1, par3, par4 + 1) == this && par1World.getBlock(par2 + 1, par3, par4 - 1) == this && par1World.getBlock(par2 - 1, par3, par4) == this && par1World.getBlock(par2 - 1, par3, par4 - 1) == this && par1World.getBlock(par2 - 1, par3, par4 + 1) == this) {
                        if (!par1World.isRemote) {
                            Dragon = new DragonEntity(par1World);
                            Dragon.setLocationAndAngles((double)par2, (double)par3, (double)par4, f, 0.0F);
                            par1World.spawnEntityInWorld(Dragon);
                            par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "jinryuudragonbc:dragon.makeone", 1.0F, 1.0F);
                        }

                        par1World.setBlock(par2 + 1, par3, par4, Blocks.air);
                        par1World.setBlock(par2 + 1, par3, par4 + 1, Blocks.air);
                        par1World.setBlock(par2 + 1, par3, par4 - 1, Blocks.air);
                        par1World.setBlock(par2 - 1, par3, par4, Blocks.air);
                        par1World.setBlock(par2 - 1, par3, par4 - 1, Blocks.air);
                        par1World.setBlock(par2 - 1, par3, par4 + 1, Blocks.air);
                        par1World.setBlock(par2, par3, par4, Blocks.air);
                    }
                } else if (par1World.getBlock(par2, par3, par4 + 1) == this && par1World.getBlock(par2 + 1, par3, par4 + 1) == this && par1World.getBlock(par2 + 1, par3, par4 - 1) == this && par1World.getBlock(par2, par3, par4 - 1) == this && par1World.getBlock(par2 - 1, par3, par4 - 1) == this && par1World.getBlock(par2 - 1, par3, par4 + 1) == this) {
                    if (!par1World.isRemote) {
                        Dragon = new DragonEntity(par1World);
                        Dragon.setLocationAndAngles((double)par2, (double)par3, (double)par4, f, 0.0F);
                        par1World.spawnEntityInWorld(Dragon);
                        par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "jinryuudragonbc:dragon.makeone", 1.0F, 1.0F);
                    }

                    par1World.setBlock(par2, par3, par4 + 1, Blocks.air);
                    par1World.setBlock(par2 + 1, par3, par4 + 1, Blocks.air);
                    par1World.setBlock(par2 + 1, par3, par4 - 1, Blocks.air);
                    par1World.setBlock(par2, par3, par4 - 1, Blocks.air);
                    par1World.setBlock(par2 - 1, par3, par4 - 1, Blocks.air);
                    par1World.setBlock(par2 - 1, par3, par4 + 1, Blocks.air);
                    par1World.setBlock(par2, par3, par4, Blocks.air);
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
