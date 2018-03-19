package com.iamagamedev.kodetestandroid.forecastActivity;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iamagamedev.kodetestandroid.R;
import com.iamagamedev.kodetestandroid.ThisApplication;
import com.iamagamedev.kodetestandroid.repository.model.forecastObject.ForecastObject;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Михан on 11.03.2018.
 */

public class ForecastRecyclerAdapter extends RecyclerView.Adapter<ForecastRecyclerAdapter.ForecastViewHolder> {

    private ForecastObject forecastObjectDeparture;
    private ForecastObject forecastObjectDestination;

    public ForecastRecyclerAdapter(ForecastObject forecastObjectDeparture, ForecastObject forecastObjectDestination) {
        this.forecastObjectDeparture = forecastObjectDeparture;
        this.forecastObjectDestination = forecastObjectDestination;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_item, parent, false);

        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        double temperatureC = forecastObjectDeparture.getList().get(position).getMain().getTemp() - 273.15;
        String celsiusFormat = String.format(Locale.JAPAN, "%.1f C", temperatureC);
        String pressureFormat = String.format(Locale.JAPAN, "Давление: %.0f hPa", forecastObjectDeparture.getList().get(position).getMain().getGrndLevel());
        String humidityFormat = String.format(Locale.JAPAN, "Влажность: %d%%", forecastObjectDeparture.getList().get(position).getMain().getHumidity());
        String cloudinessFormat = String.format(Locale.JAPAN, "Облачность: %d%%", forecastObjectDeparture.getList().get(position).getClouds().getAll());
        String windSpeedFormat = String.format(Locale.JAPAN, "Скорость ветра: %.1f м/с", forecastObjectDeparture.getList().get(position).getWind().getSpeed());
        String windDirectionFormat = String.format(Locale.JAPAN, "Направление ветра: %s", degToCompass(forecastObjectDeparture.getList().get(position).getWind().getDeg()));

        getIcon(holder.imageViewForecastDeparture, forecastObjectDeparture.getList().get(position).getWeather().get(0).getIcon());

        holder.textViewForecastDepartureName.setText(forecastObjectDeparture.getCity().getName());
        holder.textViewForecastDepartureTime.setText(forecastObjectDeparture.getList().get(position).getDtTxt());
        holder.textViewForecastDepartureTemperature.setText(celsiusFormat);
        holder.textViewForecastDeparturePressure.setText(pressureFormat);
        holder.textViewForecastDepartureHumidity.setText(humidityFormat);
        holder.textViewForecastDepartureWeatherDescription.setText(forecastObjectDeparture.getList().get(position).getWeather().get(0).getDescription());
        holder.textViewForecastDepartureCloudiness.setText(cloudinessFormat);
        holder.textViewForecastDepartureWindSpeed.setText(windSpeedFormat);
        holder.textViewForecastDepartureWindDirection.setText(windDirectionFormat);
        setBackgroundAndTemperatureIcon(holder.textViewForecastDepartureTemperature, holder.imageViewForecastDepartureTemp, (int)temperatureC, ThisApplication.getInstance());

        double temperatureC2 = forecastObjectDestination.getList().get(position).getMain().getTemp() - 273.15;
        String celsiusFormat2 = String.format(Locale.JAPAN, "%.1f C", temperatureC2);
        String pressureFormat2 = String.format(Locale.JAPAN, "Давление: %.0f hPa", forecastObjectDestination.getList().get(position).getMain().getGrndLevel());
        String humidityFormat2 = String.format(Locale.JAPAN, "Влажность: %d%%", forecastObjectDestination.getList().get(position).getMain().getHumidity());
        String cloudinessFormat2 = String.format(Locale.JAPAN, "Облачность: %d%%", forecastObjectDestination.getList().get(position).getClouds().getAll());
        String windSpeedFormat2 = String.format(Locale.JAPAN, "Скорость ветра: %.1f м/с", forecastObjectDestination.getList().get(position).getWind().getSpeed());
        String windDirectionFormat2 = String.format(Locale.JAPAN, "Направление ветра: %s", degToCompass(forecastObjectDestination.getList().get(position).getWind().getDeg()));

        getIcon(holder.imageViewForecastDestination, forecastObjectDestination.getList().get(position).getWeather().get(0).getIcon());

        holder.textViewForecastDestinationName.setText(forecastObjectDestination.getCity().getName());
        holder.textViewForecastDestinationTime.setText(forecastObjectDestination.getList().get(position).getDtTxt());
        holder.textViewForecastDestinationTemperature.setText(celsiusFormat2);
        holder.textViewForecastDestinationPressure.setText(pressureFormat2);
        holder.textViewForecastDestinationHumidity.setText(humidityFormat2);
        holder.textViewForecastDestinationWeatherDescription.setText(forecastObjectDestination.getList().get(position).getWeather().get(0).getDescription());
        holder.textViewForecastDestinationCloudiness.setText(cloudinessFormat2);
        holder.textViewForecastDestinationWindSpeed.setText(windSpeedFormat2);
        holder.textViewForecastDestinationWindDirection.setText(windDirectionFormat2);
        setBackgroundAndTemperatureIcon(holder.textViewForecastDestinationTemperature, holder.imageViewForecastDestinationTemp, (int)temperatureC2, ThisApplication.getInstance());
    }

    private String degToCompass(Double degree) {
        int val = (int) Math.floor((degree / 45) + 0.5);
        String[] arr = {"С", "СВ", "В", "ЮВ", "Ю", "ЮЗ", "З", "СЗ"};
        return arr[(val % 8)];
    }

    @Override
    public int getItemCount() {
        return forecastObjectDeparture.getList().size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewForecastDeparture)
        ImageView imageViewForecastDeparture;
        @BindView(R.id.textViewForecastDepartureName)
        TextView textViewForecastDepartureName;
        @BindView(R.id.textViewForecastDepartureTime)
        TextView textViewForecastDepartureTime;
        @BindView(R.id.textViewForecastDepartureTemperature)
        TextView textViewForecastDepartureTemperature;
        @BindView(R.id.textViewForecastDeparturePressure)
        TextView textViewForecastDeparturePressure;
        @BindView(R.id.textViewForecastDepartureHumidity)
        TextView textViewForecastDepartureHumidity;
        @BindView(R.id.textViewForecastDepartureWeatherDescription)
        TextView textViewForecastDepartureWeatherDescription;
        @BindView(R.id.textViewForecastDepartureCloudiness)
        TextView textViewForecastDepartureCloudiness;
        @BindView(R.id.textViewForecastDepartureWindSpeed)
        TextView textViewForecastDepartureWindSpeed;
        @BindView(R.id.textViewForecastDepartureWindDirection)
        TextView textViewForecastDepartureWindDirection;
        @BindView(R.id.imageViewForecastDepartureTemp)
        ImageView imageViewForecastDepartureTemp;

        @BindView(R.id.imageViewForecastDestination)
        ImageView imageViewForecastDestination;
        @BindView(R.id.textViewForecastDestinationName)
        TextView textViewForecastDestinationName;
        @BindView(R.id.textViewForecastDestinationTime)
        TextView textViewForecastDestinationTime;
        @BindView(R.id.textViewForecastDestinationTemperature)
        TextView textViewForecastDestinationTemperature;
        @BindView(R.id.textViewForecastDestinationPressure)
        TextView textViewForecastDestinationPressure;
        @BindView(R.id.textViewForecastDestinationHumidity)
        TextView textViewForecastDestinationHumidity;
        @BindView(R.id.textViewForecastDestinationWeatherDescription)
        TextView textViewForecastDestinationWeatherDescription;
        @BindView(R.id.textViewForecastDestinationCloudiness)
        TextView textViewForecastDestinationCloudiness;
        @BindView(R.id.textViewForecastDestinationWindSpeed)
        TextView textViewForecastDestinationWindSpeed;
        @BindView(R.id.textViewForecastDestinationWindDirection)
        TextView textViewForecastDestinationWindDirection;
        @BindView(R.id.imageViewForecastDestinationTemp)
        ImageView imageViewForecastDestinationTemp;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void getIcon(ImageView imageView, String weather) {
        Picasso.with(ThisApplication.getInstance()).load(Uri.parse("http://openweathermap.org/img/w/" + weather + ".png")).into(imageView);
    }

    private void setBackgroundAndTemperatureIcon(TextView textView, ImageView imageView, int temp, Context context){
        if (temp <= 0){
            textView.setBackgroundColor(context.getResources().getColor(R.color.cold));
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_cold));
        }else if (temp > 0 && temp <= 15){
            textView.setBackgroundColor(context.getResources().getColor(R.color.cool));
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_cool));
        }else if (temp > 15 && temp <= 30){
            textView.setBackgroundColor(context.getResources().getColor(R.color.warm));
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_warm));
        }else if (temp > 30){
            textView.setBackgroundColor(context.getResources().getColor(R.color.really_hot));
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_hot));
        }
    }
}
