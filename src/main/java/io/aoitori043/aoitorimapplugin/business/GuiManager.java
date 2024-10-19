package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import lombok.Builder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-19  20:55
 * @Description: ?
 */
public class GuiManager {

    Player player;
    Map<String, GuiMapper> guiMapper;

    @Builder
    public GuiManager(Player player) {
        this.player = player;
        this.guiMapper = new ConcurrentHashMap<>();
    }

    public GuiMapper getGuiMapper(String guiName){
        return guiMapper.get(guiName);
    }


}
