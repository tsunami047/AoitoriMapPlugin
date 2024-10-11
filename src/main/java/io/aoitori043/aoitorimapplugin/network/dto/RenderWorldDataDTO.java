package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;

public class RenderWorldDataDTO extends DataDTO {

    String worldName;

    @Builder
    public RenderWorldDataDTO(String worldName) {
        super(DataDTOType.RenderWorld);
        this.worldName = worldName;
    }
}
