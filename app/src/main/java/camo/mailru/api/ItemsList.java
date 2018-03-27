package camo.mailru.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ItemListDeserializer implements JsonDeserializer<FolderJson.ItemsList> {
    public FolderJson.ItemsList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

//        FolderJson.ItemsList list = new FolderJson.ItemsList();
//
//        JsonArray array = json.getAsJsonArray();
//        for (int i = 0; i < array.size(); i++){
//            JsonObject element = array.get(i).getAsJsonObject();
//            String type = element.get("type").getAsString();
//            if (type.equals("folder"))
//                list.folders.add((FolderJson)context.deserialize(element, FolderJson.class));
//            else
//                list.files.add((File)context.deserialize(element, File.class));
//        }
//        return list;
        return null;
    }
}
