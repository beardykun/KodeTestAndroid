package com.iamagamedev.kodetestandroid.forecastActivity;

import com.iamagamedev.kodetestandroid.generalActivity.IGenetalInteractorListener;
import com.iamagamedev.kodetestandroid.repository.model.forecastObject.ForecastObject;

/**
 * Created by Михан on 11.03.2018.
 */

public interface IForecastInteractor {

    interface onForecastListener extends IGenetalInteractorListener{

        void onSuccess(ForecastObject object);
    }

    void getForecast(String cityLatLon, onForecastListener listener);
}
