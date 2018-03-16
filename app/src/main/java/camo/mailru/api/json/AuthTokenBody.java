package camo.mailru.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthTokenBody {

    @SerializedName("token")
    @Expose
    public String token;

}
