package io.aoitori043.aoitorimapplugin.config.mapper;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.AoitoriMapAPI;
import io.aoitori043.aoitorimapplugin.business.gui.MapGuiImpl;
import io.aoitori043.aoitorimapplugin.business.gui.impl.MapComponent;
import io.aoitori043.aoitorimapplugin.network.dto.ActionsDataDTO;
import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.GetClassifyMapping;
import io.aoitori043.aoitoriproject.config.NonConfigProperty;
import io.aoitori043.aoitoriproject.config.Run;
import lombok.Getter;
import lombok.Setter;

import java.beans.Transient;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  01:47
 * @Description: ?
 */
@ConfigProperties
@Getter
public class GuiMapper {

    String index;
    @GetClassifyMapping(
            anchor = "type",
            appoint = {
                    @GetClassifyMapping.ClassDesignation(key = "label",value = MapLabel.class),
                    @GetClassifyMapping.ClassDesignation(key = "texture",value = MapTexture.class),
                    @GetClassifyMapping.ClassDesignation(key = "button",value = MapButton.class)
            }
    )
    LinkedHashMap<String,GuiComponent> components;

    public static GuiMapper convertToGuiMapper(MapGuiImpl mapGui){
        GuiMapper guiMapper = new GuiMapper();
        guiMapper.index = mapGui.getGuiName();
        guiMapper.components = new LinkedHashMap<>();
        for (Map.Entry<String, MapComponent> entry : mapGui.getComponents().entrySet()) {
            MapComponent value = entry.getValue();
            guiMapper.components.put(entry.getKey(),(GuiComponent)value);
        }
        return guiMapper;
    }

    @Getter
    public static class MapLabel extends GuiComponent{
        String hAlign;
        String vAlign;
        String bgColor;
        String bgAlpha;
        String color;
        String alpha;
        String fontScala;
        String fontShadow;
        String rotation;
        List<String> texts;
    }

    @Getter
    public static class MapTexture extends GuiComponent{
        String path;
        String width;
        String height;
        String rotation;
        String alpha;
    }

    @Getter
    public static class MapButton extends MapLabel{
        String defaultPath;
        String pressedPath;
        String hoverPath;
        String width;
        String height;
    }

    @Run(after = "guiMapper")
    void load(){
        for (Map.Entry<String, GuiComponent> entry : components.entrySet()) {
            GuiComponent guiComponent = entry.getValue();
            for (ActionsDataDTO.ClickMethod value : ActionsDataDTO.ClickMethod.values()) {
                String key = value.toString().toLowerCase();
                String script = guiComponent.getActions().get(key);
                AoitoriMapPlugin.scriptExecutor.addFunction(this.index+"_"+entry.getKey()+"_"+key,script);
            }
        }
    }




}
