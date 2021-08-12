package com.example.finlit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(() -> {

            Intent i = new Intent(MainActivity.this, UserDetails.class);
            startActivity(i);

            finish();
        }, SPLASH_TIME_OUT);
    }
    }
