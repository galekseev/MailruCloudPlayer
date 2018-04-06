package camo.mailru.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import camo.mailru.api.json.EntriesList;
import camo.mailru.api.json.EntriesListDeserializer;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MailruApiService implements ApiService {

    private CookieJar cookieJar;
    private OkHttpClient httpClient;
    private Retrofit retrofit;
    private CloudApi api;

    public MailruApiService(CookieJar jar){
        cookieJar = jar;

        httpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(EntriesList.class, new EntriesListDeserializer())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(RequestBuilder.API_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Override
    public CloudApi createService() {
        api = retrofit.create(CloudApi.class);
        return api;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    @Override
    public CookieJar getCookieJar() {
        return cookieJar;
    }
}
