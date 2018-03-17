package com.camo.mailrucloudplayer;

import android.util.Log;

import static org.junit.Assert.*;

import org.junit.Test;

import camo.mailru.api.JsonParser;
import camo.mailru.api.PObject;
import camo.mailru.api.AuthToken;
import camo.mailru.api.DiskUsage;

/**
 * Created by AlekseevGA on 16.03.2018.
 */

public class JsonTests {
    public static final String JSON_TOKEN = "{\"email\":\"thor_2001@mail.ru\",\"body\":{\"token\":\"biNGPZmZujKARX873cZGHXEbtmMpd2GW\"},\"time\":1521224562356,\"status\":200}";
    public static final String JSON_DISK_USAGE = "{\"email\":\"thor_2001@mail.ru\",\"body\":{\"overquota\":false,\"used\":19426,\"total\":1048576},\"time\":1521312651784,\"status\":200}";


    @Test
    public void TestJSONTokenParse() {
        Object jobject = JsonParser.Parse(JSON_TOKEN, PObject.Token);
        assertNotNull(jobject);
        AuthToken token = (AuthToken)jobject;
        assertEquals(token.getEmail(), "thor_2001@mail.ru");
        assertEquals(token.getStatus().longValue(), 200);
        assertEquals(token.getToken(), "biNGPZmZujKARX873cZGHXEbtmMpd2GW");
        Log.v("TestJSONTokenParse", "token:" + token.toString());
    }

    @Test
    public void TestJSONDiskUsageParse(){
        Object jobject = JsonParser.Parse(JSON_DISK_USAGE, PObject.DiskUsage);
        assertNotNull(jobject);
        DiskUsage diskUsage = (DiskUsage)jobject;
        assertEquals(diskUsage.getEmail(), "thor_2001@mail.ru");
        assertEquals(diskUsage.getStatus().longValue(), 200L);
        assertEquals(diskUsage.getBody().overquota, false);
        assertEquals(diskUsage.getTotal().getValue(), 1048576L * 1024L * 1024L);
        assertEquals(diskUsage.getUsed().getValue(), 19426L * 1024L * 1024L);
        Log.v("TestJSONDiskUsageParse", "disk usage:" + diskUsage.toString());
    }
}
