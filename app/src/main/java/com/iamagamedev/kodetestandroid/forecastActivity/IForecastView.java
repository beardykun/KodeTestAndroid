package com.iamagamedev.kodetestandroid.forecastActivity;

import com.iamagamedev.kodetestandroid.generalActivity.IGeneralView;
import com.iamagamedev.kodetestandroid.repository.model.forecastObject.ForecastObject;

/**
 * Created by Михан on 11.03.2018.
 */

public interface IForecastView extends IGeneralView{

    void initAdapter(ForecastObject object, ForecastObject object2);
}
