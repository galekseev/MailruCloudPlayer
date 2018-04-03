package camo.mailru.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import camo.mailru.api.json.Folder;
import camo.mailru.api.json.FolderMeta;
import camo.mailru.api.json.JsonObjectType;
import camo.mailru.api.json.JsonParser;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by AlekseevGA on 14.12.2017.
 */

public class MailruCloud {

    public static final String CLOUD_ROOT_FOLDER = "/";

    private Account account = null;
    private OkHttpClient okHttpClient = null;

    /**
     * Initializes a new instance of the MailruCloud class with login and password.
     */
    public MailruCloud(String login, String password, CookieJar cookieJar){
        this.account = new Account(login, password, cookieJar);

        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(this.account.getCookieJar())
                .build();
    }

    /**
     * Initializes a new instance of the MailruCloud class.
     */
    public MailruCloud(Account account){
        this.account = account;

        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(this.account.getCookieJar())
                .build();
    }

    public boolean Login() throws IOException{
        return this.account.Login();
    }

    /**
     * Get list of files and folders from account.
     *
     * @param path Path in the cloud to return the list of the items.
     * @return Folder for requested path.
     */
    public Folder getItem(String path) throws IOException{
        this.account.ensureAuth();

        if (path == null || path.isEmpty())
            path = "/";


        Request request = RequestBuilder.buildGetItemRequest(path, account.getAuthToken());
        Response response = executeRequest(request);

        String json = response.body().string();
        Folder entry = (Folder)JsonParser.Parse(json, JsonObjectType.Entry);

        return entry;
    }

    /**
     * Get description and list of files and folders for root folder.
     *
     * @return Root folder.
     */
    public Folder getRoot() throws IOException{
        return getItem(CLOUD_ROOT_FOLDER);
    }

    /**
     * Gets all folders and subfolders for specific folder
     *
     * @param root Path to specific folder.
     * @return Latest copy of folder info and structure
     */
    public Folder GetFoldersTreeAsync(String root) throws IOException
    {
        Folder entry = getItem(root);
        Folder tree = GetFoldersTreeAsync(entry);
        return tree;
    }

    public Folder GetFoldersTreeAsync(Folder root) throws IOException
    {
        //TODO Create implementation
//        return null;
        List<Folder> folders = new ArrayList<>();

        for (FolderMeta folder : root.getFolders())
        {
            Folder entry = getItem(folder.home);
            Folder new_folder = GetFoldersTreeAsync(entry);
            folders.add(new_folder);
        }

        root.updateFolders(folders);

        return root;
    }

    private Response executeRequest(Request request) throws IOException{
        //TODO: Add http code exception handling
        Response response = okHttpClient.newCall(request).execute();

        if (!response.isSuccessful()) {
            int code = response.code();
            response.close();
            throw new IOException("Response failed with code: " + code);
        }

        return response;
    }
}
