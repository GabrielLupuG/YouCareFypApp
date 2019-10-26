package com.example.youcarefypapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity { EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth FirebaseAuth;
    EditText send_text;

    private FirebaseAuth.AuthStateListener AuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.registerBtn);
        tvSignUp = findViewById(R.id.loginBtn);
        send_text = (EditText) findViewById(R.id.email);

        AuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser FirebaseUser = FirebaseAuth.getCurrentUser();
                if( FirebaseUser != null ){
                    Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Please Log in",Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();

                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    FirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intToHome = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intToHome);



                                // get the value which input by user in EditText
                                // and convert it to string
                                String str = send_text.getText().toString();
                                // Create the Intent object of this class Context() to Second_activity class
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                // now by putExtra method put the value in key, value pair
                                // key is message_key by this key we will receive the value, and put the string
                                intent.putExtra("message_key", str);
                                // start the Intent
                                startActivity(intent);



                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.addAuthStateListener(AuthStateListener);
    }
}
