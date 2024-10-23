package io.aoitori043.aoitorimapplugin.network.serialize;

import io.aoitori043.aoitorimapplugin.network.dto.*;
import lombok.Getter;

/**
*@Author: natsumi
*@CreateTime: 2024-10-02  20:49
*@Description: ?
*/
@Getter
public enum DataDTOType {
    TILE_FILE(TileFileDataDTO.class),
    VERSION(VersionDataDTO.class),
    SECRET_KEY(SecretKeyDataDTO.class),
    UPDATE_COMPONENTS(UpdateComponentsDataDTO.class),
    OPERATE_GUI(GuiOperateDataDTO.class),
    VARIABLE_DATA(VariablesDataDTO.class),
    WORLD_REQUEST(WorldRequestDataDTO.class),
    RENDER_WORLD(RenderWorldDataDTO.class),
    GUIFile(GuiFileDataDTO.class),
    ACTIONS(ActionsDataDTO.class),
    OVERLAY_IMAGE(OverlayImageDataDTO.class),
    LOCATE_ON(LocateOnDataDTO.class),
    OPERATE_MAP(OperateMapDataDTO.class);

    Class<? extends DataDTO> dataDTOClass;

    DataDTOType(Class<? extends DataDTO> dataDTOClass) {
        this.dataDTOClass = dataDTOClass;
    }
}
