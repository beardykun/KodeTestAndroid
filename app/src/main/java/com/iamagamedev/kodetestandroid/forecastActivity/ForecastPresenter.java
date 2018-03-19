package com.iamagamedev.kodetestandroid.forecastActivity;

import com.iamagamedev.kodetestandroid.repository.model.forecastObject.ForecastObject;

/**
 * Created by Михан on 11.03.2018.
 */

public class ForecastPresenter implements IForecastPresenter, IForecastInteractor.onForecastListener {

    private IForecastView view;
    private IForecastInteractor interactor;
    private ForecastObject mForecastObject;
    private String cityLatLon;

    public ForecastPresenter() {
        interactor = new ForecastInteractor();
    }

    @Override
    public void onAttachView(IForecastView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }

    @Override
    public void getForecast(String cityLatLon, String cityLatLon2) {
        if (view != null) {
            view.showProgress();
            interactor.getForecast(cityLatLon, this);
            this.cityLatLon = cityLatLon2;
        }
    }

    @Override
    public void onSuccess(ForecastObject object) {
        if (view != null) {
            view.hideProgress();
            if (mForecastObject == null) {
                mForecastObject = object;
                getForecast(cityLatLon, "");
            } else {
                view.initAdapter(mForecastObject, object);
            }
        }
    }

    @Override
    public void onError(String error, int... code) {
        if (view != null) {
            view.hideProgress();
            view.showError(error, code);
        }
    }

    @Override
    public void onError(int error, int... code) {
        if (view != null) {
            view.hideProgress();
            view.showError(error, code);
        }
    }
}
