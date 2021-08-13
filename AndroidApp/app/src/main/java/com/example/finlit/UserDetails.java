package com.example.finlit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserDetails extends AppCompatActivity {
    private String url = "https://finanlitt.herokuapp.com/home/predict";
    String res;
    private String postBodyString;
    private MediaType mediaType;
    private RequestBody requestBody;
    private Button connect;
    private int salary, percentage;

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
        //salary = Integer.parseInt(salary_edit_text.getText().toString());

        connect = findViewById(R.id.evaluateBtn);


        // Percentage Layout
        View percentageLayout = findViewById(R.id.perc_layout);
        TextView percent_text = (TextView) percentageLayout.findViewById(R.id.card_text);
        percent_text.setText(R.string.salary_perc);
        ImageView percentage_img = percentageLayout.findViewById(R.id.card_img);
        percentage_img.setImageResource(R.drawable.percentage);
        EditText percentage_edit_text = percentageLayout.findViewById(R.id.card_edit_text);
        //percentage = Integer.parseInt(percentage_edit_text.getText().toString());


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    return true;

                case R.id.graph:
                    startActivity(new Intent(getApplicationContext(), Graph.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.settings:
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    overridePendingTransition(0, 0);
                    return true;

            }
            return false;
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    postRequest("your message here", url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //startActivity(new Intent(getApplicationContext(), Graph.class));
                //overridePendingTransition(0,0);

            }
        });

    }

    private RequestBody buildRequestBody(String msg) {
        postBodyString = msg;
        mediaType = MediaType.parse("text/plain");
        requestBody = RequestBody.create(postBodyString, mediaType);
        return requestBody;
    }

    private void postRequest(String msg, String url) throws JSONException {

        JSONObject json=new JSONObject();
        json.put("v1","idcw");
        json.put("v2",45);
        json.put("v3",8000);
        json.put("v4",5);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, String.valueOf(json));
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).post(body).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(UserDetails.this, "Error"+ e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    public void run() {
                        res= null;
                        try {
                            res = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(UserDetails.this, "Success"+ response.toString(), Toast.LENGTH_LONG).show();

                            Log.d("TESTING", "run: "+res);

                        Log.d("TESTING", "run: "+call.toString());
                        parseJson();
                    }
                });
            }
        });
    }

    private void parseJson() {

        try {
            JSONObject jsonObj = new JSONObject(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}