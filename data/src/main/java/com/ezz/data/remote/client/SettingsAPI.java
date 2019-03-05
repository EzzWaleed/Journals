package com.ezz.data.remote.client;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public class SettingsAPI {
    public static String getApiKey() {
        return "eea27d11f797499c9fdebd2210efb9ce";
    }

    public static String getBaseURL() {
        return "https://api.themoviedb.org/";
    }

    public static Long getTimeout() {
        return 30000L;
    }

    public static SimpleDateFormat getDateNetworkFormat(){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z", Locale.US);
    }
}
