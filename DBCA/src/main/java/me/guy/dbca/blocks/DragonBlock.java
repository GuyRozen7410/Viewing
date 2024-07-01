package me.guy.dbca.blocks;

import JinRyuu.DragonBC.common.Blocks.BlockDragonBall;
import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Blocks.DBCMaterial;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class DragonBlock extends Block {
    protected int maxStackSize = 7;

    public DragonBlock setMaxStackSize(int par1) {
        this.maxStackSize = par1;
        return this;
    }

    public int getItemStackLimit() {
        return this.maxStackSize;
    }

    public DragonBlock() {
        super(DBCMaterial.dragonblock);
        this.setTickRandomly(true);
        this.setHardness(0.1F);
        float var4 = 0.2F;
        this.setBlockBounds(0.5F - var4, 0.2F - var4, 0.5F - var4, 0.5F + var4, 0.2F + var4, 0.5F + var4);
        this.setCreativeTab(mod_DragonBC.DragonBlockC);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("jinryuudragonbc:" + this.getUnlocalizedName());
    }
    /*
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!par1World.isRemote) {
            if (par1World.getBlock(par2 + 1, par3, par4) == this) {
                if (par1World.getBlock(par2 + 1, par3, par4 + 1) == this && par1World.getBlock(par2 + 1, par3, par4 - 1) == this && par1World.getBlock(par2 - 1, par3, par4) == this && par1World.getBlock(par2 - 1, par3, par4 - 1) == this && par1World.getBlock(par2 - 1, par3, par4 + 1) == this) {
                    par1World.setBlock(par2, par3, par4, BlocksDBC.BlockDragonBallStone);
                }
            } else if (par1World.getBlock(par2, par3, par4 + 1) == this && par1World.getBlock(par2 + 1, par3, par4 + 1) == this && par1World.getBlock(par2 + 1, par3, par4 - 1) == this && par1World.getBlock(par2, par3, par4 - 1) == this && par1World.getBlock(par2 - 1, par3, par4 - 1) == this && par1World.getBlock(par2 - 1, par3, par4 + 1) == this) {
                par1World.setBlock(par2, par3, par4, BlocksDBC.BlockDragonBallStone);
            }
        }

    }
*/
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 0;
    }

    public boolean isOpaqueCube() {
        return false;
    }
}
