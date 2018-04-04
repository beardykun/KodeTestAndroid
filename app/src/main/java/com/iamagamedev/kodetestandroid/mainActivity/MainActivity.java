package com.iamagamedev.kodetestandroid.mainActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iamagamedev.kodetestandroid.Constants;
import com.iamagamedev.kodetestandroid.R;
import com.iamagamedev.kodetestandroid.generalActivity.GeneralActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends GeneralActivity implements View.OnClickListener, IMainView {

    private IMainPresenter presenter;

    private int adultNum = 1;
    private int kidNum = 0;
    private int childNum = 0;
    private int year, month, day;
    private static String from = "Откуда";
    private static String to = "Куда";
    private static String cityLatLon = "";
    private static String cityLatLon2 = "";

    private Calendar calendarFlightDate;
    private DatePickerDialog datePickerDialog;

    @BindView(R.id.imageViewExchange)
    ImageView imageViewExchange;
    @BindView(R.id.imageViewHideReturnDate)
    ImageView imageViewHideReturnDate;
    @BindView(R.id.textViewShowReturnDate)
    TextView textViewShowReturnDate;
    @BindView(R.id.imageViewPassengerAdultPlus)
    ImageView imageViewPassengerAdultPlus;
    @BindView(R.id.imageViewPassengerKidPlus)
    ImageView imageViewPassengerKidPlus;
    @BindView(R.id.imageViewPassengerChildPlus)
    ImageView imageViewPassengerChildPlus;
    @BindView(R.id.imageViewPassengerAdultMinus)
    ImageView imageViewPassengerAdultMinus;
    @BindView(R.id.imageViewPassengerKidMinus)
    ImageView imageViewPassengerKidMinus;
    @BindView(R.id.imageViewPassengerChildMinus)
    ImageView imageViewPassengerChildMinus;
    @BindView(R.id.imageViewKidIcon)
    ImageView imageViewKidIcon;
    @BindView(R.id.imageViewChildIcon)
    ImageView imageViewChildIcon;
    @BindView(R.id.textViewNumAdultPassengers)
    TextView textViewNumAdultPassengers;
    @BindView(R.id.textViewNumKidPassengers)
    TextView textViewNumKidPassengers;
    @BindView(R.id.textViewNumChildPassengers)
    TextView textViewNumChildPassengers;
    @BindView(R.id.textViewPassengerKidYears)
    TextView textViewPassengerKidYears;
    @BindView(R.id.textViewPassengerChildYears)
    TextView textViewPassengerChildYears;
    @BindView(R.id.textViewDeparturePlace)
    TextView textViewDeparturePlace;
    @BindView(R.id.textViewDestinationPlace)
    TextView textViewDestinationPlace;
    @BindView(R.id.textInputFlightDate)
    TextInputEditText textInputFlightDate;
    @BindView(R.id.textInputReturnDate)
    TextInputEditText textInputReturnDate;
    @BindView(R.id.buttonFindFlight)
    Button buttonFindFlight;
    @BindView(R.id.layoutDeparturePlace)
    LinearLayout layoutDeparturePlace;
    @BindView(R.id.layoutDestinationPlace)
    LinearLayout layoutDestinationPlace;

    @BindDrawable(R.drawable.ic_kid_18dp)
    Drawable kidBlack;
    @BindDrawable(R.drawable.ic_kid_gray_18dp)
    Drawable kidGray;
    @BindDrawable(R.drawable.ic_baby_12dp)
    Drawable childBlack;
    @BindDrawable(R.drawable.ic_baby_gray_12dp)
    Drawable childGray;
    @BindDrawable(R.drawable.ic_remove_circle_outline_blue_24dp)
    Drawable minusBlue;
    @BindDrawable(R.drawable.ic_remove_circle_outline_gray_24dp)
    Drawable minusGray;
    private static final int DIALOG_CALL = 1;
    private static final int DIALOG_RETURN_CALL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new MainPresenter();

        setCalendarDate();
    }

    private void getExtras(Bundle bundle) {
        if (bundle != null && bundle.containsKey(Constants.CITY)) {
            if (!bundle.getBoolean(Constants.FROM_TO, false)) {
                from = bundle.getString(Constants.CITY);
                cityLatLon = bundle.getString(Constants.CITY_LAT_LON);
            } else {
                to = bundle.getString(Constants.CITY);
                cityLatLon2 = bundle.getString(Constants.CITY_LAT_LON);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setListenersAndCount();
        presenter.onAttachView(this);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("d MMM EEE", Locale.ENGLISH);
        textInputFlightDate.setText(format1.format(calendar.getTime()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        getExtras(bundle);
        textViewDeparturePlace.setText(from);
        textViewDestinationPlace.setText(to);
        if (!cityLatLon.equals(""))
            findViewById(R.id.textViewDeparturePlaceAllAirports).setVisibility(View.VISIBLE);
        if (!cityLatLon2.equals(""))
            findViewById(R.id.textViewDestinationPlaceAllAirports).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        presenter.onDetachView();
        super.onStop();
    }

    private void setListenersAndCount() {
        imageViewExchange.setOnClickListener(this);
        imageViewHideReturnDate.setOnClickListener(this);
        textViewShowReturnDate.setOnClickListener(this);
        imageViewPassengerAdultPlus.setOnClickListener(this);
        imageViewPassengerKidPlus.setOnClickListener(this);
        imageViewPassengerChildPlus.setOnClickListener(this);
        imageViewPassengerAdultMinus.setOnClickListener(this);
        imageViewPassengerKidMinus.setOnClickListener(this);
        imageViewPassengerChildMinus.setOnClickListener(this);
        layoutDeparturePlace.setOnClickListener(this);
        layoutDestinationPlace.setOnClickListener(this);
        buttonFindFlight.setOnClickListener(this);
        textInputFlightDate.setOnClickListener(this);
        textInputReturnDate.setOnClickListener(this);

        textViewNumAdultPassengers.setText(String.valueOf(adultNum));
        textViewNumKidPassengers.setText(String.valueOf(kidNum));
        textViewNumChildPassengers.setText(String.valueOf(childNum));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewHideReturnDate:
                presenter.hideReturnDate();
                break;

            case R.id.textViewShowReturnDate:
                presenter.showReturnDate();
                break;

            case R.id.imageViewPassengerAdultPlus:
                presenter.adultPlus();
                break;

            case R.id.imageViewPassengerKidPlus:
                presenter.kidPlus();
                break;

            case R.id.imageViewPassengerChildPlus:
                presenter.childPlus();
                break;

            case R.id.imageViewPassengerAdultMinus:
                presenter.adultMinus();
                break;

            case R.id.imageViewPassengerKidMinus:
                presenter.kidMinus();
                break;

            case R.id.imageViewPassengerChildMinus:
                presenter.childMinus();
                break;

            case R.id.imageViewExchange:
                if (!cityLatLon.equals("") && !cityLatLon2.equals(""))
                presenter.exchangeDestination(textViewDeparturePlace, textViewDestinationPlace,
                        from, to, cityLatLon, cityLatLon2);
                break;

            case R.id.layoutDeparturePlace:
                presenter.changeBaseUrl(Constants.MEETUP_URL);
                presenter.onCityClick(false);
                break;

            case R.id.layoutDestinationPlace:
                presenter.changeBaseUrl(Constants.MEETUP_URL);
                presenter.onCityClick(true);
                break;

            case R.id.buttonFindFlight:
                presenter.changeBaseUrl(Constants.FORECAST_URL);
                presenter.forecastWeather(from, to, cityLatLon, cityLatLon2);
                break;

            case R.id.textInputFlightDate:
                showDialog(DIALOG_CALL);
                break;

            case R.id.textInputReturnDate:
                createReturnDialog();
                break;
        }
        textViewNumAdultPassengers.setText(String.valueOf(adultNum));
        textViewNumKidPassengers.setText(String.valueOf(kidNum));
        textViewNumChildPassengers.setText(String.valueOf(childNum));
    }

    @Override
    public void hideReturnDate() {
        findViewById(R.id.textLayoutReturnDate).setVisibility(View.GONE);
        findViewById(R.id.imageViewHideReturnDate).setVisibility(View.GONE);
        findViewById(R.id.textViewShowReturnDate).setVisibility(View.VISIBLE);
    }

    @Override
    public void showReturnDate() {
        if (datePickerDialog == null)
            showDialog(DIALOG_RETURN_CALL);
        else
            createReturnDialog();
    }

    @Override
    public void adultPlusPress() {
        if (maxPassengersNum()) {
            adultNum++;
            if (adultNum == 2)
                imageViewPassengerAdultMinus.setImageDrawable(minusBlue);
        }
    }

    @Override
    public void kidPlusPress() {
        if (maxPassengersNum()) {
            kidNum++;
            if (kidNum == 1) {
                imageViewKidIcon.setImageDrawable(kidBlack);
                textViewNumKidPassengers.setTextColor(getResources().getColor(R.color.black));
                textViewPassengerKidYears.setTextColor(getResources().getColor(R.color.black));
                imageViewPassengerKidMinus.setImageDrawable(minusBlue);
            }
        }
    }

    @Override
    public void childPlusPress() {
        if (maxPassengersNum()) {
            childNum++;
            if (childNum > adultNum) {
                childNum = adultNum;
                Toast.makeText(this, R.string.child_limit, Toast.LENGTH_SHORT).show();
            }
            if (childNum == 1) {
                imageViewChildIcon.setImageDrawable(childBlack);
                textViewNumChildPassengers.setTextColor(getResources().getColor(R.color.black));
                textViewPassengerChildYears.setTextColor(getResources().getColor(R.color.black));
                imageViewPassengerChildMinus.setImageDrawable(minusBlue);
            }
        }
    }

    @Override
    public void adultMinusPress() {
        if (adultNum > 1) {
            adultNum--;
            if (childNum > adultNum) {
                childNum = adultNum;
            }
        }

        if (adultNum == 1)
            imageViewPassengerAdultMinus.setImageDrawable(minusGray);
    }

    @Override
    public void kidMinusPress() {
        if (kidNum > 0)
            kidNum--;

        if (kidNum == 0) {
            imageViewKidIcon.setImageDrawable(kidGray);
            textViewNumKidPassengers.setTextColor(getResources().getColor(R.color.gray_not_selected));
            textViewPassengerKidYears.setTextColor(getResources().getColor(R.color.gray_not_selected));
            imageViewPassengerKidMinus.setImageDrawable(minusGray);
        }
    }

    @Override
    public void childMinusPress() {
        if (childNum > 0)
            childNum--;

        if (childNum == 0) {
            imageViewChildIcon.setImageDrawable(childGray);
            textViewNumChildPassengers.setTextColor(getResources().getColor(R.color.gray_not_selected));
            textViewPassengerChildYears.setTextColor(getResources().getColor(R.color.gray_not_selected));
            imageViewPassengerChildMinus.setImageDrawable(minusGray);
        }
    }

    @Override
    public void changeValues(String dep, String dest, String oldCityLatLon, String oldCityLatLon2) {
        from = dest;
        to = dep;
        cityLatLon = oldCityLatLon2;
        cityLatLon2 = oldCityLatLon;
    }

    private void setCalendarDate() {
        calendarFlightDate = Calendar.getInstance();
        year = calendarFlightDate.get(Calendar.YEAR);
        month = calendarFlightDate.get(Calendar.MONTH);
        day = calendarFlightDate.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_CALL:
                DatePickerDialog dialog = new DatePickerDialog(this, myDateListener, year, month, day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                return dialog;

            case DIALOG_RETURN_CALL:
                datePickerDialog = new DatePickerDialog(this, myReturnDateListener, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(calendarFlightDate.getTimeInMillis());

                return datePickerDialog;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            showDate(year, month, day);
        }
    };

    private DatePickerDialog.OnDateSetListener myReturnDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            showReturnDate(year, month, day);
        }
    };

    private void showDate(int year, int month, int day) {
        textInputFlightDate.setText(formatDate(calendarFlightDate, year, month, day));
        hideReturnDate();
    }

    private void showReturnDate(int year, int month, int day) {
        if (textInputReturnDate.getVisibility() == View.VISIBLE) {
            findViewById(R.id.textLayoutReturnDate).setVisibility(View.VISIBLE);
            findViewById(R.id.imageViewHideReturnDate).setVisibility(View.VISIBLE);
            findViewById(R.id.textViewShowReturnDate).setVisibility(View.GONE);
        }
        Calendar calendar = Calendar.getInstance();
        textInputReturnDate.setText(formatDate(calendar, year, month, day));
    }

    private String formatDate(Calendar calendar, int year, int month, int day) {

        calendar.set(year, month, day);
        SimpleDateFormat format1 = new SimpleDateFormat("d MMM EEE", Locale.ENGLISH);

        return format1.format(calendar.getTime());
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

    private boolean maxPassengersNum() {
        if ((adultNum + kidNum + childNum) < 9) {
            return true;
        } else {
            Toast.makeText(this, R.string.limit, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void createReturnDialog() {
        //костыль :(
        datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(
                this,
                myReturnDateListener,
                calendarFlightDate.get(Calendar.YEAR),
                calendarFlightDate.get(Calendar.MONTH),
                calendarFlightDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(calendarFlightDate.getTimeInMillis());
        datePickerDialog.show();
    }
}
