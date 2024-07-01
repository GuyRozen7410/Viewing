package me.guy.dbca.keyhandler;


import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


import me.guy.dbca.network.OpenGuiMessage;

import me.guy.dbca.network.PacketHandler;

import net.minecraft.client.settings.KeyBinding;


import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class KeyHandler {
    public static KeyBinding Ability1;

    public static KeyBinding OpenGui;



    public static void init(){
        Ability1 = new KeyBinding("key.ability1", Keyboard.KEY_Y, "key.categories.dbca");
        ClientRegistry.registerKeyBinding(Ability1);
        OpenGui = new KeyBinding("key.opengui", Keyboard.KEY_O, "key.categories.dbca");
        ClientRegistry.registerKeyBinding(OpenGui);


    }


}
