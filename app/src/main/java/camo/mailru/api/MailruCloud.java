package camo.mailru.api;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.IOException;

import okhttp3.CookieJar;

/**
 * Created by AlekseevGA on 14.12.2017.
 */

public class MailruCloud {

    private Account account = null;
    /**
     * Initializes a new instance of the MailruCloud class. Do not forget to set Account property before using any API functions.
     */
    public MailruCloud()
    {
    }

    public MailruCloud(String login, String password, CookieJar cookieJar){
        this.account = new Account(login, password, cookieJar);
    }

    public boolean Login() throws IOException{
        return this.account.Login();
    }
}
