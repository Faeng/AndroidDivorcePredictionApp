package com.example.androidpredictionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomePage extends AppCompatActivity {

    private Button login, signup;
    private ProgressBar progressBar;
    private LinearLayout layout;
    private TextView reset;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        login = findViewById(R.id.login_btn);
        signup = findViewById(R.id.signup_btn);
        progressBar = findViewById(R.id.progressBar);
        layout = findViewById(R.id.linearLayout2);
        reset = findViewById(R.id.reset_pass_tv);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            layout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(WelcomePage.this, MainActivity.class));
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(WelcomePage.this, LoginPage.class));
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                startActivity(new Intent(WelcomePage.this, SignUpPage.class));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                startActivity(new Intent(WelcomePage.this, ForgotPasswordPage.class));
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
