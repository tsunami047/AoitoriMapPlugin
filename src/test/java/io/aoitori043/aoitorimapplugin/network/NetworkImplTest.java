package io.aoitori043.aoitorimapplugin.network;

import com.google.gson.Gson;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.LocateOnDataDTO;
import io.aoitori043.aoitorimapplugin.network.dto.OperateMapDataDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetworkImplTest {

    @Test
    public void testGson() {
        OperateMapDataDTO build = OperateMapDataDTO
                .builder()
                .type(OperateMapDataDTO.MapOperateType.OPEN)
                .build();
        Gson gson = NetworkImpl.gson;
        String json = gson.toJson(build);
        System.out.println(json);
        DataDTO dataDTO = gson.fromJson(json, DataDTO.class);
        LocateOnDataDTO build1 = LocateOnDataDTO.builder().x(10).y(20).build();
        DataDTO dataDTO2 = gson.fromJson(gson.toJson(build1), DataDTO.class);
        System.out.println(dataDTO);
        System.out.println(dataDTO2);
    }



}
