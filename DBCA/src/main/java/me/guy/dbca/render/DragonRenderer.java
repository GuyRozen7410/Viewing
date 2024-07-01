package me.guy.dbca.render;

import JinRyuu.DragonBC.common.Npcs.*;
import JinRyuu.JRMCore.entity.EntityJRMC;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class DragonRenderer extends RendererLivingEntity {

    boolean b = false;

    public DragonRenderer(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
        this.mainModel = par1ModelBase;
        this.shadowSize = par2;
    }

    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
        this.doRender((EntityLivingBase)entity, d0, d1, d2, f, f1);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {


            return new ResourceLocation("dbca:textures/npcs/CDragon.png");

    }

    protected void passSpecialRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6) {
        if (!MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Specials.Pre(par1EntityLivingBase, this, par2, par4, par6))) {
            if (this.b) {
                float f = 1.6F;
                float f1 = 0.016666668F * f;
                double d3 = par1EntityLivingBase.getDistanceSqToEntity(this.renderManager.livingPlayer);
                float f2 = par1EntityLivingBase.isSneaking() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;
                if (d3 < (double)(f2 * f2)) {
                    String s = par1EntityLivingBase.getCommandSenderName();
                    if (par1EntityLivingBase.isSneaking()) {
                        FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
                        GL11.glPushMatrix();
                        GL11.glTranslatef((float)par2 + 0.0F, (float)par4 + par1EntityLivingBase.height + 0.5F, (float)par6);
                        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                        GL11.glScalef(-f1, -f1, f1);
                        GL11.glDisable(2896);
                        GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
                        GL11.glDepthMask(false);
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        Tessellator tessellator = Tessellator.instance;
                        GL11.glDisable(3553);
                        tessellator.startDrawingQuads();
                        int i = fontrenderer.getStringWidth(s) / 2;
                        tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                        tessellator.addVertex((double)(-i - 1), -1.0, 0.0);
                        tessellator.addVertex((double)(-i - 1), 8.0, 0.0);
                        tessellator.addVertex((double)(i + 1), 8.0, 0.0);
                        tessellator.addVertex((double)(i + 1), -1.0, 0.0);
                        tessellator.draw();
                        GL11.glEnable(3553);
                        GL11.glDepthMask(true);
                        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, 553648127);
                        GL11.glEnable(2896);
                        GL11.glDisable(3042);
                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glPopMatrix();
                    } else {
                        this.func_96449_a(par1EntityLivingBase, par2, par4, par6, s, f1, d3);
                    }
                }
            }

            MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Specials.Post(par1EntityLivingBase, this, par2, par4, par6));
        }
    }


}
