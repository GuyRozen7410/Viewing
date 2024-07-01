package me.guy.dbca.blocks;


import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class ModifactionTableRender extends TileEntitySpecialRenderer {
    private final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("dbca:models/AndroidModification.obj"));
    private int rotationangle = 0;
    private float translateX = 0;
    private float translateZ = 0;


    public ModifactionTableRender(){

    }

    @Override
    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {

        if (p_147500_1_.getClass() == AndroidTableTile.class){

            if (p_147500_1_.getBlockMetadata() == 2){
                rotationangle = 0; // south
                translateX = 0.5f;
                translateZ = 0f;
            } else if (p_147500_1_.getBlockMetadata() == 3) {
                rotationangle = 180; // north
                translateZ = 1.25f;
                translateX = 0.5f;
            } else if (p_147500_1_.getBlockMetadata() == 4) {
                rotationangle = 90; //east
                translateZ = 0.5f;
                translateX = 0f;
            }else if(p_147500_1_.getBlockMetadata() == 5){
                rotationangle = -90; //west
                translateX = 0.5f;
                translateZ = 0.5f;
            }

            GL11.glPushMatrix();
            //GL11.glRotatef(180f, 0f, 0f, 1f); // Rotate the block so that it is upright

            GL11.glTranslated(p_147500_2_  + translateX, p_147500_4_ + 1.7, p_147500_6_ + translateZ);
            GL11.glRotatef(rotationangle, 0, 1, 0);
            GL11.glScalef(4f, 3.25f, 3.8f); // Scale the block to the correct size


            this.bindTexture(new ResourceLocation("dbca:models/androidchambertextures.png"));

            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);

            // GL11.glPushMatrix();
            model.renderAll(); // Render the block
            //GL11.glPopMatrix();
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }






    }






}
