package camo.mailru.api;

/**
 * Created by GAlekseev on 26.03.2018.
 */

//-----------------------------------------------------------------------
// <created file="Folder.cs">
//     Mail.ru cloud client created in 2016.
// </created>
// <author>Korolev Erast.</author>
//-----------------------------------------------------------------------

import java.util.List;

/**
 * Server file info.
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

    /**
     * Gets number of the folders.
     */
    private int NumberOfFolders;

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

    /**
     * Gets list of the folders with their specification.
     */
    public List<Folder> Folders;

    public List<Folder> getFolders() {
        return Folders;
    }
    void setFolders(List<Folder> folders) {
        Folders = folders;
    }

    /// <summary>
    /// Gets list of the files with their specification.
    /// </summary>
    public List<File> Files;
    public List<File> getFiles() {
        return Files;
    }
    void setFiles(List<File> files) {
        Files = files;
    }

    /// <summary>
    /// Gets full entry path on the server.
    /// </summary>
    public String FullPath;
    public String getFullPath() {
        return FullPath;
    }
    void setFullPath(String fullPath) {
        FullPath = fullPath;
    }

    /// <summary>
    /// Gets entry kind.
    /// </summary>
    public String Kind;
    public String getKind() {
        return Kind;
    }
    void setKind(String kind) {
        Kind = kind;
    }

    /// <summary>
    /// Gets entry type.
    /// </summary>
    public String Type;
    public String getType() {
        return Type;
    }
    void setType(String type) {
        Type = type;
    }

    /// <summary>
    /// Gets folder name.
    /// </summary>
    /// <value>Folder name.</value>
    public String Name;
    public String getName() {
        return Name;
    }
    void setName(String name) {
        Name = name;
    }

    /// <summary>
    /// Gets folder size.
    /// </summary>
    /// <value>Folder size.</value>
    public FileSize Size;
    public FileSize getSize() {
        return Size;
    }
    void setSize(FileSize size) {
        Size = size;
    }

    /// <summary>
    /// Gets public folder link.
    /// </summary>
    /// <value>Public link.</value>
    public String PublicLink;
    public String getPublicLink() {
        return PublicLink;
    }
    void setPublicLink(String publicLink) {
        PublicLink = publicLink;
    }

    /// <summary>
    /// Get folder revision number
    /// </summary>
    /// <value>Revision number</value>
    public int Revision;
    public int getRevision() {
        return Revision;
    }
    void setRevision(int revision) {
        Revision = revision;
    }

    /// <summary>
    /// Gets folder global revision number
    /// </summary>
    /// <value>Global revision number</value>
    public int GlobalRevision;
    public int getGlobalRevision() {
        return GlobalRevision;
    }
    void setGlobalRevision(int globalRevision) {
        GlobalRevision = globalRevision;
    }

    public boolean requiresUpdate(Folder folder)
    {
        return this.GlobalRevision != folder.GlobalRevision;
    }
}
