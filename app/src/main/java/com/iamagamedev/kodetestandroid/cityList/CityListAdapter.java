package com.iamagamedev.kodetestandroid.cityList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.iamagamedev.kodetestandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Михан on 07.03.2018.
 */

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityListViewHolder> implements Filterable {

    private List<String> cityArray;
    private List<String> cityArray2;
    private List<String> cityLatLon;
    private CityFilter mCityFilter;

    private onCityListItemClickListener listener;

    interface onCityListItemClickListener {
        void onCityListItemClick(String city, String cityLatLon);
    }

    public void setListener(onCityListItemClickListener listener) {
        this.listener = listener;
    }

    public CityListAdapter(List<String> cityArray, List<String> cityLatLon) {
        this.cityArray = cityArray;
        this.cityArray2 = cityArray;
        this.cityLatLon = cityLatLon;
    }

    @Override
    public CityListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item, parent, false);
        return new CityListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityListViewHolder holder, int position) {
        holder.textViewCityName.setText(cityArray2.get(position));
    }

    @Override
    public int getItemCount() {
        return (cityArray2 != null) ? cityArray2.size() : 0;
    }

    @Override
    public Filter getFilter() {
        if (mCityFilter == null)
            mCityFilter = new CityFilter();

        return mCityFilter;
    }

    public class CityListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.textViewCityName)
        TextView textViewCityName;

        public CityListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onCityListItemClick(cityArray2.get(getAdapterPosition()), cityLatLon.get(getAdapterPosition()));
            }
        }
    }

    private class CityFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                List<String> filterList = new ArrayList<>();
                for (int i = 0; i < cityArray.size(); i++) {
                    if ((cityArray.get(i).toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(cityArray.get(i));
                    }
                }
                filterResults.count = filterList.size();
                filterResults.values = filterList;
            } else {
                filterResults.count = cityArray.size();
                filterResults.values = cityArray;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cityArray2 = (List<String>) results.values;
            notifyDataSetChanged();
        }
    }
}
