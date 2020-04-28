package com.example.androidpredictionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listView;
    private MainActivityAdapter adapter;
    int[] mTitle ={R.string.home_predict, R.string.home_lovesong,R.string.romanticMovie,R.string.home_tenthingcheating,R.string.home_eightstep,R.string.flower};
    int[] sTitle ={R.string.home_sub_predict,R.string.home_sub_lovesong,R.string.romanticMovieSub, R.string.home_sub_tenthingcheating,R.string.home_sub_eightstep,R.string.flowerSub};
    int[] image = {R.drawable.predict,  R.drawable.song,R.drawable.romantic,R.drawable.cheatingicon,R.drawable.savedivorceicon,R.drawable.flowers};
    private RecyclerView.LayoutManager mListView;

    //For Test
    private Button logout, edit, change;
    private FirebaseAuth auth;
    private TextView name;
    private CircleImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        buildRecycleView();
        auth = FirebaseAuth.getInstance();



        //For Test
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fName;
        Uri photoUri ;
        change = findViewById(R.id.button3);
        logout = findViewById(R.id.button);
        edit = findViewById(R.id.button2);
        profile = findViewById(R.id.profile_image);
        name = findViewById(R.id.name);
        if(user != null){
            fName = (user.getDisplayName()).replace("##"," ");
            photoUri = user.getPhotoUrl();
            name.setText(fName);
            profile.setImageURI(photoUri);
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UpdateUserPage.class));
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this, WelcomePage.class));
                finish();
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UpdatePasswordPage.class));
            }
        });

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
