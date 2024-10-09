package io.aoitori043.aoitorimapplugin.commands.sub;

import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.NetworkImpl;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import io.aoitori043.aoitoriproject.command.NotArgument;
import io.aoitori043.aoitoriproject.command.SubArgument;
import io.aoitori043.aoitoriproject.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-09  12:05
 * @Description: ?
 */
@SubArgument(argument = "display")
public class MapCommandDisplay  extends SubCommand {

    @NotArgument(help = "发送overlay")
    public void execute(CommandSender sender, List<ArgumentHelper> arguments) {
        MapPlayerProfile mapPlayerProfile = MapDatabaseClient.getMapPlayerProfile(sender.getName());
        OverlayManager overlayManager = mapPlayerProfile.getOverlayManager();
        for (OverlayMapper value : MapConfigHandler.overlay.values()) {
            overlayManager.addOverlayImpl(value);
        }
    }
}
