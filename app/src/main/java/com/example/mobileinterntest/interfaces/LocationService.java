package com.example.mobileinterntest.interfaces;

import com.example.mobileinterntest.model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationService {
    @GET("search.php")
    Call<List<Location>> getLocation(@Query("key") String key, @Query("q") String q, @Query("format") String format);
}
