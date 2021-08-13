package com.example.finlit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Graph extends AppCompatActivity {
    PieChart pieChart;
    BarChart barChart;
    ArrayList<PieEntry> pientries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData;
    int [] colorClassArray= new int[]{Color.RED,Color.GREEN,Color.BLUE};

    double lcp,scp,debt,ret,inv,lcpret,scpret,debtret;
    private TextView invTV,retTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Bundle bundle=getIntent().getExtras();
        lcp=bundle.getDouble("lcp");
        scp=bundle.getDouble("scp");
        debt=bundle.getDouble("debt");
        ret=bundle.getDouble("ret");
        inv=bundle.getDouble("inv");

        lcpret=(ret*lcp)/100;
        scpret=(ret*scp)/100;
        debtret=(ret*debt)/100;


        invTV=findViewById(R.id.invid);
        retTV=findViewById(R.id.returnsTV);
        invTV.setText("Total Investment: "+String.valueOf(inv));
        retTV.setText("Total return: "+String.valueOf(ret));


        pieChart = (PieChart) findViewById(R.id.piechart);
        barChart= (BarChart) findViewById(R.id.barchart);
        pientries = new ArrayList<>();
        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        pieDataSet = new PieDataSet(pientries,"");
        pieDataSet.setColors(colorClassArray);
        pieData = new PieData(pieDataSet);
        pieChart.setNoDataText("");
        pieChart.setCenterText("Distribution of Money");
        pieChart.setDrawEntryLabels(false);
        pieChart.setUsePercentValues(true);
        Description d= new Description();
        d.setText("");
        pieChart.setDescription(d);
        //pieChart.setCenterText("Distribution of funds");
        pieChart.invalidate();

        pieChart.setData(pieData);
        pieChart.animateY(1000);


        BarDataSet barDataSet1=new BarDataSet(barDataValues1(),"Return");
        BarDataSet barDataSet2= new BarDataSet(barDataValues2(),"Return");
        barDataSet1.setColor(Color.RED);
        BarData barData=new BarData();
        barData.addDataSet(barDataSet1);
        barChart.setDescription(d);
       // barData.addDataSet(barDataSet2);
        barChart.setData(barData);
        barChart.invalidate();


       BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
       bottomNavigationView.setSelectedItemId(R.id.graph);

       bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
           //Log.d("DEBUG", "onCreate: "+item.getItemId());
           switch (item.getItemId())
           {
               case R.id.graph:
                   return true;

               case R.id.home:
                   startActivity(new Intent(getApplicationContext(), UserDetails.class));
                   overridePendingTransition(0,0);
                   return true;

               // case R.id.settings:
               //     startActivity(new Intent(getApplicationContext(), Settings.class));
               //     overridePendingTransition(0,0);
               //     return true;

           }
           return false;
       });
    }
    public void AddValuesToPIEENTRY() {
        pientries.add(new PieEntry((float) lcp, "Large Cap"));
        pientries.add(new PieEntry((float) scp, "small Cap"));
        pientries.add(new PieEntry((float) debt, "Debt"));
    }

    private ArrayList<BarEntry>barDataValues1(){
        ArrayList<BarEntry>dataVals=new ArrayList<BarEntry>();
        dataVals.add(new BarEntry(0, (float) lcpret));
        dataVals.add(new BarEntry(1, (float) scpret));
        dataVals.add(new BarEntry(2, (float) debtret));
        return dataVals;
    }

    private ArrayList<BarEntry>barDataValues2(){
        ArrayList<BarEntry>dataVals=new ArrayList<BarEntry>();
        dataVals.add(new BarEntry(0,4));
        dataVals.add(new BarEntry(1,5));
        dataVals.add(new BarEntry(2,6));
        return dataVals;
    }

}
