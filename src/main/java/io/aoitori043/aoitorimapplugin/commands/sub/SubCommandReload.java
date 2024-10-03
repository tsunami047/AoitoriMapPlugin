package io.aoitori043.ashcan.commands.mapper;


import io.aoitori043.aoitoriproject.command.*;
import io.aoitori043.ashcan.config.ConfigHandler;
import org.bukkit.command.CommandSender;

import java.util.List;


@SubArgument(argument = "reload")
public class SubCommandReload extends SubCommand {

    @NotArgument(help = "重载插件配置")
    @ExecutionStartMessage(message = "开始重载...")
    @ExecutionEndMessage(message = "重载完成，耗时 %time%s")
    public void execute(CommandSender sender, List<ArgumentHelper> arguments) {
        ConfigHandler.load();

    }

}

