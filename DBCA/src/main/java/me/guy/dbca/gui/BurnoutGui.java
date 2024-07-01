package me.guy.dbca.gui;


import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.guy.dbca.ExtendedPlayer;
import me.guy.dbca.events.EventsHandler;
import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.Gui;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;


public class BurnoutGui extends Gui {
    private Minecraft mc;

    private static boolean burnout = false;

    //max of 44
    private static int bar_width = 0;
    private static int burnoutStat = 0;
    private static int burnoutStatMax = 0;

    private static int burnoutAmount = 5;
    private ResourceLocation tx = new ResourceLocation("dbca:textures/gui/Burnout_Meter__Effects.png");
    public BurnoutGui(Minecraft mc){
        super();
        this.mc = mc;
    }

    public static void AddBarWidth(){
        if (burnoutStat <= 0){
            if (bar_width <= 39){
                bar_width += burnoutAmount;
            }else{
                bar_width = 44;
            }

            burnoutStat = burnoutStatMax;

        }else{
            burnoutStat--;
        }

    }
    public static int getBarWidth(){
        return bar_width;
    }
    public static boolean getBurnout(){
        return burnout;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void doRenderEvent(RenderGameOverlayEvent event){

        if(event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE)
        {
            return;
        }
        if(mc.currentScreen == null && !mc.gameSettings.showDebugInfo && mc.thePlayer != null)
        {
            renderGameOverlay();
        }
    }


    public void renderGameOverlay() {


        if (ExtendedPlayer.PlayerData.getInventory()[2] != null){
            Minecraft.getMinecraft().getTextureManager().bindTexture(tx);
            this.drawTexturedModalRect((mc.displayWidth -(mc.displayWidth-10))/2 + 200, (mc.displayHeight  - (mc.displayHeight- 10)) / 2 - 5, 15, 35, 64,  28);
            GL11.glColor4f(1.0f, 0, 0, 1.0f);
            if (bar_width <= 44 && bar_width >= 0){
                this.drawTexturedModalRect((mc.displayWidth -(mc.displayWidth-10))/2 + 219, (mc.displayHeight  - (mc.displayHeight- 10)) / 2 + 9, 34, 64, bar_width,  13);
            }
            if (bar_width >= 44){
                burnout = true;
            }
            if (bar_width < 1){
                burnout = false;
                bar_width = 0;
            }
            if (EventsHandler.getBurnoutHealing()){
                bar_width--;
                EventsHandler.setBurnoutHealing();
            }
        }



    }


}
