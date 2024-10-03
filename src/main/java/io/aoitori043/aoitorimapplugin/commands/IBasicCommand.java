package io.aoitori043.ashcan.commands;

import io.aoitori043.aoitoriproject.command.BasicCommand;
import io.aoitori043.aoitoriproject.command.BasicCommandParameter;
import io.aoitori043.aoitoriproject.command.SubCommand;
import io.aoitori043.ashcan.commands.mapper.SubCommandOpen;
import io.aoitori043.ashcan.commands.mapper.SubCommandReload;
import io.aoitori043.ashcan.config.ConfigHandler;
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
public class IBasicCommand extends BasicCommand {

    public IBasicCommand(JavaPlugin plugin) {
        super(plugin.getName(), plugin);
    }


    public String getPrefix() {
        return ConfigHandler.instance.pluginPrefix;
    }

    @Override
    public Class<? extends SubCommand>[] getSubCommands() {
        return new Class[]{
                SubCommandOpen.class,
                SubCommandReload.class
        };
    }

    @Override
    public List<String> getAllAlias() {
        return Arrays.asList("ashcan");
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
