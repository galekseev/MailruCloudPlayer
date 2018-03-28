package camo.mailru.api.json;

import com.google.gson.annotations.SerializedName;

/**
 * Folder metadata
 */
public class FolderMeta {

    @SerializedName("count")
    public EntriesCount count;
    @SerializedName("tree")
    public String tree;
    @SerializedName("name")
    public String name;
    @SerializedName("grev")
    public Long grev;
    @SerializedName("size")
    public Long size;
    @SerializedName("sort")
    public FolderSortType sort;
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
    public EntriesList list = null;
}
