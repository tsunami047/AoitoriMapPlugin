package io.aoitori043.aoitorimapplugin.database;

import io.aoitori043.aoitoriproject.CanaryClientImpl;
import io.aoitori043.aoitoriproject.database.orm.SQLClient;
import io.aoitori043.aoitoriproject.script.AoitoriPlayerJoinEvent;
import io.aoitori043.aoitoriproject.script.AoitoriPlayerQuitEvent;
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
        return new MapPlayerProfile(playerName);
    }

    public static class DatabaseListener implements Listener {

        @EventHandler
        public void onAoitoriJoin(AoitoriPlayerJoinEvent e){
            Player player = e.getPlayer();
            cache.put(player.getName(),new MapPlayerProfile(e.getPlayer().getName()));
        }

        @EventHandler
        public void onAoitoriQuit(AoitoriPlayerQuitEvent e){
            cache.remove(e.getPlayer().getName());
        }
    }

}
