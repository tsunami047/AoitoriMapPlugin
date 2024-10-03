package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-02  20:33
 * @Description: ?
 */
@Getter
@ToString(callSuper = true)
public class LocateOnDataDTO extends DataDTO {

    @Builder
    public LocateOnDataDTO(double x, double y) {
        super(DataDTOType.LOCATE_ON);
        this.x = x;
        this.y = y;
    }

    double x;
    double y;

}
