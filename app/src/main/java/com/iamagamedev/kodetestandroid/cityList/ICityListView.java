package com.iamagamedev.kodetestandroid.cityList;

import com.iamagamedev.kodetestandroid.generalActivity.IGeneralView;

import java.util.List;

/**
 * Created by Михан on 08.03.2018.
 */

public interface ICityListView extends IGeneralView{

    void initializeAdapter(List<String>list, List<String> cityLatLon);
}
