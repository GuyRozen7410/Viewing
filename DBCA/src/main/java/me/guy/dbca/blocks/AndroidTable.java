package me.guy.dbca.blocks;


import me.guy.dbca.DBCA;
import me.guy.dbca.ExtendedPlayer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;


public class AndroidTable extends BlockContainer {

    private static AndroidTableTile androidTableTile;
    private EnumFacing direction;

    public AndroidTable() {
        super(TableMaterial.table);



    }



    @Override
    protected String getTextureName() {
        return "dbca:models/ModificationTexture.png";
    }

    @Override
    public Block setBlockTextureName(String p_149658_1_) {
        return super.setBlockTextureName("dbca:models/ModificationTexture.png");
    }
    /*
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        // Set the block's bounding box based on its current state
        //setBlockBounds(0, 0, 0, 2, 3, 2);
        setBlockBounds(0, 0, 0, 0, 0, 0);
    }



    @Override
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
       // p_149743_5_.setBounds(0, 0, 0, 2, 3, 2);
        p_149743_5_.setBounds(0, 0, 0, 0, 0, 0);

        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_,p_149743_6_,p_149743_7_);
    }
    */
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        androidTableTile = new AndroidTableTile();
        return androidTableTile;
    }

    @Override
    public int getLightOpacity() {
        return 0;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        // Call the superclass method first

        super.onBlockPlacedBy(world, x, y, z, entity, stack);

        // Check if the placed block is your custom block

        if (world.getBlock(x, y, z) == this) {
            // Get the ModifactionTableRender instance

            if (entity instanceof  EntityPlayer){
                if (entity.rotationYaw < 0){
                    entity.rotationYaw += 360;
                }
                int facing = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
                switch (facing){
                    case 0:
                        world.setBlockMetadataWithNotify(x,y,z,2,2);
                        break;

                    case 1:
                        world.setBlockMetadataWithNotify(x,y,z,5,2);
                        break;
                    case 2:
                        world.setBlockMetadataWithNotify(x,y,z,3,2);
                        break;

                    case 3:
                        world.setBlockMetadataWithNotify(x,y,z,4,2);
                        break;

                }
            }

            // Place other blocks next to it
            if (world.isAirBlock(x, y + 1, z )){
                world.setBlock(x, y+1,z, DBCA.AirTable);
            }


            /*
            if (world.isAirBlock(x + 1, y, z)) {
                world.setBlock(x + 1, y, z, tableair);
            }
            if (world.isAirBlock(x, y + 1, z)) {
                world.setBlock(x, y + 1, z, tableair);
            }
            if (world.isAirBlock(x, y, z + 1)) {
                world.setBlock(x, y, z + 1, tableair);
            }

            if (world.isAirBlock(x, y + 2, z)) {
                world.setBlock(x, y + 2, z, tableair);
            }
            if (world.isAirBlock(x, y, z - 1)) {
                world.setBlock(x, y, z - 1, tableair);
            }
            if (world.isAirBlock(x + 1, y, z - 1)) {
                world.setBlock(x + 1, y, z - 1, tableair);
            }
            */
            // Add more block placements as needed
        }
    }




    /*
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
        //return AxisAlignedBB.getBoundingBox(0, 0, 0, 2, 3, 2);
        return AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0);
    }
    */
    @Override
    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack p_149642_5_) {
        super.dropBlockAsItem(world, x, y, z, new ItemStack(DBCA.androidTable));

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {

        AndroidTableTile tileEntity = (AndroidTableTile) world.getTileEntity(x, y, z);
        if (tileEntity != null && tileEntity.getBlockType() == DBCA.androidTable){
            if (ExtendedPlayer.PlayerData.getInventory()[2] == null){
                for (int i = 0; i < tileEntity.getSizeInventory(); i++) {
                    ItemStack itemStack = tileEntity.getStackInSlot(i);
                    if (itemStack != null && itemStack.stackSize > 0) {
                        // Drop the item into the world
                        float offsetX = world.rand.nextFloat() * 0.8F + 0.1F;
                        float offsetY = world.rand.nextFloat() * 0.8F + 0.1F;
                        float offsetZ = world.rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world, x + offsetX, y + offsetY, z + offsetZ, itemStack.copy());
                        world.spawnEntityInWorld(entityItem);
                        itemStack.stackSize = 0;
                    }
                }

            }
        }


        world.setBlockToAir(x,y + 1, z);

        /*
        if (world.getBlock(x + 1, y,z) != this){
            if (world.getBlock(x + 1, y, z) == DBCA.AirTable){
                world.setBlockToAir(x + 1, y, z);
            }

        }else{
            count++;
            world.setBlockToAir(x + 1, y, z);
        }


        if (world.getBlock(x,y + 1, z) != this){
            if (world.getBlock(x, y + 1, z) == DBCA.AirTable){
                world.setBlockToAir(x, y + 1 , z);
            }

        }else{
            count++;
            world.setBlockToAir(x, y + 1 , z);
        }


        if (world.getBlock(x,y,z + 1) != this){
            if (world.getBlock(x,y,z + 1) == DBCA.AirTable){
                world.setBlockToAir(x, y, z + 1);
            }

        }else{
            count++;
            world.setBlockToAir(x, y, z + 1);
        }


        if (world.getBlock(x + 1, y, z - 1) != this){
            if (world.getBlock(x +1, y, z -1) == DBCA.AirTable){
                world.setBlockToAir(x + 1, y, z - 1);
            }

        }else{
            count++;
            world.setBlockToAir(x + 1, y, z - 1);
        }


        if (world.getBlock(x,y + 2, z) != this){
            if (world.getBlock(x,y + 2, z) == DBCA.AirTable){
                world.setBlockToAir(x, y + 2, z);
            }

        }else{
            count++;
            world.setBlockToAir(x, y + 2, z);
        }


        if (world.getBlock(x,y,z - 1) != this){
            if (world.getBlock(x,y,z - 1) == DBCA.AirTable){
                world.setBlockToAir(x, y, z - 1);
            }

        }else {
            count++;
            world.setBlockToAir(x, y, z - 1);
        }

        */
        super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
        //count = 1;

    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return 10000;
    }

    /*
                @Override
                public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,int x, int y, int z) {
                    //return AxisAlignedBB.getBoundingBox(x,y, z, x + 2, y + 3, z + 2);
                    return AxisAlignedBB.getBoundingBox(x,y, z, x , y , z );
                }
                */
    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

            //player.setPosition(x + 0.25, y, z + 0.5);
            // This code only runs on the server side
            // Load player data into the tile entity
            AndroidTableTile androidTableTile = (AndroidTableTile) world.getTileEntity(x, y, z);

            ExtendedPlayer.get(player, androidTableTile).loadNBTData(player.getEntityData());

            // Indicate that the activation was successful


            // This code only runs on the client side
            // Open the GUI for the player
            player.openGui(DBCA.instance, 0, world, x, y, z);
            return true; // Return true to indicate successful handling of block activation

            // This code only runs on the server side



    }


}
