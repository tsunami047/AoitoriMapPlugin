package io.aoitori043.aoitorimapplugin.network.dto;

import lombok.Getter;

/**
*@Author: natsumi
*@CreateTime: 2024-10-02  20:49
*@Description: ?
*/
@Getter
public enum DataDTOType {
    LOCATE_ON(LocateOnDataDTO.class),
    OPERATE_MAP(OperateMapDataDTO.class);

    Class<? extends DataDTO> dataDTOClass;

    DataDTOType(Class<? extends DataDTO> dataDTOClass) {
        this.dataDTOClass = dataDTOClass;
    }
}
