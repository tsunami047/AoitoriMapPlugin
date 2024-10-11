package io.aoitori043.aoitorimapplugin.commands;

import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import io.aoitori043.aoitorimapplugin.commands.sub.MapCommandDisplay;
import io.aoitori043.aoitorimapplugin.commands.sub.MapCommandReload;
import io.aoitori043.aoitorimapplugin.commands.sub.MapCommandTest;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.config.mapper.OverlayMapper;
import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.database.MapPlayerProfile;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import io.aoitori043.aoitoriproject.command.*;
import io.aoitori043.syncdistribute.rmi.data.PersistentDataAccess;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;


/**
 * @Author: natsumi
 * @CreateTime: 2024-03-23  13:29
 * @Description: ?
 */
@BasicCommandParameter
public class MapBasicCommand extends BasicCommand {

    public MapBasicCommand(JavaPlugin plugin) {
        super(plugin.getName(), plugin);
    }


    public String getPrefix() {
        return MapConfigHandler.instance.pluginPrefix;
    }

    @Override
    public Class<? extends SubCommand>[] getSubCommands() {
        return new Class[]{
                MapCommandDisplay.class,
                MapCommandTest.class,
                MapCommandReload.class
        };
    }

    @Override
    public List<String> getAllAlias() {
        return Arrays.asList("map","am");
    }

    @Override
    public void sendMessage(CommandSender sender, String msg) {
        if (sender instanceof Player) {
            sender.sendMessage(msg.replaceAll("&", "§"));
        } else {
            plugin.getLogger().info(msg.replaceAll("§.", ""));
        }
    }

    @Parameter(argument = "open", help = "打开地图")
    @ParameterSpecification(index = 0, tip = "player", type = ParameterSpecification.Type.Player,nullable = true)
    public void open(CommandSender sender, List<SubCommand.ArgumentHelper> arguments) {
        OperateMapDataDTO.builder()
                .type(OperateMapDataDTO.MapOperateType.OPEN)
                .build()
                .send(!arguments.isEmpty() ? arguments.get(0).getAsPlayer() : (Player) sender);
    }
}
