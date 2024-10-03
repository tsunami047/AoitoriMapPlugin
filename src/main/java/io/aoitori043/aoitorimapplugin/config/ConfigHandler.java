package io.aoitori043.aoitorimapplugin.config;


import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.ScriptExecutor;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.impl.BasicMapper;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

@ConfigProperties
public class ConfigHandler extends BasicMapper {

    public static ConfigHandler instance;

    public ConfigHandler() {
        AoitoriMapPlugin.scriptExecutor = new ScriptExecutor();
    }

    public static void load() {
        try {
            instance = new ConfigHandler();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public JavaPlugin getPlugin() {
        return AoitoriMapPlugin.plugin;
    }


    public static LinkedHashMap<String, GuiMapper> gui;

    @Override
    public void loadConfig() {
        try {
            AoitoriMapPlugin.scriptExecutor = new ScriptExecutor();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
