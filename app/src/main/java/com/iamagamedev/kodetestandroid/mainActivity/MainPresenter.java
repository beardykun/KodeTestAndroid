package com.iamagamedev.kodetestandroid.mainActivity;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.iamagamedev.kodetestandroid.Constants;
import com.iamagamedev.kodetestandroid.R;
import com.iamagamedev.kodetestandroid.ThisApplication;
import com.iamagamedev.kodetestandroid.cityList.CityListActivity;
import com.iamagamedev.kodetestandroid.forecastActivity.ForecastActivity;
import com.iamagamedev.kodetestandroid.repository.RetrofitForMyApp;

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
    public void exchangeDestination(TextView dep, TextView dest, String from, String to,
                                    String cityLatLon, String cityLatLon2) {
        if (view != null) {
            String newDestination = dep.getText().toString();
            dep.setText(dest.getText());
            dest.setText(newDestination);
            view.changeValues(from, to, cityLatLon, cityLatLon2);
        }
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
        if (view != null) {
            Intent intent = new Intent(ThisApplication.getInstance(), CityListActivity.class);
            if (!destination) {
                intent.putExtra(Constants.FROM_TO, false);
            } else {
                intent.putExtra(Constants.FROM_TO, true);
            }
            ThisApplication.getInstance().startActivity(intent);
        }
    }

    @Override
    public void forecastWeather(String from, String to, String cityLatLon, String cityLatLon2) {
        if (view != null) {
            if (!cityLatLon.equals("") && !cityLatLon2.equals("")) {
                Intent intent = new Intent(ThisApplication.getInstance(), ForecastActivity.class);
                intent.putExtra(Constants.CITY_LAT_LON, cityLatLon);
                intent.putExtra(Constants.CITY_LAT_LON2, cityLatLon2);
                intent.putExtra(Constants.CITY, from);
                intent.putExtra(Constants.CITY_2, to);
                ThisApplication.getInstance().startActivity(intent);
            } else {
                Toast.makeText(ThisApplication.getInstance(), R.string.choose_cities, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void changeBaseUrl(String url) {
        if (!RetrofitForMyApp.getBaseUrl().equals(url))
            RetrofitForMyApp.changeApiBaseUrl(url);
    }
}
