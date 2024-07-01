package me.guy.dbca.blocks;

import me.guy.dbca.DBCA;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PartsTableFill extends ModificationPartBuilder{
    public PartsTableFill(){

    }
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return 10000;
    }
    @Override
    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack p_149642_5_) {
        super.dropBlockAsItem(world, x, y, z, new ItemStack(DBCA.partsTable));
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {


        if (world.getBlock(x - 1, y, z).getClass() == DBCA.partsTable.getClass()){
            world.setBlockToAir(x - 1, y, z);

        }if (world.getBlock(x, y, z - 1).getClass() == DBCA.partsTable.getClass()){
            world.setBlockToAir(x, y, z - 1);
        }if (world.getBlock(x + 1, y, z).getClass() == DBCA.partsTable.getClass()){
            world.setBlockToAir(x + 1, y, z);
        }if (world.getBlock(x, y, z + 1).getClass() == DBCA.partsTable.getClass()){
            world.setBlockToAir(x , y, z + 1);

        }

        super.breakBlock(world,x,y,z, p_149749_5_, p_149749_6_);

    }
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new PartsTableFillTile();
    }
}
