package camo.mailru.api.json;

import com.google.gson.annotations.SerializedName;

public class FileMeta{
    @SerializedName("mtime")
    public Long mtime;
    @SerializedName("virus_scan")
    public String virusScan;
    @SerializedName("name")
    public String name;
    @SerializedName("size")
    public Long size;
    @SerializedName("hash")
    public String hash;
    @SerializedName("kind")
    public String kind;
    @SerializedName("weblink")
    public String weblink;
    @SerializedName("type")
    public String type;
    @SerializedName("home")
    public String home;
}
