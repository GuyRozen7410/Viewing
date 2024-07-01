package me.guy.dbca.gui;



import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.guy.dbca.DBCA;
import me.guy.dbca.ExtendedPlayer;
import me.guy.dbca.network.PacketHandler;
import me.guy.dbca.network.PartsDisplayMsg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

import javax.swing.text.html.parser.Entity;


@SideOnly(Side.CLIENT)
public class PlayerAndroidMenu extends GuiScreen {
    public static boolean core_display;
    public static boolean head_display = false;

    public static boolean limb_displayLeft;
    public static boolean limb_displayRight;
    public static boolean limb_displayLeftLeg;
    public static boolean limb_displayRightLeg;
    private GuiButton displayCore;
    private GuiButton displayLimbLeft;
    private GuiButton displayLimbRight;
    private GuiButton displayLimbLeftLeg;
    private GuiButton displayLimbRightLeg;
    private GuiButton KiAttacks;
    private GuiButton PlayerMenu;
    private boolean PlayerMenuOn = true;

    private boolean KiAttacksMenu;
    private GuiButton Slot0Plus;
    private GuiButton Slot0Minus;
    private GuiButton Slot1Plus;
    private GuiButton Slot1Minus;


    private static byte Slot0Power = 10;
    private static byte Slot1Power = 10;
    public PlayerAndroidMenu(){

    }

    @Override
    public void initGui() {

        displayCore = new GuiButton(0, width / 2 - 28 , height / 2 - 35, 70, 70, ""){
            @Override
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
                if (this.visible){
                    if (!KiAttacksMenu){
                        if (ExtendedPlayer.PlayerData.getInventory()[2] != null){
                            if (ExtendedPlayer.PlayerData.getInventory()[2].getItem() == DBCA.potatoCore){
                                TextureManager manger = mc.getTextureManager();
                                manger.bindTexture(new ResourceLocation( "dbca:textures/gui/cores_gui.png"));
                                this.drawTexturedModalRect(xPosition, yPosition, 2, 5, 56, 47);

                            }
                        }
                    }

                }
            }
        };

        KiAttacks = new GuiButton(1, width / 2 -150, height / 2, 20, 20, ""){
            @Override
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
                if (this.visible){
                    //jinryuumodscore
                    TextureManager manager = mc.getTextureManager();
                    manager.bindTexture(new ResourceLocation("jinryuumodscore:icons3.png"));
                    this.drawTexturedModalRect(xPosition, yPosition,48, 0, 15, 15);
                }
            }
        };

        Slot0Plus = new GuiButton(2, width / 2 -35, height / 2 -40, 28, 28, ""){
            @Override
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
                if (this.visible){
                    mc.getTextureManager().bindTexture(new ResourceLocation("dbca:textures/gui/Precentage_Hud_UI_ver1.png"));
                    this.drawTexturedModalRect(xPosition, yPosition, 20, 169, 26, 21);
                }
            }
        };

        Slot0Minus = new GuiButton(3, width / 2 - 115, height / 2 - 40, 28, 28, ""){
            @Override
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
                if (this.visible){
                    mc.getTextureManager().bindTexture(new ResourceLocation("dbca:textures/gui/Precentage_Hud_UI_ver1.png"));
                    this.drawTexturedModalRect(xPosition, yPosition, 16, 143, 26, 21);
                }
            }
        };

        PlayerMenu = new GuiButton(4, width / 2 - 150, height / 2 + 20, 20, 20, ""){
            @Override
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
                if (this.visible){
                    mc.getTextureManager().bindTexture(new ResourceLocation("jinryuumodscore:icons3.png"));
                    this.drawTexturedModalRect(xPosition, yPosition, 16, 0, 16, 16);
                }
            }
        };
        Slot1Plus = new GuiButton(5, width / 2 + 85, height / 2 -40, 28, 28, ""){
            @Override
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
                if (this.visible){
                    mc.getTextureManager().bindTexture(new ResourceLocation("dbca:textures/gui/Precentage_Hud_UI_ver1.png"));
                    this.drawTexturedModalRect(xPosition, yPosition, 20, 169, 26, 21);
                }
            }
        };

        Slot1Minus = new GuiButton(6, width / 2 + 5, height / 2 - 40, 28, 28, ""){
            @Override
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
                if (this.visible){
                    mc.getTextureManager().bindTexture(new ResourceLocation("dbca:textures/gui/Precentage_Hud_UI_ver1.png"));
                    this.drawTexturedModalRect(xPosition, yPosition, 16, 143, 26, 21);
                }
            }
        };
        displayLimbLeft = new GuiButton(7, width / 2 - 90 , height / 2 - 65, 70, 70, ""){
            @Override
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
                if (this.visible){
                    if (!KiAttacksMenu){
                        if (ExtendedPlayer.PlayerData.getInventory()[1] != null){
                            if (ExtendedPlayer.PlayerData.getInventory()[1].getItem() == DBCA.potatoLimb){
                                TextureManager manger = mc.getTextureManager();
                                manger.bindTexture(new ResourceLocation( "dbca:textures/gui/cores_gui.png"));
                                this.drawTexturedModalRect(xPosition, yPosition, 2, 72, 55, 47);

                            }
                        }
                    }

                }
            }
        };
        displayLimbRight = new GuiButton(8, width / 2 + 35 , height / 2 - 65, 70, 70, ""){
            @Override
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
                if (ExtendedPlayer.PlayerData.getInventory()[3] != null){
                    if (ExtendedPlayer.PlayerData.getInventory()[3].getItem() == DBCA.potatoLimb){
                        TextureManager manger = mc.getTextureManager();
                        manger.bindTexture(new ResourceLocation( "dbca:textures/gui/cores_gui.png"));
                        this.drawTexturedModalRect(xPosition, yPosition, 2, 72, 55, 47);

                    }
                }
            }
        };
        buttonList.add(displayCore);
        buttonList.add(KiAttacks);
        buttonList.add(Slot0Plus);
        buttonList.add(Slot0Minus);
        buttonList.add(PlayerMenu);
        buttonList.add(Slot1Plus);
        buttonList.add(Slot1Minus);
        buttonList.add(displayLimbLeft);
        buttonList.add(displayLimbRight);




    }
    //98 79 //121 121
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        drawDefaultBackground();
        if (!KiAttacksMenu && PlayerMenuOn){

            mc.getTextureManager().bindTexture(new ResourceLocation("dbca:textures/gui/BodyGui.png"));
            this.drawTexturedModalRect(width / 2 - 125, height / 2 - 125, 0, 0, 256, 256);
            displayCore.drawButton(mc, p_73863_1_, p_73863_2_);
            displayLimbRight.drawButton(mc, p_73863_1_, p_73863_2_);
            displayLimbLeft.drawButton(mc, p_73863_1_, p_73863_2_);
            KiAttacks.drawButton(mc, p_73863_1_, p_73863_2_);
            displayCore.enabled = true;
            displayLimbRight.enabled = true;
            displayLimbLeft.enabled = true;
            Slot0Minus.enabled = false;
            Slot0Plus.enabled = false;
            Slot0Plus.visible = false;
            Slot0Minus.visible = false;
            Slot1Plus.visible = false;
            Slot1Minus.visible = false;

        }else{


            mc.getTextureManager().bindTexture(new ResourceLocation("dbca:textures/gui/Precentage_Hud_UI_ver1.png"));

            this.drawTexturedModalRect(width / 2 - 120 , height / 2 - 75, 13, 8, 233, 119);//backround
            this.drawTexturedModalRect(width / 2 - 2, height/ 2 -75, 125, 128,1 , 118);//line
            this.drawTexturedModalRect(width / 2 - 100, height/ 2 -5 / 2, 138, 132,77 , 29);//left yellow box
            this.drawTexturedModalRect(width / 2  + 20, height/ 2 -5 / 2, 138, 132,77 , 29);//right yellow box
            Slot0Plus.drawButton(mc, p_73863_1_, p_73863_2_);
            Slot0Minus.drawButton(mc, p_73863_1_, p_73863_2_);
            PlayerMenu.drawButton(mc, p_73863_1_, p_73863_2_);
            Slot1Minus.drawButton(mc, p_73863_1_, p_73863_2_);
            Slot1Plus.drawButton(mc, p_73863_1_, p_73863_2_);
            displayCore.enabled = false;
            displayLimbLeft.enabled = false;
            displayLimbRight.enabled = false;
            Slot0Plus.enabled = true;
            Slot0Minus.enabled = true;
            Slot0Plus.visible = true;
            Slot0Minus.visible = true;
            Slot1Plus.visible = true;
            Slot1Minus.visible = true;
            this.drawString(this.fontRendererObj, "Left", width / 2 - 72, height/ 2 - 65,0xFFFFFF);
            this.drawString(this.fontRendererObj, "Right", width / 2 + 48, height/ 2 - 65,0xFFFFFF);
            this.drawString(this.fontRendererObj, Slot0Power + "%", width / 2 + 50, height/ 2 - 33,0xFFFFFF);


        }



    }

    @Override
    public void drawBackground(int p_146278_1_) {
        super.drawBackground(p_146278_1_);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (ExtendedPlayer.PlayerData.getInventory()[2] != null){
            if (button == displayCore){
                if (!core_display){
                    core_display = true;
                }else {
                    core_display = false;
                }
                PacketHandler.sendToServer(new PartsDisplayMsg(core_display, false, false, false, false, false));
            }
        }
        if (ExtendedPlayer.PlayerData.getInventory()[1] != null){
            if (button == displayLimbLeft){
                if (!limb_displayLeft){
                    limb_displayLeft = true;
                }else {
                    limb_displayLeft = false;
                }
                PacketHandler.sendToServer(new PartsDisplayMsg(limb_displayLeft, false, false, false, false, false));
            }
        }if (ExtendedPlayer.PlayerData.getInventory()[3] != null){
            if (button == displayLimbRight){
                if (!limb_displayRight){
                    limb_displayRight = true;
                }else {
                    limb_displayRight = false;
                }
                PacketHandler.sendToServer(new PartsDisplayMsg(limb_displayRight, false, false, false, false, false));
            }
        }
        if (button == KiAttacks){

                PlayerMenuOn = false;
                KiAttacksMenu = true;


        }
        if (button == PlayerMenu){
            PlayerMenuOn = true;
            KiAttacksMenu = false;
        }
        if (button == Slot1Plus){
            if (Slot0Power <= 90){
                Slot0Power += 10;

            }
        }
        if (button == Slot1Minus){
            if (Slot0Power > 10){
                Slot0Power -= 10;

            }
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        // Handle keyboard input here
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public static byte getSlot0Power(){
        return Slot0Power;
    }
}
