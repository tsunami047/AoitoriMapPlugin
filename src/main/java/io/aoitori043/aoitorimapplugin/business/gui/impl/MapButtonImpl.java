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

public class MapButtonImpl extends MapComponent{

    @Getter
    @Setter
    GuiMapper.MapButton configMapping;

    @Override
    public GuiComponent getGuiComponent() {
        return configMapping;
    }

    public MapButtonImpl setWidth(String width) {
        configMapping.setWidth(width);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setPressedPath(String pressedPath) {
        configMapping.setPressedPath(pressedPath);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setHoverPath(String hoverPath) {
        configMapping.setHoverPath(hoverPath);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setHeight(String height) {
        configMapping.setHeight(height);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setDefaultPath(String defaultPath) {
        configMapping.setDefaultPath(defaultPath);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setAlpha(String alpha) {
        configMapping.setAlpha(alpha);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setBgAlpha(String bgAlpha) {
        configMapping.setBgAlpha(bgAlpha);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setBgColor(String bgColor) {
        configMapping.setBgColor(bgColor);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setColor(String color) {
        configMapping.setColor(color);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setFontScala(String fontScala) {
        configMapping.setFontScala(fontScala);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setFontShadow(String fontShadow) {
        configMapping.setFontShadow(fontShadow);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setHAlign(String hAlign) {
        configMapping.setHAlign(hAlign);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setRotation(String rotation) {
        configMapping.setRotation(rotation);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setTexts(List<String> texts) {
        configMapping.setTexts(texts);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setVAlign(String vAlign) {
        configMapping.setVAlign(vAlign);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setZ(String z) {
        configMapping.setZ(z);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setY(String y) {
        configMapping.setY(y);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setX(String x) {
        configMapping.setX(x);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setType(ComponentType type) {
        configMapping.setType(type);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setTooltips(List<String> tooltips) {
        configMapping.setTooltips(tooltips);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setIndex(String index) {
        configMapping.setIndex(index);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setGuiName(String guiName) {
        configMapping.setGuiName(guiName);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setEnable(String enable) {
        configMapping.setEnable(enable);
        mapGui.updateComponents(this);
        return this;
    }


    public MapButtonImpl setActions(LinkedHashMap<String, String> actions) {
        configMapping.setActions(actions);
        mapGui.updateComponents(this);
        return this;
    }


}
