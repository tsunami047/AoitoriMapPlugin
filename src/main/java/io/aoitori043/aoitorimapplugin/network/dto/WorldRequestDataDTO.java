package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import org.bukkit.entity.Player;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-13  14:37
 * @Description: ?
 */
public class WorldRequestDataDTO extends DataDTO {

    String worldName;

    @Builder
    public WorldRequestDataDTO(String worldName) {
        super(DataDTOType.WORLD_REQUEST);
        this.worldName = worldName;
    }

    @Override
    public void onServerReceived(Player player) {
        if (worldName == null){
            this.worldName = player.getWorld().getName();
            this.send(player);
        }
    }
}
