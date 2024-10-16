package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-13  17:13
 * @Description: ?
 */
public class VariablesDataDTO extends DataDTO {

    public VariablesDataDTO() {
        super(DataDTOType.VARIABLE_DATA);
    }

    Map<String,Object> map;

    @Override
    public void send(Player player) {
        MapPlayerProfile mapPlayerProfile = MapDatabaseClient.getMapPlayerProfile(player.getName());
        Map<String, Object> dataMap = mapPlayerProfile.getDataMap();
        dataMap.putAll(map);
        super.send(player);
    }
}
