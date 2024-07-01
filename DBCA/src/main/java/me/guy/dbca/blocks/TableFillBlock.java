package me.guy.dbca.blocks;

import me.guy.dbca.DBCA;
import me.guy.dbca.ExtendedPlayer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class TableFillBlock extends AndroidTable {

    public TableFillBlock(){
        this.setCreativeTab(null);
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return 10000;
    }

    @Override
    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack p_149642_5_) {
        super.dropBlockAsItem(world, x, y, z, new ItemStack(DBCA.androidTable));
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {


        world.setBlockToAir(x, y -1, z);

        super.breakBlock(world,x,y,z, p_149749_5_, p_149749_6_);

    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TableTileFill();
    }
}
