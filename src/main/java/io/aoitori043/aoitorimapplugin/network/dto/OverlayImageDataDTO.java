package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import lombok.Getter;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  01:20
 * @Description: ?
 */
@Getter
public class OverlayImageDataDTO extends DataDTO {


    boolean enable;
    OverlayMapper overlay;

    @Builder
    public OverlayImageDataDTO(boolean enable, OverlayMapper overlay) {
        super(DataDTOType.OVERLAY_IMAGE);
        this.enable = enable;
        this.overlay = overlay;
    }
}
