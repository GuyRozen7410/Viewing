package me.guy.dbca.util;

import cpw.mods.fml.common.FMLLog;
import me.guy.dbca.reference.Reference;
import org.apache.logging.log4j.Level;

public class LogHelper {

    public static void Log(Level loglevel, Object object){
        FMLLog.log(Reference.MOD_ID, loglevel, String.valueOf(object));
    }

    public static void All(Object object){
        Log(Level.ALL, object);
    }

    public static void Debug(Object object){
        Log(Level.DEBUG, object);
    }

    public static void Error(Object object){
        Log(Level.ERROR, object);
    }

    public static void Fatal(Object object){
        Log(Level.FATAL, object);
    }

    public static void Info(Object object){
        Log(Level.INFO, object);
    }

    public static void Off(Object object){
        Log(Level.OFF, object);
    }

    public static void Trace(Object object){
        Log(Level.TRACE, object);
    }

    public static void Warn(Object object){
        Log(Level.WARN, object);
    }
}
