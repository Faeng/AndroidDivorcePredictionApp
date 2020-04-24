package com.example.androidpredictionapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class LoveSongPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int[] name = {R.string.song_1,R.string.song_2,R.string.song_3,R.string.song_4,R.string.song_5,R.string.song_6,
            R.string.song_7,R.string.song_8,R.string.song_9,R.string.song_10,R.string.song_11,R.string.song_12,
            R.string.song_13,R.string.song_14,R.string.song_15,R.string.song_16,R.string.song_17,R.string.song_18};
    private int[] description= {R.string.des_song_1,R.string.des_song_2,R.string.des_song_3,R.string.des_song_4,R.string.des_song_5,R.string.des_song_6,
            R.string.des_song_7,R.string.des_song_8,R.string.des_song_9,R.string.des_song_10,R.string.des_song_11,R.string.des_song_12,
            R.string.des_song_13,R.string.des_song_14,R.string.des_song_15,R.string.des_song_16,R.string.des_song_17,R.string.des_song_18};
    private String[] vdo = {"yKNxeF4KMsY", "WNIPqafd4As" ,"rywUS-ohqeE", "sWBqdWTg3Vs", "asYQiRvE1Xk","gvPMVKUI9go", "5xx9gTEQaiQ", "oieBnV_HFB0",
            "8wxOVn99FTE", "nIjVuRTm-dc", "3IUfGfOK3z0","450p7goxZqg", "S-cbOl96RFM", "LjhCEhWiKXk", "817P8W8-mGE","bnVUHWCynig",
            "lp-EO5I60KA", "0RyInjfgNc4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_song_page);
        recyclerView = findViewById(R.id.videoRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        VideoAdapter videoAdapter = new VideoAdapter(vdo,name,description);

        recyclerView.setAdapter(videoAdapter);
    }

}
