package camo.mailru.api;

import java.util.Date;

/// <summary>
/// Server file info.
/// </summary>
public class File
{
    /// <summary>
    /// Initializes a new instance of the <see cref="File" /> class.
    /// </summary>
    public File()
    {
    }

    /// <summary>
    /// Initializes a new instance of the <see cref="File" /> class.
    /// </summary>
    /// <param name="name">Folder name.</param>
    /// <param name="fullPath">Full folder path.</param>
    public File(String name, String fullPath)
    {
        this.Name = name;
        this.FullPath = fullPath;
    }

    /// <summary>
    /// Gets file name.
    /// </summary>
    /// <value>File name.</value>
    private String Name;
    public String getName() {
        return Name;
    }
    void setName(String name) {
        Name = name;
    }
    /// <summary>
    /// Gets file hash value.
    /// </summary>
    /// <value>File hash.</value>
    private String Hash;
    public  String getHash() {
        return Hash;
    }
    void setHash(String hash) {
        Hash = hash;
    }

    /// <summary>
    /// Gets file size.
    /// </summary>
    /// <value>File size.</value>
    private FileSize Size;
    public FileSize getSize() {
        return Size;
    }
    void setSize(FileSize size) {
        Size = size;
    }

    /// <summary>
    /// Gets full file path with name in server.
    /// </summary>
    /// <value>Full file path.</value>
    private String FullPath;
    public String getFullPath() {
        return FullPath;
    }
    void setFullPath(String fullPath) {
        FullPath = fullPath;
    }

    /// <summary>
    /// Gets public file link.
    /// </summary>
    /// <value>Public link.</value>
    private String PublicLink;
    public String getPublicLink() {
        return PublicLink;
    }
    void setPublicLink(String publicLink) {
        PublicLink = publicLink;
    }

    /// <summary>
    /// Gets cloud file type.
    /// </summary>
    private FileType Type;
    public FileType getType() {
        return Type;
    }
    void setType(FileType type) {
        Type = type;
    }

    /// <summary>
    /// Gets last modified time of file in UTC format.
    /// </summary>
    private Date LastModifiedTimeUTC;
    public Date getLastModifiedTimeUTC() {
        return LastModifiedTimeUTC;
    }
    void setLastModifiedTimeUTC(Date lastModifiedTimeUTC) {
        LastModifiedTimeUTC = lastModifiedTimeUTC;
    }

    /// <summary>
    /// Gets or sets base file name.
    /// </summary>
    String PrimaryName;

    /// <summary>
    /// Gets or sets base file size.
    /// </summary>
    /// <value>File size.</value>
    FileSize PrimarySize;
}
