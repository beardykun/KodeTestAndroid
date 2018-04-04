package com.iamagamedev.kodetestandroid.forecastActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.iamagamedev.kodetestandroid.Constants;
import com.iamagamedev.kodetestandroid.R;
import com.iamagamedev.kodetestandroid.generalActivity.GeneralActivity;
import com.iamagamedev.kodetestandroid.repository.model.forecastObject.ForecastObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastActivity extends GeneralActivity implements IForecastView {

    @BindView(R.id.recyclerViewForecast)
    RecyclerView recyclerViewForecast;
    @BindView(R.id.toolbarForecast)
    Toolbar toolbar;
    @BindView(R.id.textViewForecastDepartureBar)
    TextView textViewForecastDepartureBar;
    @BindView(R.id.textViewForecastDestinationBar)
    TextView textViewForecastDestinationBar;

    private IForecastPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        ButterKnife.bind(this);
        presenter = new ForecastPresenter();

        setToolbar(toolbar);
    }

    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
            bar.setDisplayShowTitleEnabled(false);
            bar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_custom_24dp);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttachView(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(Constants.CITY_LAT_LON) && bundle.containsKey(Constants.CITY_LAT_LON2)) {
            presenter.getForecast(bundle.getString(Constants.CITY_LAT_LON), bundle.getString(Constants.CITY_LAT_LON2));
        }
        textViewForecastDepartureBar.setText(getIntent().getStringExtra(Constants.CITY));
        textViewForecastDestinationBar.setText(getIntent().getStringExtra(Constants.CITY_2));
    }

    @Override
    protected void onStop() {
        presenter.onDetachView();
        super.onStop();
    }

    @Override
    public void initAdapter(ForecastObject object, ForecastObject object2) {
        ForecastRecyclerAdapter adapter = new ForecastRecyclerAdapter(object, object2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewForecast.setLayoutManager(layoutManager);
        recyclerViewForecast.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        showProgressView();
    }

    @Override
    public void hideProgress() {
        hideProgressView();
    }

    @Override
    public void showError(String error, int... code) {
        showErrorSnack(error);
    }

    @Override
    public void showError(int error, int... code) {
        showErrorSnack(getString(error));
    }
}
