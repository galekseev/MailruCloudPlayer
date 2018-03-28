package camo.mailru.api.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GAlekseev on 17.03.2018.
 */

public class AuthToken extends JsonResponse<AuthToken.AuthTokenBody> {

    public String getToken(){
        return body().token;
    }

    public class AuthTokenBody {

        @SerializedName("token")
        public String token;

        @Override
        public String toString(){

            return new StringBuilder()
                    .append("{token:")
                    .append(token)
                    .append("}")
                    .toString();

        }
    }

}
