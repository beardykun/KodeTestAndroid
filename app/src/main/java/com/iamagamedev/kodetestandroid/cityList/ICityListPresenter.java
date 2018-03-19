package com.iamagamedev.kodetestandroid.cityList;

import android.content.Intent;

import com.iamagamedev.kodetestandroid.generalActivity.IGeneralPresenter;

/**
 * Created by Михан on 08.03.2018.
 */

public interface ICityListPresenter extends IGeneralPresenter{

    void onShowList();

    void onAttachView(ICityListView view);

    void onCityClick(Intent intent, String city, String latLon, boolean fromTo);
}
