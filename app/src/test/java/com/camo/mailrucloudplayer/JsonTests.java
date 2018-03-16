package com.camo.mailrucloudplayer;

import org.junit.Test;

import camo.mailru.api.JsonParser;
import camo.mailru.api.PObject;

/**
 * Created by AlekseevGA on 16.03.2018.
 */

public class JsonTests {
    public static final String JSON_TOKEN_STRING = "{\"email\":\"thor_2001@mail.ru\",\"body\":{\"token\":\"biNGPZmZujKARX873cZGHXEbtmMpd2GW\"},\"time\":1521224562356,\"status\":200}";

    @Test
    public void JSONTokenParseTest() {
        JsonParser.Parse(JSON_TOKEN_STRING, PObject.Token);
    }
}
