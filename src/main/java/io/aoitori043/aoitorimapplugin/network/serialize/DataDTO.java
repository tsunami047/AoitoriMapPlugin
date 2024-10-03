package io.aoitori043.aoitorimapplugin.network.serialize;

import lombok.Data;
import lombok.Getter;
import org.bukkit.entity.Player;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-02  19:42
 * @Description: ?
 */
@Getter
@Data
public class DataDTO {

    DataDTOType dataType;

    public DataDTO(DataDTOType dataType) {
        this.dataType = dataType;
    }

    public void execute(Player player){}
}
