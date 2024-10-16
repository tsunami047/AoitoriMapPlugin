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
    String world;
    String path;
    String x;
    //    String y;
    String z;
    String width;
    String height;
    List<String> tooltip;
    String minZoom;
    String maxZoom;
    public String labelText;
    public String labelScale;
    public String labelColor;
    public String labelBackgroundColor;
    public String labelOpacity;
    public String labelBackgroundOpacity;
    public String labelFontShadow;
    public String labelMinZoom;
    public String labelMaxZoom;
    public String labelOffsetX;
    public String labelOffsetY;

    LinkedHashMap<String,String> actions;

    public OverlayMapper() {
    }

    @Builder
    public OverlayMapper(String index, String enable, String world, String path, String x, String z, String width, String height, List<String> tooltip, String minZoom, String maxZoom, String labelText, String labelScale, String labelColor, String labelBackgroundColor, String labelOpacity, String labelBackgroundOpacity, String labelFontShadow, String labelMinZoom, String labelMaxZoom, String labelOffsetX, String labelOffsetY, LinkedHashMap<String, String> actions) {
        this.index = index;
        this.enable = enable;
        this.world = world;
        this.path = path;
        this.x = x;
        this.z = z;
        this.width = width;
        this.height = height;
        this.tooltip = tooltip;
        this.minZoom = minZoom;
        this.maxZoom = maxZoom;
        this.labelText = labelText;
        this.labelScale = labelScale;
        this.labelColor = labelColor;
        this.labelBackgroundColor = labelBackgroundColor;
        this.labelOpacity = labelOpacity;
        this.labelBackgroundOpacity = labelBackgroundOpacity;
        this.labelFontShadow = labelFontShadow;
        this.labelMinZoom = labelMinZoom;
        this.labelMaxZoom = labelMaxZoom;
        this.labelOffsetX = labelOffsetX;
        this.labelOffsetY = labelOffsetY;
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
