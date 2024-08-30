package com.ua.project.android_homework_swipefragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.ua.project.android_homework_swipefragments.databinding.ActivityMainBinding;
import com.ua.project.android_homework_swipefragments.listeners.OnSwipeListener;
import com.ua.project.android_homework_swipefragments.listeners.interfaces.SwipeCallbacks;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeCallbacks {
    private int currentFragmentIndex = 0;
    private ActivityMainBinding binding;
    final Fragment[] fragments = { new FirstScrollingFragment(), new SecondScrollingFragment(), new ThirdScrollingFragment() };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.scrollingFrameLayout.setOnTouchListener(new OnSwipeListener(MainActivity.this, MainActivity.this));
        loadFragment(fragments[currentFragmentIndex]);
    }

    public void onSwipeRight() {
        if (currentFragmentIndex > 0) {
            loadFragment(fragments[--currentFragmentIndex]);
        }
    }

    public void onSwipeLeft() {
        if (currentFragmentIndex < fragments.length - 1) {
            loadFragment(fragments[++currentFragmentIndex]);
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.scrollingFrameLayout.getId(), fragment)
                .commit();
    }
}