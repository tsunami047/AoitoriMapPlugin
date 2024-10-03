package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitoriproject.utils.PlaceholderAPIUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  21:41
 * @Description: ?
 */
@Getter
public class Actions {

    Player player;

    private Actions(Player player) {
        this.player = player;
    }

    public static Actions createActions(Player player) {
        return new Actions(player);
    }


    public void consoleCommand(String cmd){
        if (cmd == null || cmd.isEmpty()) return;
        String command = PlaceholderAPIUtil.throughPAPI(player, cmd);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),command);
    }

    public String parsePAPIVariables(String arg){
        return PlaceholderAPIUtil.throughPAPI(player, arg);
    }
}
