package camo.mailru.api.json;

import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class File extends JsonResponse<FileMeta> {

    public Date getLastModifiedTimeUTC() { return new Date(body().mtime); }
    public String getVirusScan() { return body().virusScan; }
    public String getName() { return body().name; }
    public FileSize getSize() { return new FileSize(body().size);}
    public String getHash() { return body().hash; }
    public String getKind() { return body().kind; }
    public String getPublicLink() { return "https://cloud.mail.ru/public/" + body().weblink; }
    public FileType getType() { return FileType.SingleFile; }
    public String getFullPath() { return body().home; }
}
