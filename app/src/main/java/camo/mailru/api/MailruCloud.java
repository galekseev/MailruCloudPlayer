package camo.mailru.api;

import java.io.IOException;

import camo.mailru.api.json.Folder;
import camo.mailru.api.json.JsonObjectType;
import camo.mailru.api.json.JsonParser;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by AlekseevGA on 14.12.2017.
 */

public class MailruCloud {

    private Account account = null;
    private OkHttpClient okHttpClient = null;
    private CookieJar cookieJar;

    /**
     * Initializes a new instance of the MailruCloud class.
     */
    public MailruCloud(String login, String password, CookieJar cookieJar){
        this.cookieJar = cookieJar;
        this.account = new Account(login, password, cookieJar);

        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(this.cookieJar)
                .build();
    }

    public boolean Login() throws IOException{
        return this.account.Login();
    }

    /**
     * Get list of files and folders from account.
     *
     * @param path Path in the cloud to return the list of the items.
     * @return List of the items.
     */
    public Folder getItem(String path) throws IOException{
        this.account.ensureAuth();

        if (path == null || path.isEmpty())
            path = "/";


        Request request = RequestBuilder.buildGetItemRequest(path, account.getAuthToken());
        Response response = executeRequest(request);

        String json = response.body().string();
        Folder entry = (Folder) JsonParser.Parse(json, JsonObjectType.Entry);

        return entry;
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
}
