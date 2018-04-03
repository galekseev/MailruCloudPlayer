package com.camo.mailrucloudplayer;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import camo.mailru.api.Account;
import camo.mailru.api.MailruCloud;
import camo.mailru.api.json.Folder;
import camo.mailru.api.json.FolderMeta;

public class ApiTests {
    private Account account = null;
    private MailruCloud api = null;

    @Before
    public void setUp() {
        PersistentCookieJar cookieJar =
                new PersistentCookieJar(
                        new SetCookieCache(),
                        new MemoryCookiePersistor()
                );

        account = new Account(CONSTANTS.TEST_LOGIN, CONSTANTS.TEST_PASSWORD, cookieJar);

        api = new MailruCloud(account);
    }

    @Test
    public void testGetItem() throws IOException {
        Folder folder = api.getRoot();
        assertEquals(folder.getName(), "/");
    }

    @Test
    public void testGetNonExistingItem() {
        try {
            Folder folder = api.getItem("/NonExistingItem");
            fail("Either folder exists on server or getItem isn't working as expected");
        }
        catch (Exception e)
        {
            assertEquals(e.getMessage(), "Response failed with code: 404");
        }

    }

    @Test
    public void testGetFolderTree() throws IOException {
        Folder folder = api.GetFoldersTreeAsync(MailruCloud.CLOUD_ROOT_FOLDER);
        assertEquals(folder.getName(), "/");
    }

}
