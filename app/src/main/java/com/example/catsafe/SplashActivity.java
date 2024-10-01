package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.catsafe2.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SplashActivity", "Splash screen started");
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("SplashActivity", "Navigating to MainActivity");
                Intent intent = new Intent(SplashActivity.this, ConectarActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        },SPLASH_DURATION);
    }
}