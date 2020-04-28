package com.example.androidpredictionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailEt, passwordEt;
    private Button login;
    private LinearLayout layout;
    private ProgressBar progressBar;
    private TextView resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        auth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email_edt_text);
        passwordEt = findViewById(R.id.pass_edt_text);
        login = findViewById(R.id.login_btn);
        layout = findViewById(R.id.layout);
        progressBar = findViewById(R.id.progressBar);
        resetPassword = findViewById(R.id.reset_pass_tv);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEt.getText().toString();
                final String password = passwordEt.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginPage.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else{
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        layout.setVisibility(View.GONE);
                                        progressBar.setVisibility(View.VISIBLE);
                                        startActivity(new Intent(LoginPage.this,MainActivity.class));
                                        finish();
                                    }
                                    else{
                                        emailEt.setText("");
                                        passwordEt.setText("");
                                        Toast.makeText(LoginPage.this, "Login Failed!! Please Try again.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(LoginPage.this,ForgotPasswordPage.class));
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
