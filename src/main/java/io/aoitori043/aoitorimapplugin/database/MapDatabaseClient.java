package io.aoitori043.aoitorimapplugin.database;

import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import io.aoitori043.aoitorimapplugin.config.ImageEncryptor;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.network.dto.*;
import io.aoitori043.aoitoriproject.AoitoriProject;
import io.aoitori043.aoitoriproject.CanaryClientImpl;
import io.aoitori043.aoitoriproject.database.orm.SQLClient;
import io.aoitori043.aoitoriproject.script.AoitoriPlayerJoinEvent;
import io.aoitori043.aoitoriproject.script.AoitoriPlayerQuitEvent;
import kilim.Task;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.concurrent.ConcurrentHashMap;

public class MapDatabaseClient {

    public static SQLClient sqlClient = CanaryClientImpl.sqlClient;

    public static void init() {

    }

    public static ConcurrentHashMap<String, MapPlayerProfile> cache = new ConcurrentHashMap<>();

    public static MapPlayerProfile getMapPlayerProfile(String playerName){
        MapPlayerProfile mapPlayerProfile = cache.get(playerName);
        if (mapPlayerProfile!=null) return mapPlayerProfile;
        Player player = Bukkit.getPlayer(playerName);
        return player!=null && player.isOnline() ? new MapPlayerProfile(player) : null;
    }

    public static class DatabaseListener implements Listener {

        @EventHandler
        public void onAoitoriJoin(AoitoriPlayerJoinEvent e){
            Player player = e.getPlayer();
            MapPlayerProfile mapPlayerProfile = new MapPlayerProfile(e.getPlayer());
            cache.put(player.getName(), mapPlayerProfile);
            AoitoriProject.kilimScheduler.forkJoinExecute(()->{
                Task.sleep(MapConfigHandler.sendDataDelay);
                MapPlayerProfile currentProfile = cache.get(player.getName());
                if (currentProfile == null) return;
                World world = player.getWorld();
                OperateMapDataDTO.builder()
                        .type(OperateMapDataDTO.MapOperateType.STOP_MAPPING)
                        .build()
                        .send(player);
                WorldRequestDataDTO
                        .builder()
                        .worldName(world.getName())
                        .build()
                        .send(player);
                RenderWorldDataDTO.builder()
                        .worldName(world.getName())
                        .build()
                        .send(player);
                TileFileDataDTO.builder()
                        .tile(MapConfigHandler.tile)
                        .build()
                        .send(player);
                OperateMapDataDTO.builder()
                        .type(MapConfigHandler.displayAuthorLogo ? OperateMapDataDTO.MapOperateType.DISPLAY_AUTHOR_LOGO : OperateMapDataDTO.MapOperateType.NOT_DISPLAY_AUTHOR_LOGO)
                        .build()
                        .send(player);
                new VersionDataDTO().send(player);
                ImageEncryptor.sendEncryptor(player);
                OverlayManager.sendAllOverlayData(player);
                currentProfile.getGuiManager().openInitGui();
            });
        }

        @EventHandler
        public void onAoitoriQuit(AoitoriPlayerQuitEvent e){
            String name = e.getPlayer().getName();
            MapPlayerProfile mapPlayerProfile = cache.get(name);
            mapPlayerProfile.quit = true;
            cache.remove(name);
        }
    }

}
