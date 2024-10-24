package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import lombok.Getter;


@Getter
public class GuiOperateDataDTO extends DataDTO {

    public enum OperateType {
        OPEN,
        CLOSE
    }

    String guiName;
    OperateType operateType;
    boolean hud;

    @Builder
    public GuiOperateDataDTO(String guiName, OperateType operateType, boolean hud) {
        super(DataDTOType.OPERATE_GUI);
        this.guiName = guiName;
        this.operateType = operateType;
        this.hud = hud;
    }
}
