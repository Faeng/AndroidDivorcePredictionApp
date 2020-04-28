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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpPage extends AppCompatActivity {


    private FirebaseAuth auth;
    private EditText firstNameEt, lastNameEt, emailEt, passwordEt;
    private Button signup;
    private LinearLayout layout;
    private ProgressBar progressBar;
    private FirebaseDatabase database;
    private DatabaseReference  myRef;
    private FirebaseStorage storage;
    private StorageReference stRef;
    private CircleImageView profileImage;
    private Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        storage = FirebaseStorage.getInstance();
        stRef = storage.getReference();

        firstNameEt = findViewById(R.id.firstName_edt_text);
        lastNameEt = findViewById(R.id.lastName_edt_text);
        emailEt = findViewById(R.id.email_edt_text);
        passwordEt = findViewById(R.id.pass_edt_text);
        layout = findViewById(R.id.layout);
        signup = findViewById(R.id.signup_btn);
        progressBar = findViewById(R.id.progressBar);

        profileImage = findViewById(R.id.profile_image);
        profileImage.setImageResource(R.drawable.iconprofile);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SignUpPage.this, Manifest.permission.READ_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SignUpPage.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},200);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailEt.getText().toString();
                final String password = passwordEt.getText().toString();
                final String firstName = firstNameEt.getText().toString();
                final String lastName = lastNameEt.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||TextUtils.isEmpty(imageUri+"")){
                    if(TextUtils.isEmpty(imageUri+"")){
                        Toast.makeText(SignUpPage.this, "Please select image profile", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(SignUpPage.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                    }

                }else{
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        emailEt.setText("");
                                        passwordEt.setText("");
                                        firstNameEt.setText("");
                                        lastNameEt.setText("");
                                        imageUri = null;
                                        profileImage.setImageResource(R.drawable.iconprofile);
                                        profileImage.setBorderColor(Color.parseColor("#000000"));
                                        Toast.makeText(SignUpPage.this, "Authentication failed." + task.getException(),Toast.LENGTH_SHORT).show();
                                    } else {
                                        layout.setVisibility(View.GONE);
                                        progressBar.setVisibility(View.VISIBLE);

                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(firstName+"##"+lastName)
                                                .setPhotoUri(imageUri)
                                                .build();

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                        user.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(SignUpPage.this,"Complete!! Please Login.",Toast.LENGTH_LONG).show();
                                                            auth.signOut();
                                                            startActivity(new Intent(SignUpPage.this, LoginPage.class));
                                                            finish();
                                                        }
                                                    }
                                                });

                                    }
                                }
                            });
                }
            }
        });




    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
    }


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
