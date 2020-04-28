package com.example.androidpredictionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PredictionHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction_home_page);
    }
    public void predictionClk(View view){
        startActivity(new Intent(PredictionHomePage.this, PredictionPage.class));
    }
    public void predictionListClk(View view){
        startActivity(new Intent(PredictionHomePage.this, PredictionListPage.class));
    }
}
