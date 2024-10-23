package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitorimapplugin.network.dto.WorldRequestDataDTO;
import io.aoitori043.aoitoriproject.AoitoriProject;
import kilim.Task;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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
        WorldRequestDataDTO
                .builder()
                .worldName(world.getName())
                .build()
                .send(player);
    }

}
