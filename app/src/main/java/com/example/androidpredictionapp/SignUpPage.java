package com.example.androidpredictionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpPage extends AppCompatActivity {


    private FirebaseAuth auth;
    private EditText  emailEt, passwordEt;
    private Button signup;
    private LinearLayout layout;
    private ProgressBar progressBar;
    private FirebaseDatabase database;
    private DatabaseReference  myRef;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        emailEt = findViewById(R.id.email_edt_text);
        passwordEt = findViewById(R.id.pass_edt_text);
        layout = findViewById(R.id.layout);
        signup = findViewById(R.id.signup_btn);
        progressBar = findViewById(R.id.progressBar);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailEt.getText().toString();
                final String password = passwordEt.getText().toString();


                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpPage.this, "Please fill all the fields", Toast.LENGTH_LONG).show();

                }else{

                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        emailEt.setText("");
                                        Toast.makeText(SignUpPage.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        layout.setVisibility(View.GONE);
                                        progressBar.setVisibility(View.VISIBLE);

                                        auth.signInWithEmailAndPassword(email, password)
                                                .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if(task.isSuccessful()){
                                                        }
                                                        else{
                                                            emailEt.setText("");
                                                            passwordEt.setText("");
                                                            Toast.makeText(SignUpPage.this, "Login Failed!! Please Try again.", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });

                                        startActivity(new Intent(SignUpPage.this, ProfilePage.class));
                                        finish();
                                    }
                                }
                            }
                            );
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

}
