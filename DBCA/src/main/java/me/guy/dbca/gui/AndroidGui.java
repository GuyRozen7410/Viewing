package me.guy.dbca.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.ExtendedPlayer;
import me.guy.dbca.blocks.AndroidTableTile;
import me.guy.dbca.blocks.ModificationPartBuilderTile;
import net.minecraft.client.entity.EntityClientPlayerMP;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class AndroidGui extends GuiContainer {


    private float xSize_lo;
    private float ySize_lo;

    private AndroidTableTile androidTableTile;





    public AndroidGui(EntityPlayer entityPlayer, AndroidTableTile androidTableTile){

        super(new AndroidContainer(entityPlayer.inventory, entityPlayer, androidTableTile));
        this.androidTableTile = androidTableTile;


    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        super.drawScreen(mouseX, mouseY, f);
        this.xSize_lo = (float)mouseX;
        this.ySize_lo = (float)mouseY;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int MouseX, int MouseY) {



        this.drawCenteredString(this.fontRendererObj, "Modification Menu", xSize / 2 , ySize / -25, 0xFFFFFF);



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
        this.mc.getTextureManager().bindTexture(new ResourceLocation("dbca:textures/gui/android_table.png"));
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawEntityOnScreen(this.guiLeft + 51, this.guiTop + 75, 30, (this.guiLeft + 51) - this.xSize_lo, (this.guiTop + 25) - this.ySize_lo, this.mc.thePlayer);
        if (ExtendedPlayer.PlayerData.getInventory()[2] == null){
            this.mc.getTextureManager().bindTexture(new ResourceLocation("dbca:textures/gui/android_table.png"));
            drawTexturedModalRect(guiLeft + 115, guiTop + 11,222, 158,18, 18);
            drawTexturedModalRect(guiLeft + 94,guiTop + 33,222, 158,18, 18);
            drawTexturedModalRect(guiLeft + 136,guiTop +33,222, 158,18, 18);
            drawTexturedModalRect(guiLeft + 102,guiTop + 55,222, 158,18, 18);
            drawTexturedModalRect(guiLeft + 127,guiTop + 55,222, 158,18, 18);
        }

    }






    private void drawEntityOnScreen(int i, int j, int k, float f, float g, EntityClientPlayerMP thePlayer) {
        GuiInventory.func_147046_a(this.guiLeft + 60, this.guiTop + 70, 30, (this.guiLeft + 51) - this.xSize_lo, (this.guiTop + 25) - this.ySize_lo, (EntityLivingBase)this.mc.thePlayer);
    }


    /*
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        if (ModifyPressed) {
            // If ModifyPressed is true, handle accordingly
            if (ExtendedPlayer.get(player).getCustomBoolean()) {
                // If the custom boolean is true, handle accordingly
                ItemStack potatoCoreStack = new ItemStack(DBCA.potatoCore);

                // Remove potatoCore from player's inventory
                removeItemFromInventory(player, potatoCoreStack);

                // Optionally, add potatoCore back to player's inventory if the slot was empty
                if (tableTile1.getStackInSlot(2) == null) {
                    PacketHandler.sendToServer(new ItemSyncMsg(player.getEntityId()));

                }
            }
        } else {
            // If ModifyPressed is false, handle accordingly
            System.out.println("1");

            if (ExtendedPlayer.get(player).getCustomBoolean() && tableTile1.getStackInSlot(2) == null) {
                // If custom boolean is true and slot 2 is empty, remove potatoCore from player's inventory
                ItemStack potatoCoreStack = new ItemStack(DBCA.potatoCore);
                removeItemFromInventory(player, potatoCoreStack);
            }
            if (!ExtendedPlayer.get(player).getCustomBoolean() && tableTile1.getStackInSlot(2) != null){
                PacketHandler.sendToServer(new ItemSyncMsg(player.getEntityId()));
            }

            ModifyPressed = false;
        }


    }
*/

}
