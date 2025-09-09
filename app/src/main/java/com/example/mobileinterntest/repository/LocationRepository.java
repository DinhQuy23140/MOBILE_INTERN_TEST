package com.example.mobileinterntest.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileinterntest.client.Client;
import com.example.mobileinterntest.interfaces.LocationService;
import com.example.mobileinterntest.model.Location;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {
    LocationService locationService;
    MutableLiveData<List<Location>> locationList = new MutableLiveData<>();

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
                if (response.isSuccessful() && response.body() != null) {
                    locationList.setValue(response.body());
                    Log.d("API", "Số kết quả: " + response.body().size());
                    String result = new Gson().toJson(response.body());
                    Log.d("Result", result);
                } else {
                    try {
                        if (response.errorBody() != null) {
                            String errorJson = response.errorBody().string();
                            Log.e("API", "Lỗi: " + errorJson);
                        } else {
                            Log.e("API", "Lỗi không xác định");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable throwable) {
                Log.e("API", "Lỗi mạng: " + throwable.getMessage());
            }
        });
    }

}
