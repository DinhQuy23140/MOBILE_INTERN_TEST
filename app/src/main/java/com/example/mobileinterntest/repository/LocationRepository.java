package com.example.mobileinterntest.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mobileinterntest.client.Client;
import com.example.mobileinterntest.interfaces.LocationService;
import com.example.mobileinterntest.model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {
    LocationService locationService;
    MutableLiveData<List<Location>> locationList;

    public LocationRepository() {
        locationService = Client.getClient().create(LocationService.class);
    }

    public MutableLiveData<List<Location>> getLocationList() {
        return locationList;
    }

    public void getLocation(String key, String q, String format) {
        Call<List<Location>> call = locationService.getLocation(key, q, format);
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.isSuccessful()) {
                    locationList.setValue(response.body());
                } else {
                    Log.e("API", "Lỗi: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable throwable) {
                Log.e("API", "Lỗi: " + throwable.getMessage());
            }
        });
    }
}
