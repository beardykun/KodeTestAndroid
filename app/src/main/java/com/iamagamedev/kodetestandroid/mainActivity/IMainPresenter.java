package com.iamagamedev.kodetestandroid.mainActivity;

import android.widget.TextView;

import com.iamagamedev.kodetestandroid.generalActivity.IGeneralPresenter;

/**
 * Created by Михан on 05.03.2018.
 */

public interface IMainPresenter extends IGeneralPresenter {

    void onAttachView(IMainView view);

    void hideReturnDate();

    void showReturnDate();

    void exchangeDestination(TextView dep, TextView dest, String from, String to,
                             String cityLatLon, String cityLatLon2);

    void adultPlus();

    void kidPlus();

    void childPlus();

    void adultMinus();

    void kidMinus();

    void childMinus();

    void onCityClick(boolean destination);

    void forecastWeather(String from, String to, String cityLatLon, String cityLatLon2);

    void changeBaseUrl(String url);

}
