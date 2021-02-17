package com.digivalet.hackernews.utils;

import android.os.StrictMode;

import java.net.InetAddress;

public class GeneralFunctions {
    /**
     * Check Internet.
     */
    public static boolean isInternetAvailable() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            InetAddress ipAddr = InetAddress.getByName("www.google.com");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}
