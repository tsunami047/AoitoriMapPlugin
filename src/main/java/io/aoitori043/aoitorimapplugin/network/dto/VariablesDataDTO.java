package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import org.bukkit.entity.Player;

import java.util.Map;

import static io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType.VARIABLE_DATA;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-13  17:13
 * @Description: ?
 */
public class VariablesDataDTO extends DataDTO {

    Map<String,Object> map;

    @Builder
    public VariablesDataDTO( Map<String, Object> map) {
        super(VARIABLE_DATA);
        this.map = map;
    }

}
