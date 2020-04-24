package com.example.androidpredictionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class FlowersPage extends AppCompatActivity {
    private int image[] ={R.drawable.yellowacacia, R.drawable.gillyflower, R.drawable.blueviolet, R.drawable.gardenia, R.drawable.whitelilac,
            R.drawable.forgetmenot, R.drawable.whitedittany, R.drawable.sweetalyssum, R.drawable.cloverwhite,R.drawable.azalea};
    private  int name[] ={R.string.flowername1,
            R.string.flowername2, R.string.flowername3,R.string.flowername4,R.string.flowername5,R.string.flowername6,
            R.string.flowername7,
            R.string.flowername8,
            R.string.flowername9,
            R.string.flowername10,};
    private  int meaning[] ={R.string.flowermeaning1,
            R.string.flowermeaning2,
            R.string.flowermeaning3,
            R.string.flowermeaning4,
            R.string.flowermeaning5,
            R.string.flowermeaning6,
            R.string.flowermeaning7,
            R.string.flowermeaning8,
            R.string.flowermeaning9,
            R.string.flowermeaning10,};
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowers_page);

        recyclerView = findViewById(R.id.recyclerViewFlower);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        FlowerAdapter flowerAdapter = new FlowerAdapter(image, name, meaning);
        recyclerView.setAdapter(flowerAdapter);

    }
}
