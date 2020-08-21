package com.joymacharia.collaboapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.joymacharia.collaboapp.Fragments.SigninFragment;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameAuthContainer, new SigninFragment()).commit();
    }

}