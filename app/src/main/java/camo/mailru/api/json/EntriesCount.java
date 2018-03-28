package camo.mailru.api.json;

import com.google.gson.annotations.SerializedName;

public class EntriesCount {
    @SerializedName("folders")
    public int folders;
    @SerializedName("files")
    public int files;
}
