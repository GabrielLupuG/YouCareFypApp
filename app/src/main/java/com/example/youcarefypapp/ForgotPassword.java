package com.example.youcarefypapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    ProgressBar progressBar;
    EditText userEmail;
    Button forgotPass , regButton, logPage;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        progressBar=findViewById(R.id.progressBar2);
        userEmail=findViewById(R.id.userEmail);
        forgotPass=findViewById(R.id.frgPass);
        firebaseAuth=FirebaseAuth.getInstance();
        regButton=findViewById(R.id.registerPage);
        logPage=findViewById(R.id.toLogin);

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(ForgotPassword.this,"Password sent to your email",Toast.LENGTH_LONG).show();
                                }
                                else {
                                    progressBar.setVisibility(View.VISIBLE);
                                    Toast.makeText(ForgotPassword.this,task.getException()
                                            .getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });







        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent(ForgotPassword.this,MainActivity.class);
                startActivity(i);
            }
        });


        logPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent(ForgotPassword.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }
}
