package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
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
        super(DataDTOType.OPERATE_MAP);
        this.type = type;
    }

    public MapOperateType type;



}
