package me.guy.dbca.gui;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.blocks.ModificationPartBuilderTile;
import me.guy.dbca.items.Tiers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class PartsContainerGui extends GuiContainer {
    private ModificationPartBuilderTile tile;



    public PartsContainerGui(ModificationPartBuilderTile tile, EntityPlayer player) {
        super(new PartsContainer(tile, player));
        this.xSize = 256;
        this.ySize = 256;
        this.tile = tile;

    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        this.drawCenteredString(this.fontRendererObj, "Modification Part Builder", xSize / 2 , ySize / -25, 0xFFFFFF);

        if (tile.getInventory()[4] != null){



            for (int i = 0; i < tile.getInventory().length; i++){
                if(i != 4){
                    if (tile.getInventory()[i] != null){

                        BonusDes(tile.getInventory()[i], i);


                    }

                }
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        //this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/generic_54.png"));

        //this.drawTexturedModalRect(k, l, 0, 0 ,this.xSize, this.ySize);
        /*
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                int xPos = GRID_X + col * SLOT_WIDTH;
                int yPos = GRID_Y + row * SLOT_HEIGHT;
                drawSlot(xPos, yPos); // Method to draw slot at specified position
            }
        }
         */
        //drawDefaultBackground();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("dbca:textures/gui/Modification_Part_Builder_Gui.png"));
        drawTexturedModalRect((width - xSize) / 2 + 3, (height - ySize) / 2 + 3, 3, 3, 148, 144);//Slots
        drawTexturedModalRect((width - xSize) / 2 + 3, (height - ySize) / 2 + 147, 3, 147, 177, 92);//Inventory
        drawTexturedModalRect((width - xSize) / 2 +  154, (height - ySize) / 2 + 6, 154, 6, 84, 138);//Info

        if (tile.getInventory()[4] != null && Tiers.getItemInTier(tile.getInventory()[4].getItem(), (byte) 1)){
            drawTexturedModalRect((width - xSize) / 2 + 25, (height - ySize) / 2 + 22, 223, 159, 17, 17);//1
            drawTexturedModalRect((width - xSize) / 2 + 67, (height - ySize) / 2 + 22, 223, 159, 17, 17);//2
            drawTexturedModalRect((width - xSize) / 2 + 110, (height - ySize) / 2 + 22, 223, 159, 17, 17);//3
            drawTexturedModalRect((width - xSize) / 2 + 25, (height - ySize) / 2 + 105, 223, 159, 17, 17);//7
            drawTexturedModalRect((width - xSize) / 2 + 67, (height - ySize) / 2 + 105, 223, 159, 17, 17);//8
            drawTexturedModalRect((width - xSize) / 2 + 110, (height - ySize) / 2 + 105, 223, 159, 17, 17);//9

        }
        if (tile.getInventory()[4] == null){
            drawTexturedModalRect((width - xSize) / 2 + 25, (height - ySize) / 2 + 22, 223, 159, 17, 17);//1
            drawTexturedModalRect((width - xSize) / 2 + 67, (height - ySize) / 2 + 22, 223, 159, 17, 17);//2
            drawTexturedModalRect((width - xSize) / 2 + 110, (height - ySize) / 2 + 22, 223, 159, 17, 17);//3
            drawTexturedModalRect((width - xSize) / 2 + 25, (height - ySize) / 2 + 105, 223, 159, 17, 17);//7
            drawTexturedModalRect((width - xSize) / 2 + 67, (height - ySize) / 2 + 105, 223, 159, 17, 17);//8
            drawTexturedModalRect((width - xSize) / 2 + 110, (height - ySize) / 2 + 105, 223, 159, 17, 17);//9
            drawTexturedModalRect((width - xSize) / 2 + 25, (height - ySize) / 2 + 65, 223, 159, 17, 17);//4
            drawTexturedModalRect((width - xSize) / 2 + 110, (height - ySize) / 2 + 65, 223, 159, 17, 17);//5
        }


    }


    private void BonusDes(ItemStack stack, int slotIndex){

        GL11.glPushMatrix();
        GL11.glScalef(0.85F, 0.85F, 0.85F);

            switch (slotIndex){
                case 3:
                    if (stack.getItem() == Items.milk_bucket){

                        this.fontRendererObj.drawString("Milk: Removes", 182, 16, 0xFFFFFF);
                        this.fontRendererObj.drawString("All Negative Effects",184, 26, 0xFFFFFF);
                    }
                    if (stack.getItem() == ItemsDBC.ItemMedMoss){
                        this.fontRendererObj.drawString("Moss: +5% Passive HP", 182, 16, 0x006400);
                        this.fontRendererObj.drawString("Regen", 184, 26, 0x006400);
                    }
                    if (stack.getItem() == Item.getItemFromBlock(Blocks.ice)){
                        this.fontRendererObj.drawString("Ice: -5 Burnout", 182, 16, 0x00FFFF);
                        this.fontRendererObj.drawString("Timer", 184, 26, 0x00FFFF);
                    }
                    if (stack.getItem() == ItemsDBC.ItemNamekDragonBlock){
                        this.fontRendererObj.drawString("Namek Dragon Ball:", 182, 16, 0xFFA500);
                        this.fontRendererObj.drawString("+5% Tp Gain And Xp", 184, 26, 0xFFA500);
                    }if (stack.getItem() == ItemsDBC.ItemPP){
                        this.fontRendererObj.drawString("Power Pole:", 190, 16,0xFF0000);
                        this.fontRendererObj.drawString("+1 Reach", 194, 26,0xFF0000);
                    }
                break;
                case 5:
                    if (stack.getItem() == Items.milk_bucket){

                        this.fontRendererObj.drawString("Milk: Removes", 182, 40, 0xFFFFFF);
                        this.fontRendererObj.drawString("All Negative Effects",184, 50, 0xFFFFFF);
                    }
                    if (stack.getItem() == ItemsDBC.ItemMedMoss){
                        this.fontRendererObj.drawString("Moss: +5% Passive HP", 182, 40, 0x006400);
                        this.fontRendererObj.drawString("Regen", 184, 50, 0x006400);
                    }
                    if (stack.getItem() == Item.getItemFromBlock(Blocks.ice)){
                        this.fontRendererObj.drawString("Ice: -5 Burnout", 182, 40, 0x00FFFF);
                        this.fontRendererObj.drawString("Timer", 184, 50, 0x00FFFF);
                    }
                    if (stack.getItem() == ItemsDBC.ItemNamekDragonBlock){
                        this.fontRendererObj.drawString("Namek Dragon Ball:", 182, 40, 0xFFA500);
                        this.fontRendererObj.drawString("+5% Tp Gain And Xp", 184, 50, 0xFFA500);
                    }
                    if (stack.getItem() == ItemsDBC.ItemPP){
                        this.fontRendererObj.drawString("Power Pole:", 190, 40,0xFF0000);
                        this.fontRendererObj.drawString("+1 Reach", 194, 50,0xFF0000);
                    }
                break;
            }





        GL11.glPopMatrix();


    }

}
