package com.example.androidpredictionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MoviePage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int[] name = {R.string.movie_n1,R.string.movie_n2,R.string.movie_n3,R.string.movie_n4,R.string.movie_n5,
            R.string.movie_n6,R.string.movie_n7,R.string.movie_n8,R.string.movie_n9,R.string.movie_n10};
    private int[] description= {R.string.movie_1,R.string.movie_2,R.string.movie_3,R.string.movie_4,R.string.movie_5,
            R.string.movie_6,R.string.movie_7,R.string.movie_8,R.string.movie_9,R.string.movie_10};
    private String[] vdo = {"ezcvpLIyifU", "25v7N34d5HE", "Bn_-YoG69Sw", "nSbzyEJ8X9E", "CQXSforT_qQ",
            "caj2OoFUXsQ", "jcD0Daqc3Yw", "Lj5_FhLaaQQ","yDJIcYE32NU", "1f0nlxUr_iM"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);
        recyclerView = findViewById(R.id.videoRecyclerViewMovie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        VideoAdapter videoAdapter = new VideoAdapter(vdo,name,description);
        recyclerView.setAdapter(videoAdapter);
    }
}
