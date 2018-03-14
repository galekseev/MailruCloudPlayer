package camo.mailru.api;

import android.util.Log;

import java.io.IOException;
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

    private String LoginName;
    private String Password;
    private String AuthToken;
    private OkHttpClient okHttpClient;
    private CookieJar CookieJar;
    private final HttpUrl authUrl = new HttpUrl.Builder()
            .scheme("https")
            .host(ConstSettings.AUTH_DOMAIN)
            .addPathSegment("cgi-bin")
            .addPathSegment("auth")
            .build();

    public Account(String login, String password, CookieJar cookieJar) {
        LoginName = login;
        Password = password;

        CookieJar = cookieJar;
                //new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(CookieJar)
                .build();
    }

    public String getLoginName() {
        return LoginName;
    }

    public String getPassword() {
        return Password;
    }

    public String getAuthToken() {
        return AuthToken;
    }

    public void Login() {
        Log.v(TAG, "Url: " + authUrl);

        RequestBody formBody = new FormBody.Builder()
                .addEncoded("Login", this.LoginName)
                .addEncoded("Domain", ConstSettings.DOMAIN)
                .addEncoded("Password", this.Password)
                .build();

        Request request = new Request.Builder()
                .url(authUrl)
                .header("User-Agent", ConstSettings.USER_AGENT)
                .addHeader("Accept", ConstSettings.DEFAULT_ACCEPT_TYPE)
                .post(formBody)
                .build();

        Log.v(TAG, "Request: " + request.toString());

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v(TAG, "Got response!");

                if (response.isSuccessful()) {
                    List<Cookie> cookies = CookieJar.loadForRequest(authUrl);
                    if (cookies.size() > 0) {
                        Log.v(TAG, "Successful login - phase 1");
                        ensureSdcCookie();
                    } else
                        Log.v(TAG, "Failed to login - phase 1");
                }
            }
        });
    }

    private void ensureSdcCookie() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(ConstSettings.AUTH_DOMAIN)
                .addPathSegment("sdc")
                .addQueryParameter("from", "https://" + ConstSettings.CLOUD_DOMAIN + "/home")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", ConstSettings.USER_AGENT)
                .addHeader("Accept", ConstSettings.DEFAULT_ACCEPT_TYPE)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v(TAG, "Sdc call complete");

                if (response.isSuccessful()) {
                    Log.v(TAG, "Successful login - phase 2");
                    obtainAuthToken();
                } else {
                    Log.v(TAG, "Failed login - phase 2");
                }
            }
        });
    }

    private void obtainAuthToken() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(ConstSettings.CLOUD_DOMAIN)
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("tokens")
                .addPathSegment("csrf")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", ConstSettings.USER_AGENT)
                .addHeader("Accept", "application/json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v(TAG, "Auth token call complete");

                if (response.isSuccessful()) {
                    Log.v(TAG, "Successful login - phase 3");
                } else {
                    Log.v(TAG, "Failed login - phase 3");
                }
            }
        });
    }

    public DiskUsage getDiskUsage() {
        if (!this.checkAuth())
            return null;

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(ConstSettings.CLOUD_DOMAIN)
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("user")
                .addPathSegment("space")
                .addQueryParameter("api", "2")
                .addQueryParameter("email", this.LoginName)
                .addQueryParameter("token", this.AuthToken)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", ConstSettings.USER_AGENT)
                .addHeader("Accept", "application/json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v(TAG, "Disk usage call complete");

                if (response.isSuccessful()) {
                    Log.v(TAG, response.body().toString());
                } else {
                    Log.v(TAG, "Failed login - phase 3");
                }
            }
        });

        return null;
    }

    private boolean checkAuth() {
        if (this.LoginName == null && this.Password == null)
            return false;

        if (this.AuthToken == null || this.AuthToken.isEmpty()) {
            Login();
        }

        return true;
    }
}
