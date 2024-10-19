package io.aoitori043.aoitorimapplugin.business.gui;

import io.aoitori043.aoitorimapplugin.business.gui.impl.MapComponent;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import io.aoitori043.aoitorimapplugin.network.dto.GuiFileDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.GuiOperateDataDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.Map;

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
    @Getter
    Map<String, MapComponent> components;

    @Builder
    public MapGuiImpl(String guiName, Map<String, MapComponent> components) {
        this.guiName = guiName;
        this.components = components;
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
