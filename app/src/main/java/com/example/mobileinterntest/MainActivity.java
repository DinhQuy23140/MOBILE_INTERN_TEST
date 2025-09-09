package com.example.mobileinterntest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mobileinterntest.adapter.LocationAdapter;
import com.example.mobileinterntest.databinding.ActivityMainBinding;
import com.example.mobileinterntest.interfaces.OnclickItem;
import com.example.mobileinterntest.viewmodel.MainViewModel;
import com.example.mobileinterntest.viewmodelFactory.MainViewmodelFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel viewModel;
    LocationAdapter adapter;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable;
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
                        adapter.updateKeyword(keyword);
                        viewModel.getLocation("pk.2218ee0e8b922a61a6d742d19c4c6e42", keyword, "json");
                    };
                    handler.postDelayed(runnable, 500);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        viewModel.getLocationList().observe(this, locations -> {
            adapter.updateData(locations);
            Log.d("Main", String.valueOf(adapter.getItemCount()));
        });
    }

    void init() {
        viewModel = new ViewModelProvider(this, new MainViewmodelFactory(this)).get(MainViewModel.class);
        adapter = new LocationAdapter(this, new ArrayList<>(), new OnclickItem() {
            @Override
            public void onClickItem(int position) {

            }
        });
        binding.rvLocation.setAdapter(adapter);
    }
}