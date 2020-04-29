package com.example.androidpredictionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateUserPage extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText firstNameEt, lastNameEt;
    private Button update;
    private FirebaseUser user;
    private LinearLayout layout;
    private ProgressBar progressBar;
    private FirebaseDatabase database;
    private CircleImageView profileImage;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_page);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        firstNameEt = findViewById(R.id.firstName_edt_text);
        lastNameEt = findViewById(R.id.lastName_edt_text);
        layout = findViewById(R.id.layout);
        update = findViewById(R.id.update_btn);
        progressBar = findViewById(R.id.progressBar);
        profileImage = findViewById(R.id.profile_image);

        //set Data
        String[] name = (user.getDisplayName()).split("##");
        firstNameEt.setText(name[0]);
        lastNameEt.setText(name[1]);
        profileImage.setImageURI(user.getPhotoUrl());
        imageUri = user.getPhotoUrl();

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UpdateUserPage.this, Manifest.permission.READ_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(UpdateUserPage.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},200);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEt.getText().toString();
                String lastName = lastNameEt.getText().toString();
                if(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(imageUri.toString())){
                    if(TextUtils.isEmpty(imageUri+"")){
                        Toast.makeText(UpdateUserPage.this, "Please select image profile", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(UpdateUserPage.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(firstName + "##" + lastName)
                            .setPhotoUri(imageUri)
                            .build();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        layout.setVisibility(View.GONE);
                                        progressBar.setVisibility(View.VISIBLE);
                                        startActivity(new Intent(UpdateUserPage.this, MainActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        startActivity(new Intent(UpdateUserPage.this,MainActivity.class));
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != 200) {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
            profileImage.setBorderColor(Color.parseColor("#4fa575"));
        }
    }
}
