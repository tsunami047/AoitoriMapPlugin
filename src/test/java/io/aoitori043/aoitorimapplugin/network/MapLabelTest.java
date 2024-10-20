package io.aoitori043.aoitorimapplugin.network;

import com.google.gson.Gson;
import io.aoitori043.aoitorimapplugin.config.mapper.ComponentType;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiComponent;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;
import io.aoitori043.aoitorimapplugin.network.dto.GuiFileDataDTO;
import io.aoitori043.aoitorimapplugin.network.serialize.DataDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapLabelTest {

    private Gson gson;

    @BeforeEach
    public void setUp() {

    }

    @org.junit.Test
    @Test
    public void testSerialization() {
        gson = NetworkImpl.gson;

        GuiMapper guiMapper = new GuiMapper();
        GuiMapper.MapLabel mapLabel1 = new GuiMapper.MapLabel();
        // 设置 MapLabel 的属性
        mapLabel1.setHAlign("center")
                .setVAlign("middle")
                .setBgColor("#FFFFFF")
                .setBgAlpha("0.5")
                .setColor("#000000")
                .setAlpha("0.8")
                .setFontScala("1.2")
                .setFontShadow("true")
                .setRotation("90")
                .setTexts(Arrays.asList("Text1", "Text2"));

        mapLabel1.setType(ComponentType.LABEL);
        LinkedHashMap<String,GuiComponent> componentLinkedHashMap = new LinkedHashMap<>();
        componentLinkedHashMap.put("test",mapLabel1);
        guiMapper.setComponents(componentLinkedHashMap);
        guiMapper.setIndex("1aaa");
        GuiFileDataDTO build = GuiFileDataDTO.builder()
                .guiMapper(guiMapper)
                .build();

        // 序列化为 JSON
        String json = gson.toJson(build);

        DataDTO dataDTO = gson.fromJson(json, DataDTO.class);
        System.out.println(dataDTO);

//        // 验证反序列化后的对象属性
//        assertEquals("center", deserializedMapLabel.getHAlign());
//        assertEquals("middle", deserializedMapLabel.getVAlign());
//        assertEquals("#FFFFFF", deserializedMapLabel.getBgColor());
//        assertEquals("0.5", deserializedMapLabel.getBgAlpha());
//        assertEquals("#000000", deserializedMapLabel.getColor());
//        assertEquals("0.8", deserializedMapLabel.getAlpha());
//        assertEquals("1.2", deserializedMapLabel.getFontScala());
//        assertEquals("true", deserializedMapLabel.getFontShadow());
//        assertEquals("90", deserializedMapLabel.getRotation());
//        assertEquals(Arrays.asList("Text1", "Text2"), deserializedMapLabel.getTexts());
    }
}
