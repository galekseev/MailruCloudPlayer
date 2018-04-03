package com.camo.mailrucloudplayer;

import android.util.Log;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;

import java.io.IOException;

import camo.mailru.api.Account;
import camo.mailru.api.json.DiskUsage;

/**
 * Created by GAlekseev on 15.03.2018.
 */

public class AccountTests {

    private Account account = null;
    private Account failAccount = null;

    @Before
    public void setUp() {
        PersistentCookieJar cookieJar =
                new PersistentCookieJar(
                        new SetCookieCache(),
                        new MemoryCookiePersistor()
                );

        account = new Account(CONSTANTS.TEST_LOGIN, CONSTANTS.TEST_PASSWORD, cookieJar);

        failAccount = new Account(CONSTANTS.TEST_LOGIN, CONSTANTS.TEST_WRONG_PASSWORD, cookieJar);
    }

    @Test
    public void A1LoginTest() throws IOException {
        account.Login();
        String token = account.getAuthToken();
        Log.v("TESTS", "auth token: " + token);
        assertNotNull(account.getAuthToken());
    }

    @Test
    public void A1LoginFailTest() throws IOException {
        failAccount.Login();
        String token = failAccount.getAuthToken();
        Log.v("TESTS", "auth token: " + token);
        assertNull(failAccount.getAuthToken());
    }


    @Test
    public void testGettingAccountInfo() throws Exception {
        DiskUsage diskUsage = this.account.getDiskUsage();
        assertNotNull(diskUsage);
        assertTrue(diskUsage.getFree().getValue()> 0L
            && diskUsage.getTotal().getValue() > 0L
            && diskUsage.getUsed().getValue() > 0L
        );
    }

    @Test
    public void testGettingAccountInfoApi() throws Exception {
        DiskUsage diskUsage = this.account.diskUsage();
        assertNotNull(diskUsage);
        assertTrue(diskUsage.getFree().getValue()> 0L
                && diskUsage.getTotal().getValue() > 0L
                && diskUsage.getUsed().getValue() > 0L
        );
    }


    @Test
    public void testFailGetAccountInfo() throws Exception {
        try {
            DiskUsage diskUsage = this.failAccount.getDiskUsage();
            fail("Login didn't fail as expected");
        }
        catch (IOException e){
            assertEquals(e.getMessage(), "Auth token has't been retrieved.");
        }
    }
}
