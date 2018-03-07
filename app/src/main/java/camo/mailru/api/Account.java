package camo.mailru.api;

import android.content.Context;
//import android.content.SharedPreferences;
import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;

/**
 * Created by AlekseevGA on 14.12.2017.
 */

public class Account {

    private static final String TAG = "camo.mailru.api.Account";
//    public static final MediaType DEFAULT_MEDIA_TYPE
//            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    private String LoginName;
    private String Password;
//    private String AuthToken;
    private OkHttpClient okHttpClient;
    private PersistentCookieJar cookieJar;
//    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
    private final HttpUrl authUrl = buildAuthUrl();

    public Account(String login, String password, Context context){
        LoginName = login;
        Password = password;

        cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        okHttpClient = new OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .build();
    }

    public String getLoginName(){
        return LoginName;
    }
    public String getPassword(){
        return Password;
    }

//    public String getAuthToken() { return AuthToken; }
//    public HashMap<HttpUrl, List<Cookie>> getCookieStore() { return cookieStore; }
//
    public void Login(){
        HttpUrl url = buildAuthUrl();

        Log.v(TAG, "Url: "+ url);

        //String postBody = String.format("LoginName=%1&Domain=%3&Password=%2", this.LoginName, this.Password, ConstSettings.DOMAIN);
        //RequestBody body = RequestBody.create(DEFAULT_MEDIA_TYPE, postBody);

        RequestBody formBody = new FormBody.Builder()
                .add("LoginName", this.LoginName)
                .add("Domain", ConstSettings.DOMAIN)
                .add("Password", this.Password)
                .build();

        Log.v(TAG, "Body: "+formBody);

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", ConstSettings.USER_AGENT)
                .addHeader("Accept", ConstSettings.DEFAULT_ACCEPT_TYPE)
                .post(formBody)
                .build();

        Log.v(TAG, "Request: "+request.toString());

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.v(TAG, response.body().string());

                List<Cookie> cookies = cookieJar.loadForRequest(authUrl);
                if (cookies.size() > 0)
                {
                    Log.v(TAG, "Successful login - phase 1");
                }
                else Log.v(TAG, "Failed to login - phase 1");
//                if (cookieStore.containsKey(authUrl) && cookieStore.get(authUrl).size() > 0) {
//                    Log.v(TAG, "Successful login - phase 1");
//                    ensureSdcCookie();
//                }
//                else{
//                    Log.v(TAG, "Failed login - phase 2");
//                }
            }
        });
    }
//
//    private void ensureSdcCookie(){
//        HttpUrl url = new HttpUrl.Builder()
//                .scheme("https")
//                .host(ConstSettings.AUTH_DOMAIN)
//                .addPathSegment("sdc")
//                .addQueryParameter("from", ConstSettings.CLOUD_DOMAIN + "/home")
//                .build();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .header("User-Agent", ConstSettings.USER_AGENT)
//                .addHeader("Accept", ConstSettings.DEFAULT_ACCEPT_TYPE)
//                .build();
//
//        httpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //Log.v(TAG, response.body().string());
//
//                if (cookieStore.containsKey(authUrl) && cookieStore.get(authUrl).size() > 0) {
//                    Log.v(TAG, "Successful login - phase 2");
//                    if (response.isSuccessful()){
//                        obtainAuthToken();
//                    }
//                }
//                else{
//                    Log.v(TAG, "Failed login - phase 2");
//                }
//            }
//        });
//    }
//
//    private void obtainAuthToken(){
//        HttpUrl url = new HttpUrl.Builder()
//                .scheme("https")
//                .host(ConstSettings.CLOUD_DOMAIN)
//                .addPathSegment("api")
//                .addPathSegment("v2")
//                .addPathSegment("tokens")
//                .addPathSegment("csrf")
//                .build();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .header("User-Agent", ConstSettings.USER_AGENT)
//                .addHeader("Accept", "application/json")
//                .build();
//    }
//
    private HttpUrl buildAuthUrl(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(ConstSettings.AUTH_DOMAIN)
                .addPathSegment("cgi-bin")
                .addPathSegment("auth")
                .build();

        return url;
    }
}
