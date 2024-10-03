package io.aoitori043.aoitorimapplugin.network.serialize;

import io.aoitori043.aoitorimapplugin.network.dto.ActionsDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.LocateOnDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.OverlayImageDataDTO;
import lombok.Getter;

/**
*@Author: natsumi
*@CreateTime: 2024-10-02  20:49
*@Description: ?
*/
@Getter
public enum DataDTOType {
    ACTIONS(ActionsDataDTO.class),
    OVERLAY_IMAGE(OverlayImageDataDTO.class),
    LOCATE_ON(LocateOnDataDTO.class),
    OPERATE_MAP(OperateMapDataDTO.class);

    Class<? extends DataDTO> dataDTOClass;

    DataDTOType(Class<? extends DataDTO> dataDTOClass) {
        this.dataDTOClass = dataDTOClass;
    }
}
