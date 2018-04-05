package camo.mailru.api;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MailruApiService {

    private static final String BASE_URL = "https://cloud.mail.ru/api/v2/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = null;

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.NONE);

    public static void build(CookieJar cookieJar){
        build(cookieJar, HttpLoggingInterceptor.Level.NONE);
    }

    public static void build(CookieJar cookieJar, HttpLoggingInterceptor.Level level){
        httpClient.cookieJar(cookieJar);

        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
        }

        builder.client(httpClient.build());
        retrofit = builder.build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
