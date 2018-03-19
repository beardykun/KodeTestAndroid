package com.iamagamedev.kodetestandroid;

import android.content.Context;
import android.net.ConnectivityManager;

public class OnlineChecker {

    public static boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) ThisApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }
}
