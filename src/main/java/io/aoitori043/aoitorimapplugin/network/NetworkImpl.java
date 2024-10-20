package io.aoitori043.aoitorimapplugin.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiComponent;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.MapComponentDeserializer;
import io.aoitori043.aoitorimapplugin.network.serialize.MapDataDTODeserializer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @Author: natsumi
 * @CreateTime: 2024-10-02  19:25
 * @Description: ?
 */
public class NetworkImpl implements PluginMessageListener, Listener {

    private static final ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
    public static final String CHANNEL_NAME = "aoitorimap";
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DataDTO.class, new MapDataDTODeserializer())
            .registerTypeAdapter(GuiComponent.class, new MapComponentDeserializer())
            .create();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] data) {
        if (!channel.equals(CHANNEL_NAME)) return;
        singleExecutor.execute(()->{
            String text = new String(data, StandardCharsets.UTF_8);
            DataDTO dataDTO = gson.fromJson(text, DataDTO.class);
            dataDTO.onServerReceived(player);
        });
    }

    public static void sendPluginMessageToPlayer(Player player,DataDTO dataDTO){
        if (dataDTO == null){
            return;
        }
        singleExecutor.execute(()->{
            player.sendPluginMessage(AoitoriMapPlugin.plugin, CHANNEL_NAME, gson.toJson(dataDTO).getBytes(StandardCharsets.UTF_8));
        });
    }



}
