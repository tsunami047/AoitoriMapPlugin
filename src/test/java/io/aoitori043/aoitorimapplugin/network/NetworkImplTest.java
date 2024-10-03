package io.aoitori043.aoitorimapplugin.net;

import java.util.*;
import java.math.*;

import com.google.gson.Gson;
import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import io.aoitori043.aoitorimapplugin.net.dto.DataDTO;
import io.aoitori043.aoitorimapplugin.net.dto.LocationDataDTO;
import io.aoitori043.aoitorimapplugin.net.dto.OperateMapDataDTO;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        LocationDataDTO build1 = LocationDataDTO.builder().x(10).y(20).build();
        DataDTO dataDTO2 = gson.fromJson(gson.toJson(build1), DataDTO.class);
        System.out.println(dataDTO);
        System.out.println(dataDTO2);
    }



}
