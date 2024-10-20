package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.entity.Player;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-02  19:41
 * @Description: ?
 */
@Getter
@ToString(callSuper = true)
public class OperateMapDataDTO extends DataDTO {

    public enum MapOperateType{
        OPEN,
        CLOSE,
        REFRESH,
        STOP_MAPPING,
        START_MAPPING,
        ENCRYPT
    }

    @Builder
    public OperateMapDataDTO(MapOperateType type) {
        super(DataDTOType.OPERATE_MAP);
        this.type = type;
    }

    public MapOperateType type;

    @Override
    public void onServerReceived(Player player) {
        MapPlayerProfile mapPlayerProfile = MapDatabaseClient.getMapPlayerProfile(player.getName());
        mapPlayerProfile.onMapStatusChange(type);
        switch (this.type) {
            case CLOSE:{
                String worldName = player.getWorld().getName();
                if (!worldName.equals(mapPlayerProfile.getRenderWorld())){
                    RenderWorldDataDTO
                            .builder()
                            .worldName(worldName)
                            .build()
                            .send(player);
                }
                break;
            }
        }
    }

    @Override
    public void send(Player player) {
        MapPlayerProfile mapPlayerProfile = MapDatabaseClient.getMapPlayerProfile(player.getName());
        mapPlayerProfile.onMapStatusChange(type);
        super.send(player);
    }
}
