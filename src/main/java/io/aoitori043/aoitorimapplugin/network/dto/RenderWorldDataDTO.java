package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import org.bukkit.entity.Player;

public class RenderWorldDataDTO extends DataDTO {

    String worldName;

    @Builder
    public RenderWorldDataDTO(String worldName) {
        super(DataDTOType.RENDER_WORLD);
        this.worldName = worldName;
    }

}
