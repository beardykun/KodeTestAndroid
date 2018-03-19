package com.iamagamedev.kodetestandroid;

import android.app.Application;

import com.iamagamedev.kodetestandroid.repository.RetrofitForMyApp;

/**
 * Created by Михан on 05.03.2018.
 */

public class ThisApplication extends Application{

    private static ThisApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        initRetrofit();
    }

    public static ThisApplication getInstance() {
        return instance;
    }

    private void initRetrofit() {
        RetrofitForMyApp.init();
    }
}
