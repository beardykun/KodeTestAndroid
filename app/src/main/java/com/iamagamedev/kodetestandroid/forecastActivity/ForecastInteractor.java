package com.iamagamedev.kodetestandroid.forecastActivity;


import com.iamagamedev.kodetestandroid.repository.Repository;

/**
 * Created by Михан on 11.03.2018.
 */

public class ForecastInteractor implements IForecastInteractor {

    @Override
    public void getForecast(String cityLatLon, onForecastListener listener) {
        String[]latLon = cityLatLon.split(",");

        Repository.getForecast(Double.parseDouble(latLon[0]), Double.parseDouble(latLon[1]), listener);
    }
}
