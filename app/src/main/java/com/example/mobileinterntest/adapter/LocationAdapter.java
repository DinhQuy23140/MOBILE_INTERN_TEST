package com.example.mobileinterntest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileinterntest.R;
import com.example.mobileinterntest.interfaces.OnclickItem;
import com.example.mobileinterntest.model.Location;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    Context context;
    List<Location> locations;
    OnclickItem onclickItem;

    public LocationAdapter(Context context, List<Location> locations, OnclickItem onclickItem) {
        this.context = context;
        this.locations = locations;
        this.onclickItem = onclickItem;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Location> locations){
        this.locations.clear();
        this.locations = locations;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
