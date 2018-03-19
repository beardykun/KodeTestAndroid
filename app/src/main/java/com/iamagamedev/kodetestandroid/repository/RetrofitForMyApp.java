package com.iamagamedev.kodetestandroid.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iamagamedev.kodetestandroid.repository.MeetUpAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Михан on 05.03.2018.
 */

public class RetrofitForMyApp {

    private static String baseUrl = "https://api.meetup.com/";

    private static MeetUpAPI sMeetUpAPI;

    private RetrofitForMyApp() {
    }

    public static void init() {
        Gson gson = new GsonBuilder().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient logger = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(logger)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        sMeetUpAPI = retrofit.create(MeetUpAPI.class);
    }

    public static MeetUpAPI getRetrofitService() {
        if (sMeetUpAPI != null) {
            return sMeetUpAPI;
        } else {
            throw new IllegalStateException("Retrofit not initialized");
        }
    }

    public static void changeApiBaseUrl(String newApiBaseUrl) {
        baseUrl = newApiBaseUrl;

        init();
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
