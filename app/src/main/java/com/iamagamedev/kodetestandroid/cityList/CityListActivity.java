package com.iamagamedev.kodetestandroid.cityList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;

import com.iamagamedev.kodetestandroid.Constants;
import com.iamagamedev.kodetestandroid.R;
import com.iamagamedev.kodetestandroid.generalActivity.GeneralActivity;
import com.iamagamedev.kodetestandroid.mainActivity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityListActivity extends GeneralActivity implements SearchView.OnQueryTextListener, ICityListView, CityListAdapter.onCityListItemClickListener {

    @BindView(R.id.recyclerCityView)
    RecyclerView recyclerView;
    @BindView(R.id.search)
    SearchView searchView;
    @BindView(R.id.imageViewSearch)
    ImageView imageViewSearch;
    private CityListAdapter adapter;
    private ICityListPresenter mPresenter;
    private boolean fromTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mPresenter = new CityListPresenter();
        setSearchViewSettings();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(Constants.FROM_TO)) {
            fromTo = bundle.getBoolean(Constants.FROM_TO, false);
        }
        mPresenter.onAttachView(this);
        mPresenter.onShowList();
    }

    @Override
    protected void onStop() {
        mPresenter.onDetachView();
        adapter.setListener(null);
        super.onStop();
    }

    private void setSearchViewSettings() {
        searchView.setActivated(true);
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.findFocus();
        searchView.setOnQueryTextListener(this);
        if (fromTo) {
            imageViewSearch.setImageDrawable(getResources().getDrawable(R.drawable.ic_dot_to));
            searchView.setQueryHint(getString(R.string.there));
        } else {
            imageViewSearch.setImageDrawable(getResources().getDrawable(R.drawable.ic_dot_from));
            searchView.setQueryHint(getString(R.string.back));
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public void initializeAdapter(List<String> list, List<String> cityLatLon) {
        adapter = new CityListAdapter(list, cityLatLon);
        adapter.setListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerCityView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCityListItemClick(String city, String cityLatLon) {
        Intent intent = new Intent(this, MainActivity.class);
        mPresenter.onCityClick(intent, city, cityLatLon, fromTo);
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
        showError(error, code);
    }

    @Override
    public void showError(int error, int... code) {
        showError(error, code);
    }
}
