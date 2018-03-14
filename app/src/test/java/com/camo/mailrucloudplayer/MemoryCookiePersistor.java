package com.camo.mailrucloudplayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Cookie;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;

/**
 * Created by GAlekseev on 15.03.2018.
 */

public class MemoryCookiePersistor implements CookiePersistor {

    List<Cookie> inMemoryCookies = new ArrayList<Cookie>();

    @Override
    public List<Cookie> loadAll() {
        List<Cookie> cookies = new ArrayList<>(inMemoryCookies.size());
        cookies.addAll(inMemoryCookies);
        return cookies;
    }

    @Override
    public void saveAll(Collection<Cookie> cookies) {
        inMemoryCookies.clear();
        inMemoryCookies.addAll(cookies);
    }

    @Override
    public void removeAll(Collection<Cookie> cookies) {
        inMemoryCookies.removeAll(cookies);
    }

    @Override
    public void clear() {
        inMemoryCookies.clear();
    }
}
