package io.aoitori043.aoitorimapplugin.database;

import io.aoitori043.aoitorimapplugin.business.GuiManager;
import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  21:47
 * @Description: ?
 */
@Getter
public class MapPlayerProfile {

    public MapPlayerProfile(Player player) {
        this.playerName = player.getName();
        this.overlayManager = new OverlayManager(player);
        this.dataMap = new HashMap<>();
        this.guiManager = GuiManager.builder()
                .player(player)
                .build();

    }

    String playerName;
    @Setter
    String renderWorld;
    Map<String,Object> dataMap;
    OverlayManager overlayManager;
    GuiManager guiManager;




}
