package camo.mailru.api.json;

import java.util.List;

public class Folder extends JsonResponse<FolderMeta> {
    /**
     * Gets number of the subfolders in the folder
     */
    public int getFoldersCount() {
        return body().count.folders;
    }

    /**
     * Gets number of the files in the folder.
     */
    public int getFilesCount() {
        return body().count.files;
    }

    /**
     * Gets list of the subfolders with their specification.
     */
    public List<FolderMeta> getFolders() {
        if (body() == null || body().list == null)
            return null;
        return body().list.folders;
    }

    /**
     * Gets list of the files with their specification.
     */
    public List<FileMeta> getFiles() {
        if (body() == null || body().list == null)
            return null;
        return body().list.files;
    }

    /**
     * Gets full entry path on the server.
     */
    public String getFullPath() {
        return body().home;
    }

    /**
     * Gets entry kind.
     */
    public String getKind() {
        return body().kind;
    }

    /**
     * Gets entry type.
     */
    public String getType() {
        return body().type;
    }

    /**
     * Gets folder size.
     */
    public FileSize getSize() {
        return new FileSize(body().size);
    }

    /**
     * Gets folder name.
     */
    public String getName() {
        return body().name;
    }

    /**
     * Gets public folder link.
     */
    public String getPublicLink() {
        return "https://cloud.mail.ru/public/" + body().weblink;
    }

    /**
     * Get folder revision number
     */
    public long getRevision() {
        return body().rev;
    }

    /**
     * Gets folder global revision number
     */
    public long getGlobalRevision() {
        return body().grev;
    }

    /**
     * Gets status if folder contents require update
     * @return true - if folder contents require update from server, false - otherwise
     */
    public boolean requiresUpdate(Folder folder)
    {
        return body().grev != body().rev;
    }


}
