package io.aoitori043.aoitorimapplugin.business.gui.impl;

import io.aoitori043.aoitorimapplugin.business.gui.MapGuiImpl;
import io.aoitori043.aoitorimapplugin.config.mapper.ComponentType;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiComponent;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-19  22:14
 * @Description: ?
 */
public class MapTextureImpl extends MapComponent{

    @Setter
    @Getter
    GuiMapper.MapTexture configMapping;

    @Override
    public GuiComponent getGuiComponent() {
        return configMapping;
    }
    
    public MapTextureImpl setAlpha(String alpha) {
        configMapping.setAlpha(alpha);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setHeight(String height) {
        configMapping.setHeight(height);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setPath(String path) {
        configMapping.setPath(path);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setRotation(String rotation) {
        configMapping.setRotation(rotation);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setWidth(String width) {
        configMapping.setWidth(width);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setActions(LinkedHashMap<String, String> actions) {
        configMapping.setActions(actions);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setEnable(String enable) {
        configMapping.setEnable(enable);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setGuiName(String guiName) {
        configMapping.setGuiName(guiName);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setIndex(String index) {
        configMapping.setIndex(index);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setTooltips(List<String> tooltips) {
        configMapping.setTooltips(tooltips);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setType(ComponentType type) {
        configMapping.setType(type);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setX(String x) {
        configMapping.setX(x);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setY(String y) {
        configMapping.setY(y);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapTextureImpl setZ(String z) {
        configMapping.setZ(z);
        mapGui.updateComponents(this);
        return this;
    }

}
