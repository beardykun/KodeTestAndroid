package com.iamagamedev.kodetestandroid.repository;

import com.iamagamedev.kodetestandroid.OnlineChecker;
import com.iamagamedev.kodetestandroid.cityList.ICityListInteractor;
import com.iamagamedev.kodetestandroid.forecastActivity.IForecastInteractor;
import com.iamagamedev.kodetestandroid.repository.model.forecastObject.ForecastObject;
import com.iamagamedev.kodetestandroid.repository.model.meetUpObject.Result;
import com.iamagamedev.kodetestandroid.repository.model.meetUpObject.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Михан on 07.03.2018.
 */

public class Repository {

    public static void getCityList(final ICityListInteractor.onCityListListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.getCityList(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {
                    if (response.body() != null) {
                        List<Result> list = response.body().getResults();

                        listener.onSuccess(list);
                    } else {
                        listener.onError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError("Нет подключения к интернету");
        }
    }

    public static void getForecast(final double cityLat, final double cityLon,  final IForecastInteractor.onForecastListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.getForecast(cityLat, cityLon, new Callback<ForecastObject>() {

                @Override
                public void onResponse(Call<ForecastObject> call, Response<ForecastObject> response) {
                    if (response.body() != null) {
                        ForecastObject forecastObject = response.body();

                        listener.onSuccess(forecastObject);
                    } else {
                        listener.onError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<ForecastObject> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError("Нет подключения к интернету");
        }
    }
}
