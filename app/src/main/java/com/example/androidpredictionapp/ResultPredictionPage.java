package com.example.androidpredictionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultPredictionPage extends AppCompatActivity {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    float[] results = new float[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        results = extras.getFloatArray("predictResult");
        System.out.println(results.toString());
        System.out.println(results[0]);
        System.out.println(results[1]);
        setContentView(R.layout.result_prediction_layout);
        if(results[0]>results[1]){
            System.out.println("case one");
            TextView result_text = findViewById(R.id.resultTextView);
            TextView percent_value = findViewById(R.id.percentValueTextview);
            result_text.setText(R.string.predict_no_result);
            percent_value.setText(df2.format(results[0] * 100));
        }else{
            System.out.println("case two");
            TextView result_text = findViewById(R.id.resultTextView);
            TextView percent_value = findViewById(R.id.percentValueTextview);
            result_text.setText(R.string.predict_yes_result);
            percent_value.setText(df2.format(results[1] * 100));
        }

    }

    public void homeclk(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
