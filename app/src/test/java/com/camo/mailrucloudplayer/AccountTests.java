package com.camo.mailrucloudplayer;

import android.util.Log;

import static org.junit.Assert.*;
import org.junit.Test;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;

import java.io.IOException;

import camo.mailru.api.Account;

/**
 * Created by GAlekseev on 15.03.2018.
 */

public class AccountTests {
    @Test
    public void A1LoginTest() throws IOException {
        PersistentCookieJar cookieJar =
                new PersistentCookieJar(
                        new SetCookieCache(),
                        new MemoryCookiePersistor()
                );

        Account account = new Account(CONSTANTS.TEST_LOGIN, CONSTANTS.TEST_PASSWORD, cookieJar);
        account.Login();
        String token = account.getAuthToken();
        Log.v("TESTS", "auth token: " + token);
        assertNotNull(account.getAuthToken());
    }
}
