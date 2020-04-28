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

public class UpdatePasswordPage extends AppCompatActivity {
    private EditText  newPassword;
    private ProgressBar progressBar;
    private LinearLayout layout;
    private FirebaseAuth auth;
    private Button changePassword, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password_page);
        newPassword = findViewById(R.id.password_edt_text);
        progressBar = findViewById(R.id.progressBar);
        layout = findViewById(R.id.layout);
        changePassword = findViewById(R.id.change_pass_btn);
        back = findViewById(R.id.back_btn);

        auth = FirebaseAuth.getInstance();

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getCurrentUser().updatePassword(newPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(TextUtils.isEmpty(newPassword.getText().toString())){
                            Toast.makeText(UpdatePasswordPage.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (task.isSuccessful()) {
                                Toast.makeText(UpdatePasswordPage.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                                layout.setVisibility(View.GONE);
                                progressBar.setVisibility(View.VISIBLE);
                                startActivity(new Intent(UpdatePasswordPage.this, MainActivity.class));
                                finish();
                            } else {

                                layout.setVisibility(View.GONE);
                                progressBar.setVisibility(View.VISIBLE);
                                startActivity(new Intent(UpdatePasswordPage.this, UpdatePasswordPage.class));
                                layout.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(UpdatePasswordPage.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(UpdatePasswordPage.this, MainActivity.class));
                finish();
            }
        });
    }
}
