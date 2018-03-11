package camo.mailru.api;

import okhttp3.MediaType;

/**
 * Created by AlekseevGA on 14.12.2017.
 */

public class ConstSettings {
    public static final String DOMAIN = "mail.ru";
    public static final String CLOUD_DOMAIN = "cloud.mail.ru";
    public static final String AUTH_DOMAIN = "auth.mail.ru";
    public static final String PUBLISH_FILE_LINK = CLOUD_DOMAIN + "/public/";

    public static final String USER_AGENT = "Mozilla / 5.0(Windows; U; Windows NT 5.1; en - US; rv: 1.9.0.1) Gecko / 2008070208 Firefox / 3.0.1";
    public static final String DEFAULT_ACCEPT_TYPE = "text / html,application / xhtml + xml,application / xml; q = 0.9,*/*;q=0.8";
    //public static final String DEFAULT_REQUEST_TYPE = "application/x-www-form-urlencoded";
    public static final MediaType DEFAULT_REQUEST_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

}
