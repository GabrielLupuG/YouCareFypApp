package com.example.youcarefypapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Request extends AppCompatActivity {

    TextView receiver_msg;
    Button btnSave;
    Button btnLogout;
    Button btnClear;
    Button delete;
    FirebaseAuth FirebaseAuth;
    EditText namePatient, dateBirthPatient, phonePatient, addressPatient;
    DatabaseReference databaseRef;
    ProgressBar progressBar;
    AddPatient addPatient;


    private FirebaseAuth.AuthStateListener AuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnLogout = findViewById(R.id.logout);
        btnClear = findViewById(R.id.clear);
        btnSave = findViewById(R.id.save);
        delete = findViewById(R.id.deleteAcc);
        namePatient = findViewById(R.id.nameP);
        dateBirthPatient = findViewById(R.id.dateBirthP);
        phonePatient = findViewById(R.id.phoneP);
        addressPatient = findViewById(R.id.addressP);
        progressBar = findViewById(R.id.progressBar);

        addPatient = new AddPatient();
        //reference to AddPatient class

        databaseRef= FirebaseDatabase.getInstance().getReference().child("ADoctor");

        receiver_msg = (TextView) findViewById(R.id.received_value_id);
        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String str = intent.getStringExtra("message_key");
        // display the string into textView
        receiver_msg.setText(str);


        //logout button which is opening home activity page
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Request.this, LoginActivity.class);
                startActivity(intToMain);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Text = namePatient.getText().toString();
                namePatient.setText(" ");
                String Text1 = dateBirthPatient.getText().toString();
                dateBirthPatient.setText(" ");
                String Text2 = phonePatient.getText().toString();
                phonePatient.setText(" ");
                String Text3 = addressPatient.getText().toString();
                addressPatient.setText(" ");

            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //progress bar testing
                progressBar.setVisibility(View.GONE);

                String name = namePatient.getText().toString();
                String dateOfB = dateBirthPatient.getText().toString();
                String phoneN = phonePatient.getText().toString();
                String address = addressPatient.getText().toString();

                addPatient.setName(name);
                addPatient.setDateBirth(dateOfB);
                addPatient.setPhone(phoneN);
                addPatient.setAddress(address);

//                databaseRef = databaseRef.child(name);
//
//                databaseRef.push().setValue(addPatient);
                databaseRef.child(name).setValue(addPatient);

                Toast.makeText(Request.this, "Data insert successfully!", Toast.LENGTH_SHORT).show();


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.deleteAcc){
            Intent myIntent = new Intent (Request.this,DeleteAccount.class);
            startActivity(myIntent);
            return false;
        }
        if(id==R.id.goHomePage){
            Intent myIntent = new Intent (Request.this,HomeActivity.class);
            startActivity(myIntent);
            return false;
        }
        if(id==R.id.logout){
            Intent myIntent = new Intent (Request.this,MainActivity.class);
            startActivity(myIntent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
