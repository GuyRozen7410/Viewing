package me.guy.dbca.handlers;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.guy.dbca.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;


    public static boolean updateCheck = true;

    public static void Init(String dir){
        if (config == null){
            File path = new File(dir  + "\\" + Reference.MOD_ID + ".cfg");
            config = new Configuration(path);
            loadConfig();
        }

    }

    private static void loadConfig( ){
        updateCheck = config.getBoolean("Check", Configuration.CATEGORY_GENERAL, true, "Test Check");
        if (config.hasChanged()){
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event){
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)){
            loadConfig();
        }
    }

    public static  Configuration getConfig(){return config;}

}
