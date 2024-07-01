package me.guy.dbca.render;

import JinRyuu.JBRA.ModelBipedDBC;
import JinRyuu.JBRA.RenderPlayerJBRA;
import me.guy.dbca.DBCA;
import me.guy.dbca.ExtendedPlayer;
import me.guy.dbca.animations.AnimationData;
import me.guy.dbca.animations.AnimationLoader;
import me.guy.dbca.events.EventsHandler;
import me.guy.dbca.events.ExtendedPlayerEvents;
import me.guy.dbca.gui.PlayerAndroidMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.Objects;

public class RenderPartsJBRA extends RenderPlayerJBRA {
    private static final ResourceLocation overlayTexturePotatoCore = new ResourceLocation("dbca:textures/models/potatocore_overlay.png");
    private static final ResourceLocation overlayTextureLimb = new ResourceLocation("dbca:textures/models/PotatoLimbLeft.png");





    public RenderPartsJBRA(){
        super();
    }


    @Override
    protected void renderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
        if (EventsHandler.start){

            update(animationTimer);

        }else{
            animationTimer = 0;
            this.modelBipedMain.bipedRightArm.rotationPointX = new ModelBiped().bipedRightArm.rotationPointX;
            this.modelBipedMain.bipedRightArm.rotationPointY = new ModelBiped().bipedRightArm.rotationPointY;
            this.modelBipedMain.bipedRightArm.rotationPointZ = new ModelBiped().bipedRightArm.rotationPointZ;
            this.modelBipedMain.bipedRightArm.rotateAngleX = new ModelBiped().bipedRightArm.rotateAngleX;
            this.modelBipedMain.bipedRightArm.rotateAngleY = new ModelBiped().bipedRightArm.rotateAngleY;
            this.modelBipedMain.bipedRightArm.rotateAngleZ = new ModelBiped().bipedRightArm.rotateAngleZ;
            this.modelBipedMain.bipedLeftArm.rotationPointX = new ModelBiped().bipedLeftArm.rotationPointX;
            this.modelBipedMain.bipedLeftArm.rotationPointY = new ModelBiped().bipedLeftArm.rotationPointY;
            this.modelBipedMain.bipedLeftArm.rotationPointZ = new ModelBiped().bipedLeftArm.rotationPointZ;
            this.modelBipedMain.bipedLeftArm.rotateAngleX = new ModelBiped().bipedLeftArm.rotateAngleX;
            this.modelBipedMain.bipedLeftArm.rotateAngleY = new ModelBiped().bipedLeftArm.rotateAngleY;
            this.modelBipedMain.bipedLeftArm.rotateAngleZ = new ModelBiped().bipedLeftArm.rotateAngleZ;
            super.renderModel(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
        }




    }

    private float getCurrentScaleFactor(ModelRenderer modelPart) {
        // Save the current matrix state
        GL11.glPushMatrix();

        // Reset to identity to isolate the model part transformations
        GL11.glLoadIdentity();

        // Apply the model part transformations
        modelPart.postRender(1.0F);

        // Get the current transformation matrix
        FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, matrixBuffer);
        matrixBuffer.rewind();

        // Extract scale factors from the matrix
        float scaleX = (float) Math.sqrt(matrixBuffer.get(0) * matrixBuffer.get(0) + matrixBuffer.get(1) * matrixBuffer.get(1) + matrixBuffer.get(2) * matrixBuffer.get(2));
        float scaleY = (float) Math.sqrt(matrixBuffer.get(4) * matrixBuffer.get(4) + matrixBuffer.get(5) * matrixBuffer.get(5) + matrixBuffer.get(6) * matrixBuffer.get(6));
        float scaleZ = (float) Math.sqrt(matrixBuffer.get(8) * matrixBuffer.get(8) + matrixBuffer.get(9) * matrixBuffer.get(9) + matrixBuffer.get(10) * matrixBuffer.get(10));

        // Restore the previous matrix state
        GL11.glPopMatrix();

        // Assuming uniform scaling, return one of the scale factors
        return scaleZ; // Or average of scaleX, scaleY, scaleZ if non-uniform scaling is applied
    }

    @Override
    protected void renderEquippedItemsJBRA(AbstractClientPlayer abstractClientPlayer, float partialTicks) {
        super.renderEquippedItemsJBRA(abstractClientPlayer, partialTicks);

        GL11.glPushMatrix();

        // Render potato core overlay
        if (ExtendedPlayer.PlayerData.getInventory()[2] != null
                && ExtendedPlayer.PlayerData.getInventory()[2].getItem() == DBCA.potatoCore
                && PlayerAndroidMenu.core_display) {

            bindTexture(overlayTexturePotatoCore);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            // Apply transformations
            //((ModelBiped) modelMain).bipedBody.postRender(partialTicks); // Use a fixed scale factor (1/16 = 0.0625)
            ((ModelBiped) modelMain).bipedBody.render(0.0625f);


        }

        // Render left limb overlay
        if (ExtendedPlayer.PlayerData.getInventory()[1] != null
                && ExtendedPlayer.PlayerData.getInventory()[1].getItem() == DBCA.potatoLimb
                && PlayerAndroidMenu.limb_displayLeft) {

            bindTexture(overlayTextureLimb);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            // Apply transformations

            ((ModelBiped) modelMain).bipedLeftArm.render(0.0625f);

        }

        // Render right limb overlay
        if (ExtendedPlayer.PlayerData.getInventory()[3] != null
                && ExtendedPlayer.PlayerData.getInventory()[3].getItem() == DBCA.potatoLimb
                && PlayerAndroidMenu.limb_displayRight) {

            bindTexture(overlayTextureLimb);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            ((ModelBiped) modelMain).bipedRightArm.render(0.0625F);


        }

        GL11.glPopMatrix();
    }
    public static float animationTimer = 0;

    public void update(float currentTime) {
        // Iterate through each animation and check if it is active

        for (AnimationData.Animation animation : EventsHandler.getAnimationData().getAnimations()){
            if (animation.isActive(currentTime)) {
                applyAnimation(animation, currentTime);
            }
            if (animation.isLastKeyframeFired(currentTime)){
                handleLastKeyframe(animation);
            }
        }

    }
    float[] interpolatedTarget;
    private void applyAnimation(AnimationData.Animation animation, float currentTime) {
        // Get the keyframes for the current animation
        AnimationData.Keyframe[] keyframes = animation.getKeyframes();

        // Find the two keyframes we are interpolating between
        AnimationData.Keyframe startKeyframe = null;
        AnimationData.Keyframe endKeyframe = null;

        for (int i = 0; i < keyframes.length - 1; i++) {
            if (currentTime >= keyframes[i].getTimestamp() && currentTime < keyframes[i + 1].getTimestamp()) {
                startKeyframe = keyframes[i];
                endKeyframe = keyframes[i + 1];
                break;
            }
        }

        // Handle the case where the current time is exactly at or beyond the last keyframe
//        if (currentTime >= keyframes[keyframes.length - 1].getTimestamp()) {
//            startKeyframe = keyframes[keyframes.length - 1];
//            endKeyframe = keyframes[keyframes.length - 1];
//        }

        // If no valid keyframes found for interpolation, return
        if (startKeyframe == null || endKeyframe == null) {
            return;
        }

        // Interpolate between startKeyframe and endKeyframe
        float t = (currentTime - startKeyframe.getTimestamp()) / (endKeyframe.getTimestamp() - startKeyframe.getTimestamp());
        if (startKeyframe == endKeyframe) {
            t = 0;
        }

        float[] startTarget = startKeyframe.getTarget();
        float[] endTarget = endKeyframe.getTarget();

        interpolatedTarget = new float[startTarget.length];
        for (int i = 0; i < startTarget.length; i++) {
            interpolatedTarget[i] = startTarget[i] + t * (endTarget[i] - startTarget[i]);
        }

        // Apply the interpolated values to the bone
        applyToBone(animation.getBone(), animation.getTarget(), interpolatedTarget);


    }

    private void applyToBone(String bone, String target, float[] values) {
        // Example: Apply interpolated values to the right arm or left arm based on bone and target type
        if (bone.equals("RightArm")) {
            applyToModelPart(this.modelBipedMain.bipedRightArm, target, values , bone);
        } else if (bone.equals("LeftArm")) {
            applyToModelPart(this.modelBipedMain.bipedLeftArm, target, values, bone);
        }
        // Add more bones and their respective target types as needed
    }

    private void applyToModelPart(ModelRenderer modelPart, String target, float[] values, String bone) {
        if (target.equals("position")) {
            if (Objects.equals(bone, "RightArm")){
                modelPart.rotationPointX = values[0] - 5.0f;
                modelPart.rotationPointY = values[1] + 2.0f;
                modelPart.rotationPointZ = values[2];
            }else if (Objects.equals(bone, "LeftArm")){
                modelPart.rotationPointX = values[0] + 5.0f;
                modelPart.rotationPointY = values[1] + 2.0f;
                modelPart.rotationPointZ = values[2];
            }

            System.out.println(values[0] + "  " + values[1]);
        } else if (target.equals("rotation")) {
            modelPart.rotateAngleX = (float) Math.toRadians(values[0]);
            modelPart.rotateAngleY = (float) Math.toRadians(values[1]);
            modelPart.rotateAngleZ = (float) Math.toRadians(values[2]);
        }
        
    }

    private void handleLastKeyframe(AnimationData.Animation animation) {
        // Handle the logic for when the last keyframe has been fired
        // This could involve resetting the animation, triggering an event, etc.
        System.out.println("Last keyframe fired for animation: " + animation.getBone() + " targeting " + animation.getTarget());
        EventsHandler.start = false;
        animationTimer = 0;
    }
}








