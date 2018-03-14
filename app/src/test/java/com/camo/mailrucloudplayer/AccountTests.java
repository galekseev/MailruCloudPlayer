package com.camo.mailrucloudplayer;

import static org.junit.Assert.*;
import org.junit.Test;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;

import camo.mailru.api.Account;

/**
 * Created by GAlekseev on 15.03.2018.
 */

public class AccountTests {
    @Test
    public void A1LoginTest(){
        PersistentCookieJar cookieJar =
                new PersistentCookieJar(
                        new SetCookieCache(),
                        new MemoryCookiePersistor()
                );

        Account account = new Account("***REMOVED***", "***REMOVED***", cookieJar);
        account.Login();
        assertNotNull(account.getAuthToken());
    }
}
