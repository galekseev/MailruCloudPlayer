package camo.mailru.api.json;

import java.util.ArrayList;
import java.util.List;

public class EntriesList {

    public EntriesList(){
        folders = new ArrayList<FolderMeta>();
        files = new ArrayList<FileMeta>();
    }

    public List<FolderMeta> folders;
    public List<FileMeta> files;
}
