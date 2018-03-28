package camo.mailru.api.json;

/**
 * Created by GAlekseev on 26.03.2018.
 */

/**
 * Cloud file type.
 */
public enum FileType
{
    /**
     * File as single file.
     */
    SingleFile,

    /**
     * File composed from several pieces, this file type has not hash and public link.
     */
    MultiFile
}
