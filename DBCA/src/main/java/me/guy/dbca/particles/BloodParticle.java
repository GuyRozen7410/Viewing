package me.guy.dbca.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class BloodParticle extends EntityFX {

    private static final ResourceLocation BLOOD_TEXTURE = new ResourceLocation("textures/particle/particles.png");


    public BloodParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);

        this.particleRed = 1.0f; // Adjust color components as needed
        this.particleGreen = 0.0f;
        this.particleBlue = 0.0f;
        this.particleScale = 0.1f; // Adjust size scale
        this.particleMaxAge = 20;
        this.particleGravity = 1.2f;


    }



    @Override
    public void renderParticle(Tessellator tessellator, float partialTicks, float rotationX, float rotationXZ, float rotationZ, float rotationYZ, float rotationXY) {
        Minecraft.getMinecraft().renderEngine.bindTexture(BLOOD_TEXTURE); // Bind rain particle texture
        // UV coordinates for rain particle texture



        tessellator.setBrightness(240); // Adjust brightness if needed
        tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, 1.0f);

        // Adjust position based on particle motion
        double posX = this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX;
        double posY = this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY;
        double posZ = this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ;

        // Define UV coordinates for rain particle within the texture
        float uMin = 1 / 128.0f;
        float uMax = 6 / 128.0f;
        float vMin = 47 / 128.0f;
        float vMax = 55 / 128.0f;

        // Define vertices and texture coordinates
        tessellator.addVertexWithUV(posX - rotationX * particleScale - rotationYZ * particleScale, posY - rotationXZ * particleScale, posZ - rotationZ * particleScale - rotationXY * particleScale, uMax, vMax);
        tessellator.addVertexWithUV(posX - rotationX * particleScale + rotationYZ * particleScale, posY + rotationXZ * particleScale, posZ - rotationZ * particleScale + rotationXY * particleScale, uMax, vMin);
        tessellator.addVertexWithUV(posX + rotationX * particleScale + rotationYZ * particleScale, posY + rotationXZ * particleScale, posZ + rotationZ * particleScale + rotationXY * particleScale, uMin, vMin);
        tessellator.addVertexWithUV(posX + rotationX * particleScale - rotationYZ * particleScale, posY - rotationXZ * particleScale, posZ + rotationZ * particleScale - rotationXY * particleScale, uMin, vMax);


    }

    @Override
    public void onUpdate() {
        // Log motion and position values


        //super.onUpdate();




    }
}

