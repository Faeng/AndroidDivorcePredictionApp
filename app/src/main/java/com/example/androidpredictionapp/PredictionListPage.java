package com.example.androidpredictionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PredictionListPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView listView;
    private PredictionListPageAdapter adapter;
    private RecyclerView.LayoutManager mListView;
    private List<Prediction> predictionList = new ArrayList<>();
    // this attribute is from the login page to query data from firebase
    private String email = "piyawad.n@ku.th";
    //private List<String> resultList = new ArrayList<>();
    //private List<String> dateList = new ArrayList<>();

    //firebase here
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference predictionDB = database.getReference("predictions");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read from the database
        predictionDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                predictionList.clear();
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    Prediction prediction = userSnapshot.getValue(Prediction.class);
                    if(prediction.getEmail().equals(email)){
                        predictionList.add(prediction);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });
        System.out.println("buildRecycleView1");
        System.out.println("buildRecycleView2");
        setContentView(R.layout.activity_prediction_list_page);
        buildRecycleView();
    }

    private void buildRecycleView() {
        recyclerView = findViewById(R.id.predictionResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new PredictionListPageAdapter(predictionList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
