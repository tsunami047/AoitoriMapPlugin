package io.aoitori043.aoitorimapplugin.config.mapper;

import io.aoitori043.aoitoriproject.config.ConfigProperties;

import java.util.List;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  01:36
 * @Description: ?
 */
@ConfigProperties
public class OverlayMapper {

    String index;
    String enable;
    String path;
    double x;
    double z;
    String width;
    String height;
    List<String> tooltip;
    String label;
    int minZoom;
    int maxZoom;

}
