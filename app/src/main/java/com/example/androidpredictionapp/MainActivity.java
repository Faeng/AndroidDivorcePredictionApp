package com.example.androidpredictionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

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
                    startActivity(new Intent(MainActivity.this, PredictionHomePage.class));
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.i("this is me","Logout");
        System.out.println("Logout ka");
        Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        //drawerLayout.closeDrawers();
        return true;
    }
}
