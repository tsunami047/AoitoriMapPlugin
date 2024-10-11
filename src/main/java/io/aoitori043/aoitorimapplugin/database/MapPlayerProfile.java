package io.aoitori043.aoitorimapplugin.database;

import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import lombok.Getter;

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
    }

    String playerName;
    OverlayManager overlayManager;




}
