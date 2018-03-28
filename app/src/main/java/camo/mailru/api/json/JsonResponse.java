/**
 * Created by GAlekseev on 17.03.2018.
 */


package camo.mailru.api.json;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class JsonResponse<T> {
    @SerializedName("email")
    private String email;
    @SerializedName("body")
    private T body;
    @SerializedName("time")
    private Long time;
    @SerializedName("status")
    private Integer status;

    public String getEmail() {
        return email;
    }

    public Date getTime() {
        return new Date(time);
    }

    public Integer getStatus() {
        return status;
    }

    T body(){ return body; }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("{email:").append(getEmail())
                .append(",body:").append(body != null ? body.toString() : null)
                .append(",time:").append(getTime())
                .append(",status:").append(getStatus())
                .append("}")
                .toString();
    }
}
