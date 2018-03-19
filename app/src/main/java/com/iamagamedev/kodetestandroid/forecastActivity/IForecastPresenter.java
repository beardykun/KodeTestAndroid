package com.iamagamedev.kodetestandroid.forecastActivity;

import com.iamagamedev.kodetestandroid.generalActivity.IGeneralPresenter;

/**
 * Created by Михан on 11.03.2018.
 */

public interface IForecastPresenter extends IGeneralPresenter{

    void onAttachView(IForecastView view);

    void getForecast(String cityLatLon, String cityLatLon2);

}
