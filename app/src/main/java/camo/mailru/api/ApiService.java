package camo.mailru.api;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

public interface ApiService {
    public CloudApi createService();
    public OkHttpClient getHttpClient();
    public CookieJar getCookieJar();
}
