package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Getter;

import java.util.List;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  01:20
 * @Description: ?
 */
@Getter
public class OverlayImageDataDTO extends DataDTO {


    public OverlayImageDataDTO() {
        super(DataDTOType.OVERLAY_IMAGE);
    }

    String path;
    String world;
    double x;
    double z;
    int width;
    int height;
    List<String> tooltip;
    String label;

    int minZoom;
    int maxZoom;


}
