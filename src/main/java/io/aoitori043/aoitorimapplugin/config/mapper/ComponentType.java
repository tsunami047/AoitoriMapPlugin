package io.aoitori043.aoitorimapplugin.config.mapper;

import lombok.Getter;

@Getter
public enum ComponentType {

    LABEL(GuiMapper.MapLabel.class),
    TEXTURE(GuiMapper.MapTexture.class),
    BUTTON(GuiMapper.MapButton.class);

    final Class<? extends GuiComponent> type;

    ComponentType(Class<? extends GuiComponent> type) {
        this.type = type;
    }

}
