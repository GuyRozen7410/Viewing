package me.guy.dbca.blocks;

import cpw.mods.fml.common.Mod;
import me.guy.dbca.DBCA;
import me.guy.dbca.ExtendedPlayer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class ModificationPartBuilder extends BlockContainer {

    public ModificationPartBuilder() {
        super(TableMaterial.table);
        this.setBlockBounds(0,0,0, 1, 1, 1);
    }

    @Override
    protected String getTextureName() {
        return "dbca:models/part_builder/PartBuilderTextures.png";
    }

    @Override
    public Block setBlockTextureName(String p_149658_1_) {
        return super.setBlockTextureName("dbca:models/part_builder/PartBuilderTextures.png");
    }


    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return 10000;
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {

        return new ModificationPartBuilderTile();
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

            if (entity instanceof EntityPlayer){
                if (entity.rotationYaw < 0){
                    entity.rotationYaw += 360;
                }
                int facing = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
                switch (facing){
                    case 0:
                        world.setBlockMetadataWithNotify(x,y,z,2,2);
                        if (world.isAirBlock(x - 1, y, z)){
                            world.setBlock(x - 1, y, z, DBCA.partsTableAir);
                        }

                        break;

                    case 1:
                        world.setBlockMetadataWithNotify(x,y,z,5,2);
                        if (world.isAirBlock(x, y,z- 1)){
                            world.setBlock(x, y, z - 1, DBCA.partsTableAir);
                        }

                        break;
                    case 2:
                        world.setBlockMetadataWithNotify(x,y,z,3,2);
                        if (world.isAirBlock(x - 1, y, z)){
                            world.setBlock(x - 1, y, z, DBCA.partsTableAir);
                        }
                        break;

                    case 3:
                        world.setBlockMetadataWithNotify(x,y,z,4,2);
                        if (world.isAirBlock(x, y,z- 1)){
                            world.setBlock(x, y, z - 1, DBCA.partsTableAir);
                        }
                        break;

                }
            }

            // Place other blocks next to it




        }
    }







    @Override
    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack p_149642_5_) {
        super.dropBlockAsItem(world, x, y, z, new ItemStack(DBCA.partsTable));

    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {


        if (world.getBlock(x - 1,y,z).getClass() == DBCA.partsTableAir.getClass()){
            world.setBlockToAir(x - 1, y, z);
        }
        if (world.getBlock(x ,y,z - 1).getClass() == DBCA.partsTableAir.getClass()){
            world.setBlockToAir(x, y, z - 1);
        }
        if (world.getBlock(x + 1 ,y,z).getClass() == DBCA.partsTableAir.getClass()){
            world.setBlockToAir(x + 1, y, z);
        }
        if (world.getBlock(x ,y,z + 1).getClass() == DBCA.partsTableAir.getClass()){
            world.setBlockToAir(x , y, z + 1);
        }

        ModificationPartBuilderTile tileEntity = (ModificationPartBuilderTile)world.getTileEntity(x, y, z);
        if (tileEntity.getBlockType() == DBCA.partsTable || tileEntity.getBlockType() == DBCA.partsTableAir){
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
        super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);


    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }








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
        return true;
    }



    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {


        // This code only runs on the server side
        // Load player data into the tile entity


        ExtendedPlayer.get(player, null).loadNBTData(player.getEntityData());
        player.openGui(DBCA.instance, 1, world, x, y, z);

        return true; // Return true to indicate successful handling of block activation

        // This code only runs on the server side



    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB boundingBox, List list, Entity entity) {
        AxisAlignedBB axisalignedbb = this.getCollisionBoundingBoxFromPool(world, x, y, z);
        if (axisalignedbb != null && boundingBox.intersectsWith(axisalignedbb)) {
            list.add(axisalignedbb);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox(x + f, y, z + f, x + 1 - f, y + 1 - f, z + 1 - f);
    }

}
