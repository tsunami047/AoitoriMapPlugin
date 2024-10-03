package io.aoitori043.aoitorimapplugin.net.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-02  19:41
 * @Description: ?
 */
@Getter
@ToString(callSuper = true)
public class OperateMapDataDTO extends DataDTO {

    public enum MapOperateType{
        OPEN,
        CLOSE
    }

    @Builder
    public OperateMapDataDTO(MapOperateType type) {
        super("operateMap");
        this.type = type;
    }

    public MapOperateType type;



}
