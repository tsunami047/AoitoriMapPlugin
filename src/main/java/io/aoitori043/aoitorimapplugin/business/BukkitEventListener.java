package io.aoitori043.aoitorimapplugin.business;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-05  00:31
 * @Description: ?
 */
public class BukkitEventListener implements Listener {

    @EventHandler
    public void onPlayerWorldSwitch(PlayerChangedWorldEvent e){
        Player player = e.getPlayer();
        World world = player.getWorld();

    }
}
