package io.aoitori043.aoitorimapplugin.commands.sub;

import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.NetworkImpl;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import io.aoitori043.aoitoriproject.command.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-05  00:30
 * @Description: ?
 */
@SubArgument(argument = "test")
public class MapCommandTest extends SubCommand {

    @NotArgument(help = "测试")
    public void execute(CommandSender sender, List<ArgumentHelper> arguments) {
        Player player = Bukkit.getPlayer(sender.getName());
        NetworkImpl.sendPluginMessageToPlayer(player, OperateMapDataDTO.builder().type(OperateMapDataDTO.MapOperateType.REFRESH).build());
    }
}
