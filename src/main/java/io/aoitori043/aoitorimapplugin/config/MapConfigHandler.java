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

@ConfigProperties
public class MapConfigHandler extends BasicMapper {

    public static MapConfigHandler instance;

    public MapConfigHandler() {
        super();
    }

    public static void init(){
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(AoitoriMapPlugin.plugin.getDataFolder().getPath());
            path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            AoitoriMapPlugin.plugin.getLogger().info("Watching directory change: " + path);
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path filePath = (Path) event.context();
                    AoitoriMapPlugin.plugin.getLogger().info("Event type: " + kind + ". File affected: " + filePath);
                    if (kind == ENTRY_CREATE) {
                        AoitoriMapPlugin.plugin.getLogger().info("File created: " + filePath);
                    } else if (kind == ENTRY_MODIFY) {
                        AoitoriMapPlugin.plugin.getLogger().info("File modified: " + filePath);
                    } else if (kind == ENTRY_DELETE) {
                        AoitoriMapPlugin.plugin.getLogger().info("File deleted: " + filePath);
                    }
                    MapConfigHandler.load();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        MapPlayerProfile mapPlayerProfile = MapDatabaseClient.getMapPlayerProfile(player.getName());
                        OverlayManager overlayManager = mapPlayerProfile.getOverlayManager();
                        for (OverlayMapper value : MapConfigHandler.overlay.values()) {
                            overlayManager.addOverlayImpl(value);
                        }
                        NetworkImpl.sendPluginMessageToPlayer(player, OperateMapDataDTO.builder().type(OperateMapDataDTO.MapOperateType.REFRESH).build());
                    }
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
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
