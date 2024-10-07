package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  21:45
 * @Description: ?
 */
public class AoitoriMapAPI {

    public static MapPlayerProfile getPlayerProfile(String playerName){
        return MapDatabaseClient.getMapPlayerProfile(playerName);
    }
}
