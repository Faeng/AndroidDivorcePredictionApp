package com.example.androidpredictionapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class LoveSongPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] name = {"Love Song 1","Love Song 2"};
    private String[] description= {"Description 1", "Description 2"};
    private String[] vdo = {"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\"0\" allowfullscreen></iframe>",
            "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KyJ71G2UxTQ\" frameborder=\"0\" allowfullscreen></iframe>"};
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
