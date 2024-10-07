package io.aoitori043.aoitorimapplugin.commands;

import io.aoitori043.aoitorimapplugin.commands.sub.MapCommandReload;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitoriproject.command.BasicCommand;
import io.aoitori043.aoitoriproject.command.BasicCommandParameter;
import io.aoitori043.aoitoriproject.command.SubCommand;
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
}
