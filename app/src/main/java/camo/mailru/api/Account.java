package camo.mailru.api;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    /**
     * Logins to mail.ru cloud
     *
     * @throws IOException if the request could not be executed due to cancellation, a connectivity
     * problem or timeout. Because networks can fail during an exchange, it is possible that the
     * remote server accepted the request before the failure.
     */
    public boolean Login() throws IOException {
        Request request = RequestBuilder.buildLoginRequest(this.LoginName, this.Password);
        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            List<Cookie> cookies = CookieJar.loadForRequest(RequestBuilder.getAuthUrl());
            if (cookies.size() > 0) {
                ensureSdcCookie();
                obtainAuthToken();
                return true;
            }
        }

        return false;
    }

    private void ensureSdcCookie() throws IOException {

        Request request = RequestBuilder.buildEnsureSdcCookieRequest();
        Response response = okHttpClient.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Unable to retrieve cookie");
        }
    }

    private void obtainAuthToken() throws IOException {

        Request request = RequestBuilder.buildAuthTokenRequest();
        Response response = okHttpClient.newCall(request).execute();
        Log.v(TAG, "Auth token call complete");

        if (response.isSuccessful()) {
            String json = response.body().string();
            Gson gson = new Gson();
            camo.mailru.api.AuthToken token = gson.fromJson(json, camo.mailru.api.AuthToken.class);
            AuthToken = token.getToken();
            Log.v(TAG, "Successful login - phase 3");
        } else {
            Log.v(TAG, "Failed login - phase 3");
        }

//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.v(TAG, "Auth token call complete");
//
//                if (response.isSuccessful()) {
//                    Log.v(TAG, "Successful login - phase 3");
//                } else {
//                    Log.v(TAG, "Failed login - phase 3");
//                }
//            }
//        });
    }

    public DiskUsage getDiskUsage() throws IOException {
        this.checkAuth();

        Request request = RequestBuilder.buildGetDiskUsageRequest(LoginName, getAuthToken());
        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            String json = response.body().string();
            Gson gson = new Gson();
            DiskUsage diskUsage = gson.fromJson(json, DiskUsage.class);
            Log.v(TAG, "Successful got account info:" + diskUsage.toString());
            return diskUsage;
        } else {
            Log.v(TAG, "Failed to get account info");
        }

        return null;
    }

    private boolean checkAuth() throws IOException {
        if (this.LoginName == null && this.Password == null)
            return false;

        if (this.AuthToken == null || this.AuthToken.isEmpty()) {
            Login();
        }

        return true;
    }
}
