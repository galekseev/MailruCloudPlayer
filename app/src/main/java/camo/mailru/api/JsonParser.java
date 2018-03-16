package camo.mailru.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import camo.mailru.api.json.AuthToken;

public class JsonParser {

    public static Object Parse(String response, PObject parseObject){
        return Parse(response, parseObject, null);
    }

    public static Object Parse(String response, PObject parseObject, Object param){
        if (response == null || response.isEmpty())
        {
            throw new IllegalArgumentException("Response text is null or empty.");
        }

        //// Cancellation token.
        if (response == "7035ba55-7d63-4349-9f73-c454529d4b2e")
        {
            return null;
        }

        Gson gson = new Gson();
        AuthToken token = gson.fromJson(response, AuthToken.class);
        Log.v("JsonParser", "status:"+token.status+" email:"+token.email+" token:"+token.body.token);

//        try
//        {
//            //parsedJObject = JObject.Parse(response);
//        }

        return null;
    }
}
