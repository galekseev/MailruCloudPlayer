package camo.mailru.api.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {

    public static Object Parse(String response, JsonObjectType parseObject){
        return Parse(response, parseObject, null);
    }

    public static Object Parse(String response, JsonObjectType parseObject, Object param){
        if (response == null || response.isEmpty())
        {
            throw new IllegalArgumentException("Response text is null or empty.");
        }

        //// Cancellation token.
        if (response == "7035ba55-7d63-4349-9f73-c454529d4b2e")
        {
            return null;
        }

        Gson gson = null;

        switch(parseObject){
            case Token:
                gson = new Gson();
                AuthToken jToken = gson.fromJson(response, AuthToken.class);
                Log.v("JsonParser", jToken.toString());
                return jToken;
            case DiskUsage:
                gson = new Gson();
                DiskUsage diskUsage = gson.fromJson(response, DiskUsage.class);
                Log.v("JsonParser", diskUsage.toString());
                return diskUsage;
            case Entry:
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(EntriesList.class, new EntriesListDeserializer());
                gson = builder.create();
                Folder folder = gson.fromJson(response, Folder.class);
                Log.v("JsonParser", folder.toString());
                return folder;
            default:
                return null;
        }

//        try
//        {
//            //parsedJObject = JObject.Parse(response);
//        }

    }
}
