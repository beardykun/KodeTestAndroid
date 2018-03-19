package com.iamagamedev.kodetestandroid.mainActivity;

import com.iamagamedev.kodetestandroid.repository.RetrofitForMyApp;
import com.iamagamedev.kodetestandroid.cityList.CityListActivity;
import com.iamagamedev.kodetestandroid.forecastActivity.ForecastActivity;

/**
 * Created by Михан on 05.03.2018.
 */

public class MainPresenter implements IMainPresenter {

    private IMainView view;

    @Override
    public void onAttachView(IMainView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        view = null;
    }

    @Override
    public void hideReturnDate() {
        if (view != null)
            view.hideReturnDate();
    }

    @Override
    public void showReturnDate() {
        if (view != null)
            view.showReturnDate();
    }

    @Override
    public void exchangeDestination() {
        if (view != null)
            view.destinationExchange();
    }

    @Override
    public void adultPlus() {
        if (view != null)
            view.adultPlusPress();
    }

    @Override
    public void kidPlus() {
        if (view != null)
            view.kidPlusPress();
    }

    @Override
    public void childPlus() {
        if (view != null)
            view.childPlusPress();
    }

    @Override
    public void adultMinus() {
        if (view != null)
            view.adultMinusPress();
    }

    @Override
    public void kidMinus() {
        if (view != null)
            view.kidMinusPress();
    }

    @Override
    public void childMinus() {
        if (view != null)
            view.childMinusPress();
    }

    @Override
    public void onCityClick(boolean destination) {
        if (view != null)
            view.goToNextActivity(CityListActivity.class, destination);
    }

    @Override
    public void forecastWeather() {
        if (view != null)
            view.goToForecastActivity(ForecastActivity.class);
    }

    @Override
    public void changeBaseUrl(String url) {
        if (!RetrofitForMyApp.getBaseUrl().equals(url))
            RetrofitForMyApp.changeApiBaseUrl(url);
    }
}
