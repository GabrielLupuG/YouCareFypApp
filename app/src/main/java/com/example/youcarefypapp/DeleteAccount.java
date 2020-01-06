package com.example.youcarefypapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DeleteAccount extends AppCompatActivity {

    TextView userEm;
    Button delet, home;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userEm= findViewById(R.id.userEmail);
        delet= findViewById(R.id.deleteAcc);
        home = findViewById(R.id.homaPage);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userEm.setText(firebaseUser.getEmail());

        //click to go on home activity page
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DeleteAccount.this,HomeActivity.class);
                startActivity(i);
            }
        });


        //delete account from firebase with dialog alert button before delete account
        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // progressBar.setVisibility(View.VISIBLE);
                AlertDialog.Builder dialog = new AlertDialog.Builder(DeleteAccount.this);
                dialog.setTitle("Alert!");
                dialog.setMessage("Deleting this account wil remove your account from system");

                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DeleteAccount.this, "Account Deleted", Toast.LENGTH_LONG).show();


                                    Intent intToMain = new Intent(DeleteAccount.this, StartActivity.class);
                                    startActivity(intToMain);
                                }
                                else {
                                    Toast.makeText(DeleteAccount.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });


                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();

            }
        });


    }
}
