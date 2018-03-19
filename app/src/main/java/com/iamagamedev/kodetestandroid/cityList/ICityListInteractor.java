package com.iamagamedev.kodetestandroid.cityList;

import com.iamagamedev.kodetestandroid.generalActivity.IGenetalInteractorListener;
import com.iamagamedev.kodetestandroid.repository.model.meetUpObject.Result;

import java.util.List;

/**
 * Created by Михан on 08.03.2018.
 */

public interface ICityListInteractor {

    interface onCityListListener extends IGenetalInteractorListener {

        void onSuccess(List<Result> list);
    }

    void onCityRequest(onCityListListener listener);
}
