package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.config.mapper.GuiComponent;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-20  13:11
 * @Description: ?
 */
@Getter
@ToString(callSuper = true)
public class UpdateComponentsDataDTO extends DataDTO {
    String guiName;
    String componentName;
    GuiComponent guiComponent;

    @Builder
    public UpdateComponentsDataDTO( String guiName, String componentName, GuiComponent guiComponent) {
        super(DataDTOType.UPDATE_COMPONENTS);
        this.guiName = guiName;
        this.componentName = componentName;
        this.guiComponent = guiComponent;
    }
}
