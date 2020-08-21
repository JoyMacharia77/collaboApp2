package com.joymacharia.collaboapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class BeginnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);

        //code to pause up for 1.5 seconds then anything run method to run
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                boolean isLoggedIn = userPref.getBoolean("isLoggedIn", false);

                if (isLoggedIn) {
                    startActivity(new Intent(BeginnerActivity.this, HomeActivity.class));
                    finish();
                } else {
                    isFirstTime();
                }
            }
        }, 3000);
    }

    private void isFirstTime() {
        //check if app is running for first time
        //save a value in shared preference
        SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirstTime", true);
        if (isFirstTime) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();
            Intent intent = new Intent(BeginnerActivity.this, onBoardActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(BeginnerActivity.this, AuthActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
