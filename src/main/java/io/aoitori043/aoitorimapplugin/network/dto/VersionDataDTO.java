package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.config.DebugUtil;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import org.bukkit.entity.Player;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-21  15:53
 * @Description: ?
 */
public class VersionDataDTO extends DataDTO {

    String version;

    public VersionDataDTO() {
        super(DataDTOType.VERSION);
    }


    @Override
    public void onServerReceived(Player player) {
        DebugUtil.debug(player.getName()+" AoitoriMap version："+version, DebugUtil.DebugLevel.VERBOSE);
    }


}
