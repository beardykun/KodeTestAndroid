package com.iamagamedev.kodetestandroid.generalActivity;

/**
 * Created by Михан on 07.03.2018.
 */

public interface IGenetalInteractorListener {

    void onError(String error, int... code);

    void onError(int error, int... code);
}
