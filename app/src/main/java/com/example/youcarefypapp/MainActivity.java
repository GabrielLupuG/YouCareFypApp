package com.example.youcarefypapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText emailId, password, user, dateOfB, phone;
    Button btnRegister;
    TextView btnLogin;
    FirebaseAuth FirebaseAuth;
    EditText send_text;
    ProgressBar progressBar;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        FirebaseAuth = FirebaseAuth.getInstance();
//        emailId = findViewById(R.id.email);
//        password = findViewById(R.id.password);
//
//        username = findViewById(R.id.userName);
//        dateOfB = findViewById(R.id.dateBirth);
//        phone =findViewById(R.id.phone);
//
//
//        btnRegister = findViewById(R.id.registerBtn);
//        btnLogin = findViewById(R.id.loginBtn);
       // send_text = (EditText) findViewById(R.id.email);
       // progressBar = findViewById(R.id.progressBar);
//
//
//        //register button and check if each field is with value or not
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            //on click to verify if email and password are added
//            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//
//                String email = emailId.getText().toString();
//                String pwd = password.getText().toString();
//
//                String userN = username.getText().toString();
//                String date = dateOfB.getText().toString();
//                String phon = phone.getText().toString();
//
//
//                if(userN.isEmpty()){
//                    username.setError("Please enter username");
//                    username.requestFocus();
//                    Toast.makeText(MainActivity.this, "Please enter your username", Toast.LENGTH_LONG).show();
//                }
//               else if(date.isEmpty()){
//                    dateOfB.setError("Please enter date of birth");
//                    dateOfB.requestFocus();
//                    Toast.makeText(MainActivity.this, "Please enter your date of birth", Toast.LENGTH_LONG).show();
//                }
//               else if(phon.isEmpty()){
//                    phone.setError("Please enter phone number");
//                    phone.requestFocus();
//                    Toast.makeText(MainActivity.this, "Please enter your phone number", Toast.LENGTH_LONG).show();
//                }
//               else if(email.isEmpty()){
//                    emailId.setError("Please enter email id");
//                    emailId.requestFocus();
//                    Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
//                }
//                else  if(pwd.isEmpty()){
//                    password.setError("Please enter your password");
//                    password.requestFocus();
//                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_LONG).show();
//                }
//                else  if(userN.isEmpty() && date.isEmpty() && phon.isEmpty() && email.isEmpty() && pwd.isEmpty()){
//                    Toast.makeText(MainActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
//                }
//                else  if(!(email.isEmpty() && pwd.isEmpty())){
//
//                //creating the username and email in firebase authentification
//                    FirebaseAuth.createUserWithEmailAndPassword(email, pwd)
//                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            progressBar.setVisibility(View.GONE);
//                            if (!task.isSuccessful()) {
//                                //error message
//                                Toast.makeText(MainActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                if(task.isSuccessful()){
//                                    FirebaseAuth.getCurrentUser().sendEmailVerification()
//                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    if(task.isSuccessful()){
//                                                        Toast.makeText(MainActivity.this,"Registered succesful.Please check your email",
//                                                                Toast.LENGTH_LONG).show();
//                                                        emailId.setText("");
//                                                        password.setText("");
//                                                    }
//                                                    else{
//                                                        Toast.makeText(MainActivity.this, task.getException().getMessage(),
//                                                                Toast.LENGTH_LONG).show();
//                                                    }
//                                                }
//                                            });
//                                }
//                                else{
//                                    Toast.makeText(MainActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
//                                }
//
//                                //code to create user profile in home activity
//                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
//
//                                // get the value which input by user in EditText
//                                // and convert it to string
//                                String str = send_text.getText().toString();
//                                // Create the Intent object of this class Context() to Second_activity class
//                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                                // now by putExtra method put the value in key, value pair
//                                // key is message_key by this key we will receive the value, and put the string
//                                intent.putExtra("message_key", str);
//                                // start the Intent
//                                startActivity(intent);
//                            }
//                        }
//                    });
//                }
//                else{
//                    //toast error if any error occurred
//                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//
//
//        //it go to log in page by clicking on login activity button
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//                Intent i = new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(i);
//            }
//        });
//    }



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        user= findViewById(R.id.userName);
        emailId=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btnRegister=findViewById(R.id.registerBtn);
        dateOfB = findViewById(R.id.phone);
        phone = findViewById(R.id.phone);
        btnLogin = findViewById(R.id.loginBtn);
        send_text =  findViewById(R.id.email);
        progressBar = findViewById(R.id.progressBar);

        FirebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = user.getText().toString();
                String txtEmail = emailId.getText().toString();
                String txtPassword = password.getText().toString();

                String txtDate = dateOfB.getText().toString();
                String txtPhon = phone.getText().toString();

                progressBar.setVisibility(View.VISIBLE);


                if (TextUtils.isEmpty(txtUsername)|| TextUtils.isEmpty(txtEmail)|| TextUtils.isEmpty(txtPassword)
                        || TextUtils.isEmpty(txtDate)|| TextUtils.isEmpty(txtPhon)){
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_LONG).show();
                }
                else{
                    register(txtUsername, txtEmail,txtPassword, txtDate, txtPhon);

                    //  code to create user profile in home activity
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

        // it go to log in page by clicking on login activity button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }


    private void register(String username, String email, String password, String phone, String date){

        FirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser=FirebaseAuth.getCurrentUser();
                            String userid= firebaseUser.getUid();

                            String name = user.getText().toString();
                            //String email = emailId.getText().toString();

                            reference = FirebaseDatabase.getInstance().getReference("Doctor").child(name);



                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id:", userid);
                            hashMap.put("username:", username);
                            hashMap.put("email:", email);
                            hashMap.put("password:", password);
                            hashMap.put("date of birth:", date);
                            hashMap.put("phone number:", phone);
                           // hashMap.put("imageURL:", "default");
                          //  hashMap.put("status:", "offline");
                           // hashMap.put("search:", username.toLowerCase());


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(MainActivity.this, SelectPage.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(MainActivity.this, "You can't register with this email or password", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}
