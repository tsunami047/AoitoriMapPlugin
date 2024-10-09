package io.aoitori043.aoitorimapplugin.config;


import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.ScriptExecutor;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.InjectMapper;
import io.aoitori043.aoitoriproject.config.InjectMappers;
import io.aoitori043.aoitoriproject.config.impl.BasicMapper;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;

@ConfigProperties
public class MapConfigHandler extends BasicMapper {

    public static MapConfigHandler instance;

    public MapConfigHandler() {
        super();
    }

    public static void load() {
        try {
            AoitoriMapPlugin.scriptExecutor = new ScriptExecutor();
            instance = new MapConfigHandler();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public JavaPlugin getPlugin() {
        return AoitoriMapPlugin.plugin;
    }


    @InjectMappers(dir = "gui")
    public static LinkedHashMap<String, GuiMapper> gui;
    @InjectMappers(dir = "overlay")
    public static LinkedHashMap<String, OverlayMapper> overlay;

    @Override
    public void loadConfig() {
        try {
            AoitoriMapPlugin.scriptExecutor = new ScriptExecutor();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
