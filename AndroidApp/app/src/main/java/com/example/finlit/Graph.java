package com.example.finlit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.graph);


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId())
            {
                case R.id.graph: return true;

                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), UserDetails.class));
                    overridePendingTransition(0,0);
                    return true;

//                case R.id.settings:
//                    startActivity(new Intent(getApplicationContext(), Settings.class));
//                    overridePendingTransition(0,0);
//                    return true;

            }
            return false;
        });
    }
}