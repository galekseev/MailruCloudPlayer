package camo.mailru.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FolderJson extends JsonResponse<FolderJson.FolderBody> {

    public class ItemsCount{
        @SerializedName("folders")
        public Long folders;
        @SerializedName("files")
        public Long files;
    }

    public class SortType {
        @SerializedName("order")
        public String order;
        @SerializedName("type")
        public String type;
    }

    public class FolderBody {

        @SerializedName("count")
        public ItemsCount count;
        @SerializedName("tree")
        public String tree;
        @SerializedName("name")
        public String name;
        @SerializedName("grev")
        public Long grev;
        @SerializedName("size")
        public Long size;
        @SerializedName("sort")
        public SortType sort;
        @SerializedName("kind")
        public String kind;
        @SerializedName("weblink")
        public String weblink;
        @SerializedName("rev")
        public Long rev;
        @SerializedName("type")
        public String type;
        @SerializedName("home")
        public String home;
        @SerializedName("list")
        public List<FolderBody> list = null;
    }
}
