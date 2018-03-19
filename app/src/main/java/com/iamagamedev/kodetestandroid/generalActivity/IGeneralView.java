package com.iamagamedev.kodetestandroid.generalActivity;

/**
 * Created by Михан on 07.03.2018.
 */

public interface IGeneralView {

    void showProgress();

    void hideProgress();

    void showError(String error, int... code);

    void showError(int error, int... code);

}
