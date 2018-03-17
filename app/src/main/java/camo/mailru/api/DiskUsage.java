package camo.mailru.api;

import com.google.gson.annotations.SerializedName;

public class DiskUsage extends JsonResponse<DiskUsage.DiskUsageBody> {

    public FileSize getTotal(){
        return new FileSize(getBody().total * 1024L * 1024L);
    }

    public FileSize getUsed(){
        return new FileSize(getBody().used * 1024L * 1024L);
    }

    public FileSize getFree(){
        return new FileSize((getBody().total - getBody().used) * 1024L * 1024L);
    }
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("email", email).append("body", body).append("time", time).append("status", status).toString();
//    }

    public class DiskUsageBody {

        @SerializedName("overquota")
        public Boolean overquota;
        @SerializedName("used")
        public Long used;
        @SerializedName("total")
        public Long total;


        @Override
        public String toString() {
            return new StringBuilder()
                    .append("{overquota:")
                    .append(overquota)
                    .append(",used:")
                    .append(used)
                    .append(",total:")
                    .append(total)
                    .append("}")
                    .toString();
        }
    }
}