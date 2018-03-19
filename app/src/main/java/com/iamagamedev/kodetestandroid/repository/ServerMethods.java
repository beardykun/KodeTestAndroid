package com.iamagamedev.kodetestandroid.repository;

import com.iamagamedev.kodetestandroid.repository.model.forecastObject.ForecastObject;
import com.iamagamedev.kodetestandroid.repository.model.meetUpObject.Results;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Михан on 07.03.2018.
 */

public class ServerMethods {

    public static void getCityList(Callback<Results> cb) {

        Call<Results> call = RetrofitForMyApp.getRetrofitService()
                .getCityList();

        call.enqueue(cb);
    }

    public static void getForecast(double cityLat, double cityLon, Callback<ForecastObject> cb) {

        Call<ForecastObject> call = RetrofitForMyApp.getRetrofitService()
                .getForecast(cityLat, cityLon, "51d729c92098f4a1e42cbd63d7d8cf99");

        call.enqueue(cb);
    }
}
