package camo.mailru.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GAlekseev on 17.03.2018.
 */

public class AuthToken extends JsonResponse<AuthToken.AuthTokenBody> {

    public String getToken(){
        return getBody().token;
    }

    public class AuthTokenBody {

        @SerializedName("token")
        public String token;

        @Override
        public String toString(){
            return token;
        }
    }

}
