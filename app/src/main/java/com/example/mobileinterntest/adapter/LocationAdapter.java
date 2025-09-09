package com.example.mobileinterntest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileinterntest.R;
import com.example.mobileinterntest.interfaces.OnclickItem;
import com.example.mobileinterntest.model.Location;

import java.util.List;
import java.util.Locale;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    Context context;
    String keyword = "";
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
        Location location = locations.get(position);
        SpannableString spannableString = new SpannableString(location.getDisplayName());
        String lowerText = location.getDisplayName().toLowerCase(Locale.ROOT);
        int start = lowerText.indexOf(keyword.toLowerCase());
        while (start >= 0) {
            int end = start + keyword.length();
            spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = lowerText.indexOf(keyword.toLowerCase(), end);
        }
        holder.tvLocationName.setText(spannableString);
        holder.tvLocationName.setText(spannableString);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateKeyword(String keyword){
        this.keyword = keyword;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Location> newList) {
        this.locations.clear();
        this.locations.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView tvLocationName;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLocationName = itemView.findViewById(R.id.tvLocationName);
        }
    }
}
