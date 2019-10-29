package com.example.youcarefypapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnRegister;
    TextView btnLogin;
    FirebaseAuth FirebaseAuth;
    EditText send_text;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnRegister = findViewById(R.id.registerBtn);
        btnLogin = findViewById(R.id.loginBtn);
        send_text = (EditText) findViewById(R.id.email);

        progressBar = findViewById(R.id.progressBar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            //on click to verify if email and password are added
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);


//



                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_LONG).show();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){



 //creating the username and email
                    FirebaseAuth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                //error message
                                Toast.makeText(MainActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {






                                if(task.isSuccessful()){
                                    FirebaseAuth.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(MainActivity.this,"Registered succesful.Please check your email",
                                                                Toast.LENGTH_LONG).show();
                                                        emailId.setText("");
                                                        password.setText("");
                                                    }
                                                    else{
                                                        Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                                                Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                }
                                else{
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }







                                //code to create user profile in home activity
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));


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
                    //toast error if any error occurred
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });



        //it go to log in page by clicking on login activity button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
