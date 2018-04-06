package camo.mailru.api;

import java.util.stream.Stream;

import camo.mailru.api.json.DiskUsage;

import camo.mailru.api.json.Folder;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CloudApi {
    @Headers({
            "Accept: application/json",
            "User-Agent: " + RequestBuilder.USER_AGENT
    })
    @GET("user/space")
    Call<DiskUsage> getDiskUsage(
            @Query("api") int api,
            @Query("email") String email,
            @Query("token") String token);

    @Headers({
            "Accept: application/json",
            "User-Agent: " + RequestBuilder.USER_AGENT
    })
    @GET("folder")
    Call<Folder> getFolder(
            @Query("home") String path,
            @Query("token") String token);

//    @Headers({
//            "Accept: */*",
//            "User-Agent: " + RequestBuilder.USER_AGENT,
//            "Origin: " + RequestBuilder.PROTOCOL + RequestBuilder.CLOUD_DOMAIN
//    })
//    @POST("folder/add")
//    Call<Void> addFolder(String folder);

    /*
    public boolean Copy(Folder folder, Folder destinationFolder);
    public boolean Copy(Folder folder, String destinationPath);
    public boolean Copy(File file, Folder destinationFolder);
    public boolean Copy(File file, String destinationPath);

    public boolean Rename(Folder folder, String newFileName);
    public boolean Rename(File file, String newFileName);

    public boolean Move(Folder folder, Folder destinationFolder);
    public boolean Move(Folder folder, String destinationPath);
    public boolean Move(File file, Folder destinationFolder);
    public boolean Move(File file, String destinationPath);

    public boolean CreateFolder(String name, String createIn);

    public boolean Remove(File file);
    public boolean Remove(Folder folder);
    public boolean Remove(String fullPath);

    public String GetPublishDirectLink(String publishLink, FileType fileType);

    public boolean UnpublishLink(File file);
    public boolean UnpublishLink(Folder folder);

    public boolean GetFile(File file, String destinationPath);
    public boolean GetFile(File file);

    public File UploadFileAsync(String fileName, Stream content, String destinationPath);
    public boolean UploadFile(File file, String destinationPath);
    //*/
}
