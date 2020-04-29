package com.example.androidpredictionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView listView;
    private MainActivityAdapter adapter;
    int[] mTitle ={R.string.home_predict, R.string.home_lovesong,R.string.romanticMovie,R.string.home_tenthingcheating,R.string.home_eightstep,R.string.flower};
    int[] sTitle ={R.string.home_sub_predict,R.string.home_sub_lovesong,R.string.romanticMovieSub, R.string.home_sub_tenthingcheating,R.string.home_sub_eightstep,R.string.flowerSub};
    int[] image = {R.drawable.predict,  R.drawable.song,R.drawable.romantic,R.drawable.cheatingicon,R.drawable.savedivorceicon,R.drawable.flowers};
    private RecyclerView.LayoutManager mListView;

    private FirebaseAuth auth;
    private TextView name;
    private CircleImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        NavigationView navigationView = findViewById(R.id.navigation);
        View headerView = navigationView.getHeaderView(0);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        buildRecycleView();
        auth = FirebaseAuth.getInstance();
        //For Test
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fName;
        profile = headerView.findViewById(R.id.profile_image_nav);
        name = headerView.findViewById(R.id.profile_name_nav);
        if(user != null){
            fName = (user.getDisplayName()).replace("##"," ");
            name.setText(fName);
            profile.setImageURI(user.getPhotoUrl());
        }


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
        if(item.getItemId()==R.id.action_logout){
            Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show();
            auth.signOut();
            startActivity(new Intent(MainActivity.this, WelcomePage.class));
            finish();
        }
        else if(item.getItemId() == R.id.edit_profile){
            Toast.makeText(this,"Edit Profile",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, UpdateUserPage.class));
        }
        else if(item.getItemId() == R.id.change_password){
            Toast.makeText(this,"Change Password",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, UpdatePasswordPage.class));
        }
        Log.i("this is me","Logout");
        System.out.println("Logout ka");

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        //drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
