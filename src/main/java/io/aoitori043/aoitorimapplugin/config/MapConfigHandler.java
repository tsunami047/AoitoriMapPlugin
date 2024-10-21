package io.aoitori043.aoitorimapplugin.config;


import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import io.aoitori043.aoitorimapplugin.business.ScriptExecutor;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.NetworkImpl;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.InjectMapper;
import io.aoitori043.aoitoriproject.config.InjectMappers;
import io.aoitori043.aoitoriproject.config.impl.BasicMapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

import java.util.LinkedHashMap;
import java.util.List;

@ConfigProperties
public class MapConfigHandler extends BasicMapper {

    public static MapConfigHandler instance;

    public MapConfigHandler() {
        super();
    }

    public static void init(){
        try {
            FileWatcher fileWatcher = new FileWatcher(AoitoriMapPlugin.plugin.getDataFolder().getPath());
            fileWatcher.startWatching();
        }catch (Exception e){
            e.printStackTrace();
        }
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


    @InjectMappers(dir = "gui",singe = true)
    public static LinkedHashMap<String, GuiMapper> gui;
    @InjectMappers(dir = "overlay")
    public static LinkedHashMap<String, OverlayMapper> overlay;

    public static List<String> permanentGUI;
    public static int sendDataDelay;
    public static boolean autoReloadConfig;
    public static String resourcesKey;
    public static boolean kickValidationTimeout;
    public static int kickValidationTimeoutTime;
    public static boolean enableValidation;

    @Override
    public void loadConfig() {

    }


}
