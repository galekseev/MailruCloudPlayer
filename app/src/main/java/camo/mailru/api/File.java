package camo.mailru.api;

import java.util.Date;

/**
 * Server file info.
 */
public class File
{
    /**
     * Initializes a new instance of the File class.
     */
    public File()
    {
    }

    /**
     * Initializes a new instance of the File class.
     *
     * @param name Folder name.
     * @param fullPath Full folder path.
     */
    public File(String name, String fullPath)
    {
        this.Name = name;
        this.FullPath = fullPath;
    }

    private String Name;
    /**
     * Gets file name.
     *
     * @return File name.
     */
    public String getName() {
        return Name;
    }
    void setName(String name) {
        Name = name;
    }

    private String Hash;
    /**
     * Gets file hash value.
     *
     * @return File hash.
     */
    public  String getHash() {
        return Hash;
    }
    void setHash(String hash) {
        Hash = hash;
    }

    private FileSize Size;
    /**
     * Gets file size.
     *
     * @return File size.
     */
    public FileSize getSize() {
        return Size;
    }
    void setSize(FileSize size) {
        Size = size;
    }

    private String FullPath;
    /**
     * Gets full file path with name in server.
     *
     * @return Full file path.
     */
    public String getFullPath() {
        return FullPath;
    }
    void setFullPath(String fullPath) {
        FullPath = fullPath;
    }

    private String PublicLink;
    /**
     * Gets public file link.
     *
     * @return Public link.
     */
    public String getPublicLink() {
        return PublicLink;
    }
    void setPublicLink(String publicLink) {
        PublicLink = publicLink;
    }

    private FileType Type;
    /**
     * Gets cloud file type.
     *
     * @return Cloud file type.
     */
    public FileType getType() {
        return Type;
    }
    void setType(FileType type) {
        Type = type;
    }

    private Date LastModifiedTimeUTC;
    /**
     * Gets last modified time of file in UTC format.
     *
     * @return Last modified date in UTC format
     */
    public Date getLastModifiedTimeUTC() {
        return LastModifiedTimeUTC;
    }
    void setLastModifiedTimeUTC(Date lastModifiedTimeUTC) {
        LastModifiedTimeUTC = lastModifiedTimeUTC;
    }

    /**
     * Gets or sets base file name.
     */
    String PrimaryName;

    /**
     * File size.
     */
    FileSize PrimarySize;
}
