package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.config.mapper.TileMapper;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;

import java.util.LinkedHashMap;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-23  20:05
 * @Description: ?
 */
public class TileFileDataDTO extends DataDTO {

    LinkedHashMap<String, TileMapper> tile;

    @Builder
    public TileFileDataDTO(LinkedHashMap<String, TileMapper> tile) {
        super(DataDTOType.TILE_FILE);
        this.tile = tile;
    }
}
