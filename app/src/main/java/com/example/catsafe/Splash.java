package com.example.catsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.catsafe2.R;

public class Splash extends AppCompatActivity {

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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        },SPLASH_DURATION);
    }
}