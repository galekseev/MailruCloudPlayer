package camo.mailru.api;

import java.util.List;

/**
 * Server folder/file info.
 */
public class Folder
{
    /**
     * Initializes a new instance of the Folder class.
     */
    public Folder()
    {
    }

    /**
     * Initializes a new instance of the Folder class.
     *
     * @param foldersCount   Number of folders.
     * @param filesCount     Number of files.
     * @param name           Folder name.
     * @param size           Folder size.
     * @param fullPath       Full folder path.
     * @param publicLink     Public folder link.
     */
    public Folder(
            int filesCount,             //count.files
            int foldersCount,           //count.folders
            int globalRevision,         //grev
            String fullPath,            //home
            String kind,                //kind
            List<Folder> folders,       //list.[]
            List<File> files,           //list.[]
            String name,                //name
            int revision,               //rev
            FileSize size,              //size
            String tree,                //tree
            String type,                //type
            String publicLink           //weblink
    )
    {
        this.NumberOfFolders = foldersCount;
        this.NumberOfFiles = filesCount;
        this.GlobalRevision = globalRevision;
        this.FullPath = fullPath;
        this.Kind = kind;
        this.Folders = folders;
        this.Files = files;
        this.Name = name;
        this.Revision = revision;
        this.Size = size;
        this.Type = type;
        this.PublicLink = publicLink;
    }

    private int NumberOfFolders;
    /**
     * Gets number of the folders.
     *
     * @return Number of the folders.
     */
    public int getNumberOfFolders() {
        return NumberOfFolders;
    }
    void setNumberOfFolders(int numberOfFolders) {
        NumberOfFolders = numberOfFolders;
    }

    /**
     * Gets number of the files.
     */
    private int NumberOfFiles;// { get; internal set; }
    public int getNumberOfFiles() {
        return NumberOfFiles;
    }
    void setNumberOfFiles(int numberOfFiles) {
        NumberOfFiles = numberOfFiles;
    }


    private List<Folder> Folders;
    /**
     * Gets list of the folders with their specification.
     */
    public List<Folder> getFolders() {
        return Folders;
    }
    void setFolders(List<Folder> folders) {
        Folders = folders;
    }

    private List<File> Files;
    /**
     * Gets list of the files with their specification.
     */
    public List<File> getFiles() {
        return Files;
    }
    void setFiles(List<File> files) {
        Files = files;
    }

    /**
     * Gets full entry path on the server.
     */
    private String FullPath;
    public String getFullPath() {
        return FullPath;
    }
    void setFullPath(String fullPath) {
        FullPath = fullPath;
    }

    /**
     * Gets entry kind.
     */
    private String Kind;
    public String getKind() {
        return Kind;
    }
    void setKind(String kind) {
        Kind = kind;
    }

    /**
     * Gets entry type.
     */
    private String Type;
    public String getType() {
        return Type;
    }
    void setType(String type) {
        Type = type;
    }

    private String Name;
    /*
     * Gets folder name.
     *
     * @return Folder name.
     */
    public String getName() {
        return Name;
    }
    void setName(String name) {
        Name = name;
    }

    private FileSize Size;
    /**
     * Gets folder size.
     *
     * @return Folder size.
     */
    public FileSize getSize() {
        return Size;
    }
    void setSize(FileSize size) {
        Size = size;
    }

    private String PublicLink;
    /**
     * Gets public folder link.
     *
     * @return Public link.
     */
    public String getPublicLink() {
        return PublicLink;
    }
    void setPublicLink(String publicLink) {
        PublicLink = publicLink;
    }

    private int Revision;
    /**
     * Get folder revision number
     *
     * @return Revision number
     */
    public int getRevision() {
        return Revision;
    }
    void setRevision(int revision) {
        Revision = revision;
    }

    private int GlobalRevision;
    /**
     * Gets folder global revision number
     *
     * @return Global revision number
     */
    public int getGlobalRevision() {
        return GlobalRevision;
    }
    void setGlobalRevision(int globalRevision) {
        GlobalRevision = globalRevision;
    }

    /**
     * Gets status if folder contents require update
     *
     * @return true - if folder contents require update from server, false - otherwise
     */
    public boolean requiresUpdate(Folder folder)
    {
        return this.GlobalRevision != folder.GlobalRevision;
    }
}
