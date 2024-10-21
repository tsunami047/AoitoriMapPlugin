package io.aoitori043.aoitorimapplugin.business.gui;

import io.aoitori043.aoitorimapplugin.business.GuiManager;
import io.aoitori043.aoitorimapplugin.business.gui.impl.MapButtonImpl;
import io.aoitori043.aoitorimapplugin.business.gui.impl.MapComponent;
import io.aoitori043.aoitorimapplugin.business.gui.impl.MapLabelImpl;
import io.aoitori043.aoitorimapplugin.business.gui.impl.MapTextureImpl;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiComponent;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import io.aoitori043.aoitorimapplugin.network.dto.GuiFileDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.GuiOperateDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.UpdateComponentsDataDTO;
import io.aoitori043.aoitoriproject.config.loader.YamlMapping;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-19  21:09
 * @Description: ?
 */

public class MapGuiImpl {


    @Getter
    @Setter
    String guiName;
    boolean isOpened;
    boolean hud;
    Player player;
    @Getter
    Map<String, MapComponent> components;


    @Builder
    public MapGuiImpl(String guiName, Map<String, MapComponent> components, boolean hud) {
        this.guiName = guiName;
        this.components = components;
        this.hud = hud;
    }


    public void updateComponents(MapComponent mapComponent){
        if (isOpened){
            GuiComponent guiComponent = mapComponent.getGuiComponent();
            UpdateComponentsDataDTO.builder()
                    .guiComponent(guiComponent)
                    .guiName(guiName)
                    .componentName(guiComponent.getIndex())
                    .build()
                    .send(player);
        }
    }

    public static MapGuiImpl convertTo(GuiMapper guiMapper){
        Map<String, MapComponent> components = new ConcurrentHashMap<>();
        for (Map.Entry<String, GuiComponent> entry : guiMapper.getComponents().entrySet()) {
            GuiComponent guiComponent = entry.getValue();
            switch (guiComponent.getType()) {
                case BUTTON:{
                    MapButtonImpl mapButton = new MapButtonImpl();
                    mapButton.setConfigMapping((GuiMapper.MapButton) guiComponent);
                    components.put(entry.getKey(), mapButton);
                    break;
                }
                case TEXTURE:{
                    MapTextureImpl mapTexture = new MapTextureImpl();
                    mapTexture.setConfigMapping((GuiMapper.MapTexture) guiComponent);
                    components.put(entry.getKey(), mapTexture);
                    break;
                }
                case LABEL:{
                    MapLabelImpl mapLabel = new MapLabelImpl();
                    mapLabel.setConfigMapping((GuiMapper.MapLabel) guiComponent);
                    components.put(entry.getKey(), mapLabel);
                    break;
                }
            }
        }
        return MapGuiImpl.builder()
                .components(components)
                .guiName(guiMapper.getIndex())
                .build();
    }

    public static MapGuiImpl copyFromConfig(YamlConfiguration config,String guiName){
        GuiMapper guiMapper = new GuiMapper();
        YamlMapping.loadFromConfig(guiMapper,guiMapper,config,guiName);
        return convertTo(guiMapper);
    }

    public <T extends MapComponent> T getMapComponentImpl(String componentName, Class<T> clazz){
        MapComponent mapComponent = components.get(componentName);
        if (mapComponent == null) return null;
        return (T) mapComponent;
    }

    public void open(Player player){
        GuiMapper guiMapper = GuiMapper.convertToGuiMapper(this);
        GuiFileDataDTO.builder()
                .guiMapper(guiMapper)
                .build()
                .send(player);
        GuiOperateDataDTO.builder()
                .guiName(this.guiName)
                .operateType(GuiOperateDataDTO.OperateType.OPEN)
                .build()
                .send(player);
        this.player = player;
        this.isOpened = true;
    }

    public void close(Player player){
        GuiOperateDataDTO.builder()
                .guiName(this.guiName)
                .operateType(GuiOperateDataDTO.OperateType.CLOSE)
                .build()
                .send(player);
        this.isOpened = false;
    }
}
