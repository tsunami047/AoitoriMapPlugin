package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Data;
import lombok.Getter;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-02  19:42
 * @Description: ?
 */
@Getter
@Data
public class DataDTO {

    DataDTOType dataType;

    public DataDTO(DataDTOType dataType) {
        this.dataType = dataType;
    }
}
