package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-04  19:24
 * @Description: ?
 */
@Getter
@ToString(callSuper = true)
public class GuiFileDataDTO extends DataDTO {

    GuiMapper guiMapper;

    @Builder
    public GuiFileDataDTO(GuiMapper guiMapper) {
        super(DataDTOType.GUIFile);
        this.guiMapper = guiMapper;
    }


}
