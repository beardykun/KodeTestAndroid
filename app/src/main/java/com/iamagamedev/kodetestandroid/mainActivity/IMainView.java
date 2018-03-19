package com.iamagamedev.kodetestandroid.mainActivity;

import com.iamagamedev.kodetestandroid.generalActivity.IGeneralView;

/**
 * Created by Михан on 05.03.2018.
 */

public interface IMainView extends IGeneralView{

    void hideReturnDate();

    void showReturnDate();

    void destinationExchange();

    void adultPlusPress();

    void kidPlusPress();

    void childPlusPress();

    void adultMinusPress();

    void kidMinusPress();

    void childMinusPress();

    void goToNextActivity(Class activityClass, boolean destination);

    void goToForecastActivity(Class activityClass);
}
