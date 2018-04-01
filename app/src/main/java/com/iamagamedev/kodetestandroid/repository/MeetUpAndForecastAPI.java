package com.iamagamedev.kodetestandroid.repository;

import com.iamagamedev.kodetestandroid.repository.model.forecastObject.ForecastObject;
import com.iamagamedev.kodetestandroid.repository.model.meetUpObject.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Михан on 05.03.2018.
 */

public interface MeetUpAndForecastAPI {

    @GET("2/cities?&country=ru")
    Call<Results> getCityList();

    @GET("data/2.5/forecast?")
    Call<ForecastObject> getForecast(@Query("lat") double cityLat, @Query("lon") double cityLon, @Query("APPID") String key);
}
