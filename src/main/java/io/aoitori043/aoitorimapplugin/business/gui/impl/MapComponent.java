package io.aoitori043.aoitorimapplugin.business.gui.impl;

import io.aoitori043.aoitorimapplugin.business.gui.MapGuiImpl;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiComponent;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-19  22:05
 * @Description: ?
 */
public abstract class MapComponent {

    MapGuiImpl mapGui;

    public MapGuiImpl getMapGui() {
        return mapGui;
    }

    public MapComponent setMapGui(MapGuiImpl mapGui) {
        this.mapGui = mapGui;
        return this;
    }

    public abstract GuiComponent getGuiComponent();


}
