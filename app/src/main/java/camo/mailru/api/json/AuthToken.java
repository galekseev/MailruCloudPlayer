package camo.mailru.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthToken {

    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("body")
    @Expose
    public AuthTokenBody body;
    @SerializedName("time")
    @Expose
    public Long time;
    @SerializedName("status")
    @Expose
    public Integer status;

}

