package io.aoitori043.aoitorimapplugin.config.mapper;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.AoitoriMapAPI;
import io.aoitori043.aoitorimapplugin.network.dto.ActionsDataDTO;
import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.GetClassifyMapping;
import io.aoitori043.aoitoriproject.config.NonConfigProperty;
import io.aoitori043.aoitoriproject.config.Run;
import lombok.Getter;

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
    LinkedHashMap<String,GuiMapper> guiMapper;

    //override methods
    @NonConfigProperty
    LinkedHashMap<String,String> actions;

    @Getter
    public static class MapLabel extends GuiComponent{
        String index;
        String type;
        String enable;
        String locX;
        String locY;
        String locZ;
        List<String> texts;
    }

    @Getter
    public static class MapTexture extends GuiComponent{
        String index;
        String type;
        String enable;
        String path;
        String locX;
        String locY;
        String locZ;
        String width;
        String height;
    }

    @Getter
    public static class MapButton extends GuiComponent{
        String index;
        String type;
        String enable;
        String path;
        String locX;
        String locY;
        String locZ;
        String width;
        String height;

        LinkedHashMap<String,String> actions;
    }

    @Run(after = "guiMapper")
    void load(){
        for (Map.Entry<String, GuiMapper> entry : guiMapper.entrySet()) {
            GuiMapper guiMapper = entry.getValue();
            for (ActionsDataDTO.ClickMethod value : ActionsDataDTO.ClickMethod.values()) {
                String key = value.toString().toLowerCase();
                String script = guiMapper.getActions().get(key);
                AoitoriMapPlugin.scriptExecutor.addFunction(this.index+"_"+entry.getKey()+"_"+key,script);
            }
        }
    }




}
