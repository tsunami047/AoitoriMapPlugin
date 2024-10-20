package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.gui.MapGuiImpl;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-19  20:55
 * @Description: ?
 */
@Getter
public class GuiManager {

    Player player;
    Map<String, MapGuiImpl> mapGuiMap;

    @Builder
    public GuiManager(Player player) {
        this.player = player;
        this.mapGuiMap = new ConcurrentHashMap<>();
    }

    public MapGuiImpl getGuiMapper(String guiName){
        return mapGuiMap.get(guiName);
    }

    public void openInitGui(){
        for (String guiName : MapConfigHandler.permanentGUI) {
            GuiMapper guiMapper = MapConfigHandler.gui.get(guiName);
            if (guiMapper == null) {
                AoitoriMapPlugin.plugin.getLogger().info("不存在界面："+guiName);
                continue;
            }
            MapGuiImpl mapGui = MapGuiImpl.convertTo(guiMapper);
            mapGui.open(player);
        }
    }

}
