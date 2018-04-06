package com.camo.mailrucloudplayer;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import camo.mailru.api.ApiService;
import camo.mailru.api.MailruApiService;
import camo.mailru.api.MailruCloud;
import camo.mailru.api.json.Folder;

public class ApiTests {

    private MailruCloud api = null;

    @Before
    public void setUp() {
        PersistentCookieJar cookieJar =
                new PersistentCookieJar(
                        new SetCookieCache(),
                        new MemoryCookiePersistor()
                );

        ApiService provider = new MailruApiService(cookieJar);

        api = new MailruCloud(CONSTANTS.TEST_LOGIN, CONSTANTS.TEST_PASSWORD, provider);
    }

    @Test
    public void testGetItem() throws IOException {
        Folder folder = api.getRoot();
        assertEquals(folder.getName(), MailruCloud.CLOUD_ROOT_FOLDER);
    }

    @Test
    public void testGetNonExistingItem() throws IOException {
        Folder folder = api.getItem("/NonExistingItem");
        assertNull(folder);
    }

    @Test
    public void testGetFolderTree() throws IOException {
        Folder folder = api.GetFoldersTree(MailruCloud.CLOUD_ROOT_FOLDER);
        assertEquals(folder.getName(), "/");
        assertEquals(folder.getFoldersCount(), 3);
        assertEquals(folder.getFilesCount(), 10);
    }

}
