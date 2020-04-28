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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordPage extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailEt;
    private Button reset, back;
    private ProgressBar progressBar;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_page);

        auth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email_edt_text);
        reset = findViewById(R.id.reset_pass_btn);
        back = findViewById(R.id.back_btn);
        progressBar = findViewById(R.id.progressBar);
        layout = findViewById(R.id.layoutForgot);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEt.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(ForgotPasswordPage.this, "Please enter email address", Toast.LENGTH_LONG).show();
                }else {
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(ForgotPasswordPage.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ForgotPasswordPage.this,"Reset link sent to your email",Toast.LENGTH_LONG).show();
                                        layout.setVisibility(View.GONE);
                                        progressBar.setVisibility(View.VISIBLE);
                                        startActivity(new Intent(ForgotPasswordPage.this, WelcomePage.class));
                                    }else{
                                        Toast.makeText(ForgotPasswordPage.this, "Unable to send reset mail", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(ForgotPasswordPage.this, WelcomePage.class));
            }
        });
    }

}
