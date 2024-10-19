package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.OverlayImageDataDTO;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-04  18:18
 * @Description: ?
 */
public class OverlayManager {

    Player player;
    @Getter
    LinkedHashMap<String, OverlayMapper> overMap;

    public OverlayManager(Player player) {
        this.overMap = new LinkedHashMap<>();
        this.player = player;
    }

    public static void sendAllOverlayData(Player player) {
        MapPlayerProfile mapPlayerProfile = MapDatabaseClient.getMapPlayerProfile(player.getName());
        OverlayManager overlayManager = mapPlayerProfile.getOverlayManager();
        for (OverlayMapper value : MapConfigHandler.overlay.values()) {
            overlayManager.addOverlayImpl(value);
        }
        OperateMapDataDTO.builder().type(OperateMapDataDTO.MapOperateType.REFRESH).build().send(player);
    }

    public void addOverlayImpl(OverlayMapper overlay){
        String index = overlay.getIndex();
        if (index == null) throw new NullPointerException("Cannot be null pointer");
        overMap.put(index,overlay);
        OverlayImageDataDTO.builder()
                .overlay(overlay)
                .enable(true)
                .build()
                .send(player);
    }

    public void removeOverlayImpl(String index){
        if (index == null) throw new NullPointerException("Cannot be null pointer");
        overMap.remove(index);
        OverlayImageDataDTO.builder()
                .overlay(OverlayMapper.builder()
                        .index(index)
                        .build()
                )
                .enable(false)
                .build()
                .send(player);
    }
}
