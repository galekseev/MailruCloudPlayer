package camo.mailru.api.json;

import com.google.gson.annotations.SerializedName;

public class DiskUsage extends JsonResponse<DiskUsage.DiskUsageBody> {

    public FileSize getTotal(){
        return new FileSize(body().total * 1024L * 1024L);
    }

    public FileSize getUsed(){
        return new FileSize(body().used * 1024L * 1024L);
    }

    public FileSize getFree(){
        return new FileSize((body().total - body().used) * 1024L * 1024L);
    }

    public boolean getOverquota() { return body().overquota; }

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