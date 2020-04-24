package com.example.androidpredictionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listView;
    private MainActivityAdapter adapter;
    int[] mTitle ={R.string.home_predict, R.string.home_lovesong,R.string.romanticMovie,R.string.home_tenthingcheating,R.string.home_eightstep,R.string.flower};
    int[] sTitle ={R.string.home_sub_predict,R.string.home_sub_lovesong,R.string.romanticMovieSub, R.string.home_sub_tenthingcheating,R.string.home_sub_eightstep,R.string.flowerSub};
    int[] image = {R.drawable.predict,  R.drawable.song,R.drawable.romantic,R.drawable.cheatingicon,R.drawable.savedivorceicon,R.drawable.flowers};
    private RecyclerView.LayoutManager mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        buildRecycleView();

    }

    public void buildRecycleView(){
        listView = findViewById(R.id.listView);
        listView.setHasFixedSize(true);
        mListView = new LinearLayoutManager(this);
        adapter = new MainActivityAdapter(image,mTitle,sTitle);

        listView.setLayoutManager(mListView);
        listView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this, PredictionPage.class));
                }
                if (position == 1){
                   //Love Song Page
                    startActivity(new Intent(MainActivity.this, LoveSongPage.class));
                }
                if(position == 2){
                    startActivity((new Intent(MainActivity.this, MoviePage.class)));
                }
                if(position == 3){
                    startActivity(new Intent(MainActivity.this,TenThingPageActivity.class));
                }
                if(position == 4){
                    startActivity(new Intent(MainActivity.this,EightStepPageActivity.class));
                }
                if (position == 5){
                    startActivity(new Intent(MainActivity.this, FlowersPage.class));
                }

            }
        });
    }
}
