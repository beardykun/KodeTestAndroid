package com.iamagamedev.kodetestandroid.repository;

import com.iamagamedev.kodetestandroid.Constants;
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

    /*Произвожу поиск по широте и долготе, так как другие данные полученные из meetup выдают ошибки на некоторых городах
       Например города с "-" не находятся по имени, по zip и id, тоже наблюдались проблемы с поиском городов.
       По широте и долготе, тоже не всё идеально, но по крайней мере не наблюдается случаев когда ответ совсем не получен */
    public static void getForecast(double cityLat, double cityLon, Callback<ForecastObject> cb) {

        Call<ForecastObject> call = RetrofitForMyApp.getRetrofitService()
                .getForecast(cityLat, cityLon, Constants.FORECAST_API_KEY);

        call.enqueue(cb);
    }
}
