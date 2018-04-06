package camo.mailru.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import camo.mailru.api.json.Folder;
import camo.mailru.api.json.FolderMeta;

/**
 * Created by AlekseevGA on 14.12.2017.
 */

public class MailruCloud {

    public static final String CLOUD_ROOT_FOLDER = "/";

    private ApiService apiService;
    private CloudApi api = null;

    private Account account = null;

    /**
     * Initializes a new instance of the MailruCloud class with login and password.
     */
    public MailruCloud(String login, String password, ApiService provider){
        this.apiService = provider;
        this.api = provider.createService();

        this.account = new Account(login, password, this.apiService);
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

        path = Utils.IsNullOrEmpty(path) ? CLOUD_ROOT_FOLDER : path;

        Folder entry = api.getFolder(path, account.getAuthToken()).execute().body();
        return entry;
    }

    /**
     * Get description and list of files and folders for root folder.
     *
     * @return Root folder.
     */
    public Folder getRoot() throws IOException {
        return getItem(CLOUD_ROOT_FOLDER);
    }

    /**
     * Gets all folders and subfolders for specific folder
     *
     * @param root Path to specific folder.
     * @return Latest copy of folder info and structure
     */
    public Folder GetFoldersTree(String root) throws IOException {
        Folder entry = getItem(root);
        Folder tree = GetFoldersTree(entry);
        return tree;
    }

    public Folder GetFoldersTree(Folder root) throws IOException
    {
        //TODO rewrite with async calls
        List<Folder> folders = new ArrayList<>();

        for (FolderMeta folder : root.getFolders())
        {
            Folder entry = getItem(folder.home);
            Folder new_folder = GetFoldersTree(entry);
            folders.add(new_folder);
        }

        root.updateFolders(folders);

        return root;
    }
}
