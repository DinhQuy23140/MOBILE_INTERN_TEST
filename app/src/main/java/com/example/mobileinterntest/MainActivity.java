package com.example.mobileinterntest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mobileinterntest.adapter.LocationAdapter;
import com.example.mobileinterntest.databinding.ActivityMainBinding;
import com.example.mobileinterntest.interfaces.OnclickItem;
import com.example.mobileinterntest.model.Location;
import com.example.mobileinterntest.viewmodel.MainViewModel;
import com.example.mobileinterntest.viewmodelFactory.MainViewmodelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel viewModel;
    LocationAdapter adapter;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable;
    Drawable drawableStart, drawableEnd, drawableProgress;
    List<Location> locations;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        binding.rvLocation.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        drawableStart = ContextCompat.getDrawable(this, R.drawable.ic_search);
        if (drawableStart != null) {
            drawableStart.setBounds(0, 0, drawableStart.getIntrinsicWidth(), drawableStart.getIntrinsicHeight());
        }
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        drawableProgress = progressBar.getIndeterminateDrawable();
        int size = (int) (binding.edtInputKeyword.getLineHeight() * 0.8);
        drawableProgress.setBounds(0, 0, size, size);
        if (drawableProgress instanceof android.graphics.drawable.Animatable) {
            ((android.graphics.drawable.Animatable) drawableProgress).start();
        }

        drawableEnd = ContextCompat.getDrawable(this, R.drawable.ic_cancel);
        if (drawableEnd != null) {
            drawableEnd.setBounds(0, 0, drawableEnd.getIntrinsicWidth(), drawableEnd.getIntrinsicHeight());
        }
        binding.edtInputKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString().trim();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                if (!keyword.isEmpty()) {
                    runnable = () -> {
                        binding.edtInputKeyword.setCompoundDrawablesRelative(drawableProgress, null, drawableEnd, null);
                        adapter.updateKeyword(keyword);
                        viewModel.getLocation("pk.2218ee0e8b922a61a6d742d19c4c6e42", keyword, "json");
                    };
                    handler.postDelayed(runnable, 1000);
                } else {
                    binding.edtInputKeyword.setCompoundDrawablesRelative(drawableStart, null, null, null);
                    adapter.clearData();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.edtInputKeyword.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (binding.edtInputKeyword.getCompoundDrawablesRelative()[2] != null) {
                    int drawableEndStart = binding.edtInputKeyword.getWidth() - binding.edtInputKeyword.getPaddingEnd() - drawableEnd.getIntrinsicWidth();
                    if (event.getX() >= drawableEndStart) {
                        binding.edtInputKeyword.setText("");
                        binding.edtInputKeyword.setHint("Enter keyword");
                        adapter.clearData();
                        return true;
                    }
                }
            }
            return false;
        });
        viewModel.getLocationList().observe(this, result -> {
            locations = result;
            adapter.updateData(locations);
            binding.edtInputKeyword.setCompoundDrawablesRelative(drawableStart, null, drawableEnd, null);
            Log.d("Main", String.valueOf(adapter.getItemCount()));
        });
    }

    void init() {
        viewModel = new ViewModelProvider(this, new MainViewmodelFactory(this)).get(MainViewModel.class);
        adapter = new LocationAdapter(this, new ArrayList<>(), new OnclickItem() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClickItem(int position) {
                Location location = locations.get(position);
                String uri = "https://www.google.com/maps/dir/?api=1&destination=" + location.getLat() + "," + location.getLon();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        binding.rvLocation.setAdapter(adapter);
    }
}