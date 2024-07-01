package me.guy.dbca.items;

import me.guy.dbca.DBCA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class PartModificationItemRender implements IItemRenderer {

    private final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("dbca:models/PartModification.obj"));
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (type == ItemRenderType.INVENTORY){
            //Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("dbca:models/ModificationTexture.png"));
            RenderItem.getInstance().renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer,  Minecraft.getMinecraft().getTextureManager(), new ItemStack(DBCA.partsTableItem), 0, 0);
        }else{
            GL11.glPushMatrix();
            //GL11.glRotatef(180f, 0f, 0f, 1f); // Rotate the block so that it is upright

            GL11.glTranslatef(0.5f, 0.5f, 0.5f);

            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("dbca:models/PartBuilderTextures.png"));
            GL11.glRotatef(180, 0, 1, 0);

            GL11.glScalef(0.8f, 0.8f, 0.8f);
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
