package camo.mailru.api;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import camo.mailru.api.json.AuthToken;
import camo.mailru.api.json.DiskUsage;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AlekseevGA on 14.12.2017.
 */

public class Account {

    private static final String TAG = "camo.mailru.api.Account";

    private OkHttpClient okHttpClient;
    private CookieJar cookieJar;

    public Account(String login, String password, CookieJar cookieJar) {
        this.loginName = login;
        this.password = password;

        this.cookieJar = cookieJar;
                //new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(this.cookieJar)
                .build();
    }

    //region public properties
    private final String loginName;
    private final String password;
    private String authToken;

    public String getLogin() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthToken() {
        return authToken;
    }
    //endregion

    /**
     * Logins to mail.ru cloud
     *
     * @throws IOException if the request could not be executed due to cancellation, a connectivity
     * problem or timeout. Because networks can fail during an exchange, it is possible that the
     * remote server accepted the request before the failure.
     */
    public boolean Login() throws IOException {
        //Build and execute request
        Request request = RequestBuilder.buildLoginRequest(this.loginName, this.password);
        Response response = executeRequest(request);
        response.close();

        List<Cookie> cookies = cookieJar.loadForRequest(RequestBuilder.getAuthUrl());
        if (cookies.size() > 0) {
            ensureSdcCookie();
            return obtainAuthToken();
        }

        return false;
    }

    private void ensureSdcCookie() throws IOException {
        //Build and execute request
        Request request = RequestBuilder.buildEnsureSdcCookieRequest();
        Response response = executeRequest(request);
        response.close();
    }

    private boolean obtainAuthToken() throws IOException {
        //Build and execute request
        Request request = RequestBuilder.buildAuthTokenRequest();
        Response response = executeRequest(request);

        //Parse json to get auth token
        String json = response.body().string();
        Gson gson = new Gson();
        AuthToken token = gson.fromJson(json, AuthToken.class);
        authToken = token.getToken();

        return !(authToken == null || authToken.isEmpty());
    }

    private Response executeRequest(Request request) throws IOException{
        Response response = okHttpClient.newCall(request).execute();

        if (!response.isSuccessful()) {
            int code = response.code();
            response.close();
            throw new IOException("Response failed with code: " + code);
        }

        return response;
    }

    public DiskUsage getDiskUsage() throws IOException {
        this.ensureAuth();

        CloudApi api = MailruApiService.createService(CloudApi.class);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://cloud.mail.ru/api/v2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();
//
//        CloudApi api = retrofit.create(CloudApi.class);
        Call<DiskUsage> call = api.getDiskUsage(2, loginName, getAuthToken());
        DiskUsage diskUsage = call.execute().body();
        Log.v(TAG, "Successful got account info:" + diskUsage.toString());
        return diskUsage;
    }

    public void ensureAuth() throws IOException {
        if (this.loginName == null && this.password == null)
            throw new IOException("Login or password is empty.");

        if (this.authToken == null || this.authToken.isEmpty()) {
            if (!this.Login())
                throw new IOException("Auth token has't been retrieved.");
        }
    }

    public CookieJar getCookieJar() {
        return this.cookieJar;
    }
}
