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
 * @CreateTime: 2024-10-19  21:41
 * @Description: ?
 */
@Setter
@Getter
public class MapLabelImpl extends MapComponent {

    GuiMapper.MapLabel configMapping;

    @Override
    public GuiComponent getGuiComponent() {
        return configMapping;
    }

    public MapLabelImpl setAlpha(String alpha) {
        configMapping.setAlpha(alpha);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setBgAlpha(String bgAlpha) {
        configMapping.setBgAlpha(bgAlpha);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setBgColor(String bgColor) {
        configMapping.setBgColor(bgColor);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setColor(String color) {
        configMapping.setColor(color);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setFontScala(String fontScala) {
        configMapping.setFontScala(fontScala);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setFontShadow(String fontShadow) {
        configMapping.setFontShadow(fontShadow);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setHAlign(String hAlign) {
        configMapping.setHAlign(hAlign);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setRotation(String rotation) {
        configMapping.setRotation(rotation);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setTexts(List<String> texts) {
        configMapping.setTexts(texts);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setVAlign(String vAlign) {
        configMapping.setVAlign(vAlign);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setActions(LinkedHashMap<String, String> actions) {
        configMapping.setActions(actions);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setEnable(String enable) {
        configMapping.setEnable(enable);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setGuiName(String guiName) {
        configMapping.setGuiName(guiName);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setIndex(String index) {
        configMapping.setIndex(index);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setTooltips(List<String> tooltips) {
        configMapping.setTooltips(tooltips);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setType(ComponentType type) {
        configMapping.setType(type);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setX(String x) {
        configMapping.setX(x);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setY(String y) {
        configMapping.setY(y);
        mapGui.updateComponents(this);
        return this;
    }

    
    public MapLabelImpl setZ(String z) {
        configMapping.setZ(z);
        mapGui.updateComponents(this);
        return this;
    }


}
