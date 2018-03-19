package com.iamagamedev.kodetestandroid.generalActivity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iamagamedev.kodetestandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeneralActivity extends AppCompatActivity {

    @BindView(R.id.progressLay)
    RelativeLayout progress;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater layoutInflater = getLayoutInflater();

        final View container = layoutInflater.inflate(R.layout.activity_general, (ViewGroup) getWindow().getDecorView(), false);
        layoutInflater.inflate(layoutResID, (ViewGroup) container.findViewById(R.id.cont_root), true);
        super.setContentView(container);
        ButterKnife.bind(this);
        progress.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void showErrorSnack(final String error) {
        showErrorSnack(error, coordinatorLayout);
    }

    protected void showErrorSnack(final String error, CoordinatorLayout coordinatorLayout) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.app_error_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.amdp_white));
        snackbar.setActionTextColor(getResources().getColor(R.color.black));

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setMaxLines(10);
        snackbar.show();
    }

    public void showProgressView() {
        progress.setVisibility(View.VISIBLE);
    }

    public void hideProgressView() {
        progress.setVisibility(View.GONE);
    }

}
