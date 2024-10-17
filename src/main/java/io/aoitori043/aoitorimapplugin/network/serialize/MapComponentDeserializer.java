package io.aoitori043.aoitorimapplugin.network.serialize;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.aoitori043.aoitorimapplugin.config.mapper.ComponentType;
import io.aoitori043.aoitorimapplugin.config.mapper.GuiComponent;

import java.lang.reflect.Type;

public class MapComponentDeserializer implements JsonDeserializer<GuiComponent> {
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
}
