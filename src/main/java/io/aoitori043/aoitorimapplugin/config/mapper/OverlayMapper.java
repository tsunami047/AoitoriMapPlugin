package io.aoitori043.aoitorimapplugin.config.mapper;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.network.dto.ActionsDataDTO;
import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.Run;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  01:36
 * @Description: ?
 */
@ConfigProperties
@Getter
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

    LinkedHashMap<String,String> actions;

    public OverlayMapper() {
    }

    @Builder
    public OverlayMapper(String index, String enable, String path, double x, double z, String width, String height, List<String> tooltip, String label, int minZoom, int maxZoom, LinkedHashMap<String, String> actions) {
        this.index = index;
        this.enable = enable;
        this.path = path;
        this.x = x;
        this.z = z;
        this.width = width;
        this.height = height;
        this.tooltip = tooltip;
        this.label = label;
        this.minZoom = minZoom;
        this.maxZoom = maxZoom;
        this.actions = actions;
    }

    @Run(after = "actions")
    void load(){
        for (ActionsDataDTO.ClickMethod value : ActionsDataDTO.ClickMethod.values()) {
            String key = value.toString().toLowerCase();
            String script = actions.get(key);
            AoitoriMapPlugin.scriptExecutor.addFunction("overlay_"+this.index+"_"+key,script);
        }
    }
}
