package camo.mailru.api;

import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;

/**
 * Created by AlekseevGA on 14.12.2017.
 */

public class Account {

    private static final String TAG = "camo.mailru.api.Account";

    private String Login;
    private String Password;
    private String AuthToken;
    private OkHttpClient httpClient;
    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
    private final HttpUrl authUrl = buildAuthUrl();

    public Account(String login, String password){
        Login = login;
        Password = password;

        httpClient = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url, cookies);
                    Log.v(TAG, "cookies: " + cookies.size());
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url);
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            })
            .build();
    }

    public String getLogin(){
        return Login;
    }
    public String getPassword(){
        return Password;
    }
    public HashMap<HttpUrl, List<Cookie>> getCookieStore() { return cookieStore; }

    public void Login(){
        HttpUrl url = buildAuthUrl();

        Log.v(TAG, "Url: "+ url);

        //String postBody = String.format("Login=%1&Domain=%3&Password=%2", this.Login, this.Password, ConstSettings.DOMAIN);
        //RequestBody body = RequestBody.create(DEFAULT_MEDIA_TYPE, postBody);

        RequestBody formBody = new FormBody.Builder()
                .add("Login", this.Login)
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

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.v(TAG, response.body().string());

                if (cookieStore.containsKey(authUrl) && cookieStore.get(authUrl).size() > 0) {
                    Log.v(TAG, "Successful login - phase 1");
                    ensureSdcCookie();
                }
                else{
                    Log.v(TAG, "Failed login - phase 1");
                }
            }
        });
    }

    private void ensureSdcCookie(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(ConstSettings.AUTH_DOMAIN)
                .addPathSegment("sdc")
                .addQueryParameter("from", ConstSettings.CLOUD_DOMAIN + "/home")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", ConstSettings.USER_AGENT)
                .addHeader("Accept", ConstSettings.DEFAULT_ACCEPT_TYPE)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.v(TAG, response.body().string());

                if (cookieStore.containsKey(authUrl) && cookieStore.get(authUrl).size() > 0) {
                    Log.v(TAG, "Successful login - phase 2");
                    if (response.isSuccessful()){
                        getAuthToken();
                    }
                }
                else{
                    Log.v(TAG, "Failed login - phase 2");
                }
            }
        });
    }

    private void getAuthToken(){

    }

    private HttpUrl buildAuthUrl(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(ConstSettings.CLOUD_DOMAIN)
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("tokens")
                .addPathSegment("csrf")
                .build();

        return url;
    }
}
