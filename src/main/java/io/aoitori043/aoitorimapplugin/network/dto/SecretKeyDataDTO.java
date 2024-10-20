package io.aoitori043.aoitorimapplugin.network.dto;

import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static io.aoitori043.aoitorimapplugin.network.serialize.DataDTOType.SECRET_KEY;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-20  22:58
 * @Description: ?
 */
public class SecretKeyDataDTO extends DataDTO {

    byte[] key;

    @Builder
    public SecretKeyDataDTO(byte[] key) {
        super(SECRET_KEY);
        this.key = key;
    }




}
