package io.aoitori043.aoitorimapplugin.database;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.GuiManager;
import io.aoitori043.aoitorimapplugin.business.OverlayManager;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.network.dto.LocateOnDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.RenderWorldDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.VariablesDataDTO;
import io.aoitori043.aoitoriproject.AoitoriProject;
import kilim.Task;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  21:47
 * @Description: ?
 */
@Getter
public class MapPlayerProfile {

    public MapPlayerProfile(Player player) {
        this.player = player;
        this.overlayManager = new OverlayManager(player);
        this.dataMap = new HashMap<>();
        this.guiManager = GuiManager.builder()
                .player(player)
                .build();
        this.joinTime = System.currentTimeMillis();
        if (MapConfigHandler.kickValidationTimeout){
            AoitoriProject.kilimScheduler.forkJoinExecute(()->{
                Task.sleep(MapConfigHandler.kickValidationTimeoutTime);
                if (!verified && !quit){
                    player.kickPlayer("连接中止");
                }
            });
        }
    }

    Player player;
    String renderWorld;
    Map<String,Object> dataMap;
    OverlayManager overlayManager;
    GuiManager guiManager;
    boolean isOpen;
    boolean isMapping;
    long joinTime;
    @Setter
    boolean verified;
    boolean quit;

    public void onMapStatusChange(OperateMapDataDTO.MapOperateType mapOperateType){
        switch (mapOperateType) {
            case OPEN:{
                this.isOpen = true;
                break;
            }
            case CLOSE:{
                this.isOpen = false;
                break;
            }
            case STOP_MAPPING:{
                this.isMapping = false;
                break;
            }
            case START_MAPPING:{
                this.isMapping = true;
                break;
            }
        }
    }

    public MapPlayerProfile setRenderWorld(String renderWorld) {
        this.renderWorld = renderWorld;
        RenderWorldDataDTO.builder()
                .worldName(this.renderWorld)
                .build()
                .send(player);
        return this;
    }

    public void locateOn(int x,int y){
        LocateOnDataDTO.builder()
                .x(x)
                .y(y)
                .build()
                .send(player);
    }

    public void operateMap(OperateMapDataDTO.MapOperateType mapOperateType){
        OperateMapDataDTO.builder()
                .type(mapOperateType)
                .build()
                .send(player);
    }

    public void sendGeneralVariables(Map<String,Object> generalVariables){
        dataMap.putAll(generalVariables);
        VariablesDataDTO.builder()
                .map(generalVariables)
                .build()
                .send(player);
    }
}
