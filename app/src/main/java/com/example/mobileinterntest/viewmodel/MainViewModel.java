package com.example.mobileinterntest.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileinterntest.model.Location;
import com.example.mobileinterntest.repository.LocationRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    Context context;
    LocationRepository locationRepository;
    MutableLiveData<List<Location>> locationList;

    public MainViewModel(Context context) {
        this.context = context;
        locationRepository = new LocationRepository();
    }

    public MutableLiveData<List<Location>> getLocationList() {
        return locationList;
    }

    public void getLocation(String key, String q, String format) {
        locationRepository.getLocation(key, q, format);
        locationList = locationRepository.getLocationList();
    }
}
