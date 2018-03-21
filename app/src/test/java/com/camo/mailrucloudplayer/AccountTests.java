package com.camo.mailrucloudplayer;

import android.util.Log;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;

import java.io.IOException;

import camo.mailru.api.Account;
import camo.mailru.api.DiskUsage;

/**
 * Created by GAlekseev on 15.03.2018.
 */



public class AccountTests {

    private Account account = null;

    @Before
    public void setUp() throws Exception {
        PersistentCookieJar cookieJar =
                new PersistentCookieJar(
                        new SetCookieCache(),
                        new MemoryCookiePersistor()
                );

        account = new Account(CONSTANTS.TEST_LOGIN, CONSTANTS.TEST_PASSWORD, cookieJar);
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
        PersistentCookieJar cookieJar =
                new PersistentCookieJar(
                        new SetCookieCache(),
                        new MemoryCookiePersistor()
                );

        Account failAccount = new Account(CONSTANTS.TEST_LOGIN, CONSTANTS.TEST_WRONG_PASSWORD, cookieJar);
        failAccount.Login();
        String token = failAccount.getAuthToken();
        Log.v("TESTS", "auth token: " + token);
        assertNull(failAccount.getAuthToken());
    }


    @Test
    public void TestGettingAccountInfo() throws Exception {
        DiskUsage diskUsage = this.account.getDiskUsage();
        assertNotNull(diskUsage);
        assertTrue(diskUsage.getFree().getValue()> 0L
            && diskUsage.getTotal().getValue() > 0L
            && diskUsage.getUsed().getValue() > 0L
        );
    }
}
