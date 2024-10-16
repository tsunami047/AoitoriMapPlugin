package io.aoitori043.aoitorimapplugin.database;

import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  21:47
 * @Description: ?
 */
@Getter
public class MapPlayerProfile {

    public MapPlayerProfile(String playerName) {
        this.playerName = playerName;
        this.overlayManager = new OverlayManager(playerName);
        this.dataMap = new HashMap<>();
    }

    String playerName;
    @Setter
    String renderWorld;
    Map<String,Object> dataMap;
    OverlayManager overlayManager;




}
