package io.aoitori043.aoitorimapplugin.config.mapper;

import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.Inject;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-16  20:36
 * @Description: Injection
 */
@ConfigProperties
public class GuiComponent {

    @Inject(type = Inject.InjectType.INDEX)
    String index;
}
