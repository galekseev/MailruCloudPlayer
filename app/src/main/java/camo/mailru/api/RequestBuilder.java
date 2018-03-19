package camo.mailru.api;

import android.util.Log;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by GAlekseev on 19.03.2018.
 */

public class RequestBuilder {

    public static final String DOMAIN = "mail.ru";
    public static final String CLOUD_DOMAIN = "cloud.mail.ru";
    public static final String AUTH_DOMAIN = "auth.mail.ru";
    public static final String PUBLISH_FILE_LINK = CLOUD_DOMAIN + "/public/";

    public static final String USER_AGENT = "Mozilla / 5.0(Windows; U; Windows NT 5.1; en - US; rv: 1.9.0.1) Gecko / 2008070208 Firefox / 3.0.1";
    public static final String DEFAULT_ACCEPT_TYPE = "text / html,application / xhtml + xml,application / xml; q = 0.9,*/*;q=0.8";
    public static final MediaType DEFAULT_REQUEST_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");


    private static final String TAG = "RequestBuilder";
    private static final HttpUrl authUrl = new HttpUrl.Builder()
            .scheme("https")
            .host(AUTH_DOMAIN)
            .addPathSegment("cgi-bin")
            .addPathSegment("auth")
            .build();

    public static final HttpUrl getAuthUrl(){
        return authUrl;
    }

    public static Request buildLoginRequest(String login, String password){
        RequestBody formBody = new FormBody.Builder()
                .addEncoded("Login", login)
                .addEncoded("Domain", DOMAIN)
                .addEncoded("Password", password)
                .build();

        Request request = new Request.Builder()
                .url(authUrl)
                .header("User-Agent", USER_AGENT)
                .addHeader("Accept", DEFAULT_ACCEPT_TYPE)
                .post(formBody)
                .build();

        Log.v(TAG, "Request built: " + request.toString());

        return request;
    }

    public static Request buildEnsureSdcCookieRequest() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(AUTH_DOMAIN)
                .addPathSegment("sdc")
                .addQueryParameter("from", "https://" + CLOUD_DOMAIN + "/home")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", USER_AGENT)
                .addHeader("Accept", DEFAULT_ACCEPT_TYPE)
                .build();

        Log.v(TAG, "Request built: " + request.toString());

        return request;
    }

    public static Request buildAuthTokenRequest(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(CLOUD_DOMAIN)
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("tokens")
                .addPathSegment("csrf")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", USER_AGENT)
                .addHeader("Accept", "application/json")
                .build();

        Log.v(TAG, "Request built: " + request.toString());

        return request;
    }

    public static Request buildGetDiskUsageRequest(String login, String token){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(CLOUD_DOMAIN)
                .addPathSegment("api")
                .addPathSegment("v2")
                .addPathSegment("user")
                .addPathSegment("space")
                .addQueryParameter("api", "2")
                .addQueryParameter("email", login)
                .addQueryParameter("token", token)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", USER_AGENT)
                .addHeader("Accept", "application/json")
                .build();

        Log.v(TAG, "Request built: " + request.toString());

        return request;
    }
}
