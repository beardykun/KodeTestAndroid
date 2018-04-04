package com.iamagamedev.kodetestandroid.cityList;

import android.content.Intent;

import com.iamagamedev.kodetestandroid.Constants;
import com.iamagamedev.kodetestandroid.ThisApplication;
import com.iamagamedev.kodetestandroid.repository.model.meetUpObject.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михан on 08.03.2018.
 */

public class CityListPresenter implements ICityListPresenter, ICityListInteractor.onCityListListener {

    private ICityListView view;
    private ICityListInteractor mInteractor;

    CityListPresenter() {
        mInteractor = new CitiListInteractor();
    }

    @Override
    public void onAttachView(ICityListView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        view = null;
    }

    @Override
    public void onCityClick(Intent intent, String city, String latLon, boolean fromTo) {
        intent.putExtra(Constants.CITY, city);
        intent.putExtra(Constants.CITY_LAT_LON, latLon);
        intent.putExtra(Constants.FROM_TO, fromTo);
        ThisApplication.getInstance().startActivity(intent);
    }

    @Override
    public void onShowList() {
        if (view != null) {
            view.showProgress();
            mInteractor.onCityRequest(this);
        }
    }

    @Override
    public void onSuccess(List<Result> list) {
        if (view != null) {
            view.hideProgress();
            List<String> cityName = new ArrayList<>(1);
            List<String> cityLatLon = new ArrayList<>(1);
            for (int i = 0; i < list.size(); i++) {
                cityName.add(list.get(i).getCity());
                cityLatLon.add(list.get(i).getLat() + "," + list.get(i).getLon());
            }
            view.initializeAdapter(cityName, cityLatLon);
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
