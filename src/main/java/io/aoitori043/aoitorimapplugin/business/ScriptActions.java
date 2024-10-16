package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitoriproject.AoitoriProject;
import io.aoitori043.aoitoriproject.utils.CMDUtils;
import io.aoitori043.aoitoriproject.utils.PlaceholderAPIUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  21:41
 * @Description: ?
 */
@Getter
public class ScriptActions {

    Player player;

    private ScriptActions(Player player) {
        this.player = player;
    }

    public static ScriptActions createActions(Player player) {
        return new ScriptActions(player);
    }

    public String getPoint(String dataName){
        return AoitoriProject.pointManager.get(this.getPlayer().getName(),dataName);
    }


    public void consoleCommand(String cmd){
        if (cmd == null || cmd.isEmpty()) return;
        CMDUtils.performCmd(player.getName(), Collections.singletonList(PlaceholderAPIUtil.throughPAPI(player, cmd)));
    }

    public String parsePAPIVariables(String arg){
        return PlaceholderAPIUtil.throughPAPI(player, arg);
    }
}
