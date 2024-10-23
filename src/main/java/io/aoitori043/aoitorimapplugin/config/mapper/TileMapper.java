package io.aoitori043.aoitorimapplugin.config.mapper;

import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.Inject;

import java.util.List;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-23  19:55
 * @Description: ?
 */
@ConfigProperties
public class TileMapper {

    @Inject(type = Inject.InjectType.INDEX)
    String worldName;
    boolean defaultDisplay;
    List<String> region;
}
