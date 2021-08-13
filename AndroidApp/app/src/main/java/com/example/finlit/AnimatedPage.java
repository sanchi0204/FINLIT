package com.example.finlit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class AnimatedPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_page);

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(() -> {

            Intent i = new Intent(AnimatedPage.this, Graph.class);
            startActivity(i);

            // close this activity
            finish();
        }, SPLASH_TIME_OUT);
    }
    }
