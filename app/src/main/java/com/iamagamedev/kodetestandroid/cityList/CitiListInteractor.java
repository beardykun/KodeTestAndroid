package com.iamagamedev.kodetestandroid.cityList;

import com.iamagamedev.kodetestandroid.repository.Repository;

/**
 * Created by Михан on 08.03.2018.
 */

public class CitiListInteractor implements ICityListInteractor{
    @Override
    public void onCityRequest(onCityListListener listener) {
        Repository.getCityList(listener);
    }
}
