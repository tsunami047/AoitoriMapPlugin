package io.aoitori043.aoitorimapplugin.network.serialize;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-02  20:30
 * @Description: ?
 */
public class MapDataDTODeserializer implements JsonDeserializer<DataDTO> {

    @Override
    public DataDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            throw new JsonParseException("Not a JSON Object");
        }

        JsonElement dataTypeElement = json.getAsJsonObject().get("dataType");
        if (dataTypeElement == null) {
            throw new JsonParseException("Missing 'dataType' field");
        }

        String dataType = dataTypeElement.getAsString();
        try {
            DataDTOType dataDTOType = DataDTOType.valueOf(dataType);
            return context.deserialize(json, dataDTOType.getDataDTOClass());
        } catch (IllegalArgumentException e) {
            throw new JsonParseException("Unsupported dataType: " + dataType);
        }

    }
}
