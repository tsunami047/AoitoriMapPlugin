package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static io.aoitori043.aoitorimapplugin.AoitoriMapPlugin.plugin;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-13  14:27
 * @Description: ?
 */
public class JourneyMapImpl implements PluginMessageListener {

    private static final int BUFFER_SIZE_MINIMUM = 44;
    private static final byte VOXELMAP_MAGIC_NUMBER = 42;


    public static final String WORLD_ID_CHANNEL = "worldinfo:world_id";

    public void init(){
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, WORLD_ID_CHANNEL);
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, WORLD_ID_CHANNEL, this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (channel.toLowerCase().equals(WORLD_ID_CHANNEL)) {
            this.sendWorldId(player.getWorld().getName(), player);
        }
    }


    public void sendWorldId(String worldID, Player player) {
        try {
            sendPacket(player, 0, worldID.getBytes(StandardCharsets.UTF_8), WORLD_ID_CHANNEL);
        } catch (Exception e) {
            plugin.getLogger().severe("Unsupported encoding: UTF-8");
        }
    }

    public void sendPacket(Player player, int packetID, byte[] data, String channel) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE_MINIMUM + data.length);
        buffer.put((byte) packetID).put(VOXELMAP_MAGIC_NUMBER).put((byte) data.length).put(data);
        player.sendPluginMessage(plugin, channel, buffer.array());
    }
}
