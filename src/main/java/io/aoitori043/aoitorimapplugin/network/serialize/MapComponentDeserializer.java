package io.aoitori043.aoitorimapplugin.network.serialize;

import com.google.gson.*;
import io.aoitori043.aoitorimapplugin.config.mapper.ComponentType;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiComponent;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiMapper;

import java.lang.reflect.Type;

public class MapComponentDeserializer implements JsonDeserializer<GuiComponent>, JsonSerializer<GuiComponent> {
    @Override
    public GuiComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            throw new JsonParseException("Not a JSON Object");
        }

        JsonElement dataTypeElement = json.getAsJsonObject().get("type");
        if (dataTypeElement == null) {
            throw new JsonParseException("Missing 'type' field");
        }

        String type = dataTypeElement.getAsString();
        try {
            ComponentType componentType = ComponentType.valueOf(type.toUpperCase());
            return context.deserialize(json, componentType.getType());
        } catch (IllegalArgumentException e) {
            throw new JsonParseException("Unsupported components type: " + type);
        }
    }


    @Override
    public JsonElement serialize(GuiComponent guiComponent, Type type, JsonSerializationContext context) {
        switch (guiComponent.getType()) {
            case BUTTON:{
                return context.serialize(guiComponent, GuiMapper.MapButton.class);
            }
            case LABEL:{
                return context.serialize(guiComponent, GuiMapper.MapLabel.class);
            }
            case TEXTURE:{
                return context.serialize(guiComponent, GuiMapper.MapTexture.class);
            }
        }
        return null;
    }
}
