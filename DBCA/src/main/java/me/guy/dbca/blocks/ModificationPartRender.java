package me.guy.dbca.blocks;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class ModificationPartRender extends TileEntitySpecialRenderer {
    private final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("dbca:models/PartModification.obj"));
    private int rotationangle = 0;
    private float transletX = 0;
    private float transletZ = 0;


    public ModificationPartRender(){

    }

    @Override
    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {

        if (p_147500_1_.getClass() == ModificationPartBuilderTile.class){

            if (p_147500_1_.getBlockMetadata() == 2){
                rotationangle = -90; // south
                transletZ = 0.5f;
                transletX = 0f;
            } else if (p_147500_1_.getBlockMetadata() == 3) {
                rotationangle = 90; // north
                transletZ = 0.5f;
                transletX = 0f;
            } else if (p_147500_1_.getBlockMetadata() == 4) {
                rotationangle = 0; //east
                transletX = 0.5f;
                transletZ = 0;

            }else if(p_147500_1_.getBlockMetadata() == 5){
                rotationangle = -180; //west
                transletX = 0.5f;
                transletZ = 0;
            }

            GL11.glPushMatrix();
            //GL11.glRotatef(180f, 0f, 0f, 1f); // Rotate the block so that it is upright

            GL11.glTranslated(p_147500_2_ + transletX, p_147500_4_ , p_147500_6_ + transletZ);
            GL11.glRotatef(rotationangle, 0, 1, 0);
            GL11.glScalef(1f, 1f, 1f); // Scale the block to the correct size


            this.bindTexture(new ResourceLocation("dbca:models/PartBuilderTextures.png"));


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
