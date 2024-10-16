package io.aoitori043.aoitorimapplugin.config.mapper;

import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.Inject;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-16  20:36
 * @Description: Injection
 */
@ConfigProperties
@Getter
public class GuiComponent {

    @Inject(type = Inject.InjectType.INDEX)
    String index;
    String enable;
    ComponentType type;
    String x;
    String y;
    String z;
    List<String> tooltips;

    LinkedHashMap<String,String> actions;
}
