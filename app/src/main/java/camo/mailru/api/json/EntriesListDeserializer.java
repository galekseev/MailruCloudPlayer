package camo.mailru.api.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class EntriesListDeserializer implements JsonDeserializer<EntriesList> {
    public EntriesList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        EntriesList list = new EntriesList();

        JsonArray array = json.getAsJsonArray();
        for (int i = 0; i < array.size(); i++){
            JsonObject element = array.get(i).getAsJsonObject();
            String type = element.get("type").getAsString();
            if (type.equals("folder"))
                list.folders.add((FolderMeta)context.deserialize(element, FolderMeta.class));
            else if (type.equals("file"))
                list.files.add((FileMeta)context.deserialize(element, FileMeta.class));
        }
        return list;
    }
}
