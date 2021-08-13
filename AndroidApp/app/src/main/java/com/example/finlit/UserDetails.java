package com.example.finlit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

       //Salary layout
        View salaryLayout = findViewById(R.id.salary_layout);
        TextView salary_text = (TextView) salaryLayout.findViewById(R.id.card_text);
        salary_text.setText(R.string.salary);
        ImageView salary_img = salaryLayout.findViewById(R.id.card_img);
        salary_img.setImageResource(R.drawable.salary);
        EditText salary_edit_text = salaryLayout.findViewById(R.id.card_edit_text);
        String salary = salary_edit_text.getText().toString();

        // Percentage Layout
        View percentageLayout = findViewById(R.id.perc_layout);
        TextView percent_text = (TextView) percentageLayout.findViewById(R.id.card_text);
        percent_text.setText(R.string.salary_perc);
        ImageView percentage_img = percentageLayout.findViewById(R.id.card_img);
        percentage_img.setImageResource(R.drawable.percentage);
        EditText percentage_edit_text = percentageLayout.findViewById(R.id.card_edit_text);
        String percentage = percentage_edit_text.getText().toString();



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId())
            {
                case R.id.home: return true;

                case R.id.graph:
                    startActivity(new Intent(getApplicationContext(), Graph.class));
                    overridePendingTransition(0,0);
                    return true;

                case R.id.settings:
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        });
    }
}