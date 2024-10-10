package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.business.ScriptActions;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.entity.Player;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  21:53
 * @Description: ?
 */
@Getter
@ToString(callSuper = true)
public class ActionsDataDTO extends DataDTO {


    String guiName;
    String componentIndex;
    ClickMethod clickMethod;

    public enum ClickMethod{
        LEFT,
        RIGHT
    }

    @Builder
    public ActionsDataDTO( String guiName, String componentIndex, ClickMethod clickMethod) {
        super(DataDTOType.ACTIONS);
        this.guiName = guiName;
        this.componentIndex = componentIndex;
        this.clickMethod = clickMethod;
    }

    @Override
    public void onServerReceived(Player player){
        GuiMapper guiMapper = MapConfigHandler.gui.get(guiName);
        if (guiMapper == null) return;
        String methodIndex = this.clickMethod.toString().toLowerCase();
        ScriptActions scriptActions = ScriptActions.createActions(player);
        try {
            AoitoriMapPlugin.scriptExecutor.invokeFunction(guiName+"_"+componentIndex+"_"+methodIndex, scriptActions);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
