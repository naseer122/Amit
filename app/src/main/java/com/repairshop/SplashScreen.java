package com.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Check if user is already registered or not
        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        boolean isRegistered = pref.getBoolean("isRegistered", false);

        if (!isRegistered) {
            // If user is not registered, then redirect to registration activity
            Intent intent = new Intent(SplashScreen.this, RepairShopRegistration.class);
            startActivity(intent);
            finish();
        } else {
            // If user is already registered, then redirect to main activity (dashboard)
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}