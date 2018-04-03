package camo.mailru.api;

import camo.mailru.api.json.DiskUsage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CloudApi {
    @Headers({
            "Accept: application/json",
            "User-Agent: " + RequestBuilder.USER_AGENT
    })
    @GET("user/space")
    Call<DiskUsage> getDiskUsage(
            @Query("api") int api,
            @Query("email") String email,
            @Query("token") String token);
}
