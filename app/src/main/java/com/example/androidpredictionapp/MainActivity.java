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
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView listView;
    private MainActivityAdapter adapter;
    int[] mTitle ={R.string.home_predict, R.string.home_lovesong,R.string.romanticMovie,R.string.home_tenthingcheating,R.string.home_eightstep,R.string.flower};
    int[] sTitle ={R.string.home_sub_predict,R.string.home_sub_lovesong,R.string.romanticMovieSub, R.string.home_sub_tenthingcheating,R.string.home_sub_eightstep,R.string.flowerSub};
    int[] image = {R.drawable.predict,  R.drawable.song,R.drawable.romantic,R.drawable.cheatingicon,R.drawable.savedivorceicon,R.drawable.flowers};
    private RecyclerView.LayoutManager mListView;

    //For Test
    //private Button logout, edit, change;
    private FirebaseAuth auth;
    private TextView name;
    private CircleImageView profile;
    private ImageView img;

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
        Uri photoUri ;
        profile = headerView.findViewById(R.id.profile_image_nav);
        name = headerView.findViewById(R.id.profile_name_nav);
        if(!TextUtils.isEmpty(user.getDisplayName())){
            fName = (user.getDisplayName()).replace("##"," ");
            photoUri = Uri.parse((user.getPhotoUrl()).toString());
            name.setText(fName);
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference stRef = storage.getReference().child("images/"+ user.getUid());
            stRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
            {
                @Override
                public void onSuccess(Uri downloadUrl)
                {
                    String uri = downloadUrl.toString();
                    if(!TextUtils.isEmpty(uri)) {
                        Glide.with(MainActivity.this)
                                .load(uri)
                                .into(profile);
                    }
                }
            });
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
            Toast.makeText(this,"Logout Successfully",Toast.LENGTH_LONG).show();
            auth.signOut();
            startActivity(new Intent(MainActivity.this, WelcomePage.class));
            finish();
        }
        else if(item.getItemId() == R.id.edit_profile){
            Toast.makeText(this,"Edit Profile",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this,UpdateUserPage.class));
        }
        else if(item.getItemId() == R.id.change_password){
            Toast.makeText(this,"Change Password",Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, UpdatePasswordPage.class));
        }
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

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}