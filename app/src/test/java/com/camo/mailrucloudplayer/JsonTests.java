package com.camo.mailrucloudplayer;

import android.util.Log;

import static org.junit.Assert.*;

import org.junit.Test;

import camo.mailru.api.json.JsonObjectType;
import camo.mailru.api.json.JsonParser;
import camo.mailru.api.json.AuthToken;
import camo.mailru.api.json.DiskUsage;
import camo.mailru.api.json.Folder;

/**
 * Created by AlekseevGA on 16.03.2018.
 */

public class JsonTests {
    public static final String JSON_TOKEN = "{\"email\":\"thor_2001@mail.ru\",\"body\":{\"token\":\"biNGPZmZujKARX873cZGHXEbtmMpd2GW\"},\"time\":1521224562356,\"status\":200}";
    public static final String JSON_TOKEN_DAMAGED_LESS1 = "{\"body\":{\"token\":\"biNGPZmZujKARX873cZGHXEbtmMpd2GW\"},\"time\":1521224562356,\"status\":200}";
    public static final String JSON_TOKEN_DAMAGED_LESS2 = "{\"time\":1521224562356,\"status\":200}";
    public static final String JSON_TOKEN_DAMAGED_MORE1 = "{\"shift\":false,\"time\":1521224562356,\"status\":200}";
    public static final String JSON_TOKEN_DAMAGED_MORE2 = "{\"email\":\"thor_2001@mail.ru\",\"body\":{\"token\":\"biNGPZmZujKARX873cZGHXEbtmMpd2GW\",\"email\":\"thor_2001@mail.ru\"},\"time\":1521224562356,\"status2\":200}";
    public static final String JSON_DISK_USAGE = "{\"email\":\"thor_2001@mail.ru\",\"body\":{\"overquota\":false,\"used\":19426,\"total\":1048576},\"time\":1521312651784,\"status\":200}";
    public static final String JSON_FOLDER = "{\"email\":\"thor_2001@mail.ru\",\"body\":{\"count\":{\"folders\":3,\"files\":10},\"tree\":\"356133646333303030303030\",\"name\":\"/\",\"grev\":3601,\"size\":20370040693,\"sort\":{\"order\":\"asc\",\"type\":\"name\"},\"kind\":\"folder\",\"rev\":1479,\"type\":\"folder\",\"home\":\"/\",\"list\":[{\"count\":{\"folders\":0,\"files\":15},\"tree\":\"356133646333303030303030\",\"name\":\"Aikido\",\"grev\":1496,\"size\":1573543165,\"kind\":\"folder\",\"weblink\":\"MhfJ/1KYE1n2YK\",\"rev\":1496,\"type\":\"folder\",\"home\":\"/Aikido\"},{\"count\":{\"folders\":0,\"files\":1},\"tree\":\"356133646333303030303030\",\"name\":\"Video\",\"grev\":398,\"size\":31896573,\"kind\":\"folder\",\"rev\":398,\"type\":\"folder\",\"home\":\"/Video\"},{\"count\":{\"folders\":10,\"files\":1},\"tree\":\"356133646333303030303030\",\"name\":\"Музыка\",\"grev\":3601,\"size\":17234576001,\"kind\":\"folder\",\"rev\":3579,\"type\":\"folder\",\"home\":\"/Музыка\"},{\"mtime\":1489756251,\"virus_scan\":\"pass\",\"name\":\"AssemblyInfo.cs\",\"size\":1422,\"hash\":\"CE707246362F8E8D8A228FB95FE6C5B1FCC631D5\",\"kind\":\"file\",\"type\":\"file\",\"home\":\"/AssemblyInfo.cs\"},{\"mtime\":1489568596,\"virus_scan\":\"pass\",\"name\":\"Builds.zip\",\"size\":210889832,\"hash\":\"690A830645F6D9937E8131A87E6690B0300EACC6\",\"kind\":\"file\",\"weblink\":\"4tqr/jTkrsNpCm\",\"type\":\"file\",\"home\":\"/Builds.zip\"},{\"mtime\":1489588930,\"virus_scan\":\"pass\",\"name\":\"devexpress64 (13.2).zip\",\"size\":224555852,\"hash\":\"9BDEE8006C22202583D7E6A919BBB97D1BF81FD3\",\"kind\":\"file\",\"type\":\"file\",\"home\":\"/devexpress64 (13.2).zip\"},{\"mtime\":1489588033,\"virus_scan\":\"pass\",\"name\":\"DevExpress .NET 14.2.8.zip\",\"size\":293234424,\"hash\":\"25F96E856733B69590F7A6044D88581D6DD8072F\",\"kind\":\"file\",\"type\":\"file\",\"home\":\"/DevExpress .NET 14.2.8.zip\"},{\"mtime\":1488294477,\"virus_scan\":\"pass\",\"name\":\"MailRuCloudApi.cs\",\"size\":77076,\"hash\":\"9102E159F2B4F37FCA96826CC5507BD9E7463A29\",\"kind\":\"file\",\"type\":\"file\",\"home\":\"/MailRuCloudApi.cs\"},{\"mtime\":1489764461,\"virus_scan\":\"pass\",\"name\":\"Mail.Ru-.net-cloud-client.zip\",\"size\":1211334,\"hash\":\"A823B0F3D2424196796826D0AF522F9683420C99\",\"kind\":\"file\",\"type\":\"file\",\"home\":\"/Mail.Ru-.net-cloud-client.zip\"},{\"mtime\":1489587955,\"virus_scan\":\"pass\",\"name\":\"pxml.zip\",\"size\":117046,\"hash\":\"FF193815FF7CAF3C0075B338148A8E52D600FAB0\",\"kind\":\"file\",\"type\":\"file\",\"home\":\"/pxml.zip\"},{\"mtime\":1489586503,\"virus_scan\":\"pass\",\"name\":\"Rockettheme.zip\",\"size\":799781551,\"hash\":\"F91633314C3F1BD12FCCF36ED4152CCECEA68C2F\",\"kind\":\"file\",\"type\":\"file\",\"home\":\"/Rockettheme.zip\"},{\"mtime\":1411932016,\"virus_scan\":\"pass\",\"name\":\"Tatiana_Litvinova_2014_ru.docx\",\"size\":31252,\"hash\":\"540063DF8B9D522E4F66F0FDB5239E91F42B8E0A\",\"kind\":\"file\",\"type\":\"file\",\"home\":\"/Tatiana_Litvinova_2014_ru.docx\"},{\"mtime\":1489588896,\"virus_scan\":\"pass\",\"name\":\"ztsTjNw1JvQ.zip\",\"size\":125165,\"hash\":\"E96AC1C840037AEA9E8EAAA2421A07CD7A8DE215\",\"kind\":\"file\",\"type\":\"file\",\"home\":\"/ztsTjNw1JvQ.zip\"}]},\"time\":1522268637343,\"status\":200}";

    @Test
    public void TestJSONTokenParse() {
        Object jobject = JsonParser.Parse(JSON_TOKEN, JsonObjectType.Token);
        //Object jobject = JsonParser.Parse(JSON_TOKEN_DAMAGED_MORE2, JsonObjectType.Token);
        assertNotNull(jobject);
        AuthToken token = (AuthToken)jobject;
        assertEquals(token.getEmail(), "thor_2001@mail.ru");
        assertEquals(token.getStatus().longValue(), 200);
        assertEquals(token.getToken(), "biNGPZmZujKARX873cZGHXEbtmMpd2GW");
        Log.v("TestJSONTokenParse", "token:" + token.toString());
    }

    @Test
    public void TestJSONDiskUsageParse(){
        Object jobject = JsonParser.Parse(JSON_DISK_USAGE, JsonObjectType.DiskUsage);
        assertNotNull(jobject);
        DiskUsage diskUsage = (DiskUsage)jobject;
        assertEquals(diskUsage.getEmail(), "thor_2001@mail.ru");
        assertEquals(diskUsage.getStatus().longValue(), 200L);
        assertEquals(diskUsage.getOverquota(), false);
        assertEquals(diskUsage.getTotal().getValue(), 1048576L * 1024L * 1024L);
        assertEquals(diskUsage.getUsed().getValue(), 19426L * 1024L * 1024L);
        Log.v("TestJSONDiskUsageParse", "disk usage:" + diskUsage.toString());
    }

    @Test
    public void TestJSONFolderDeserialization(){
        Object jobject = JsonParser.Parse(JSON_FOLDER, JsonObjectType.Entry);
        assertNotNull(jobject);
        Folder folder = (Folder) jobject;

        assertNotNull(folder);
        assertEquals(folder.getFoldersCount(), 3);
        assertEquals(folder.getFolders().size(), 3);
        assertEquals(folder.getFiles().size(), 10);
    }
}
