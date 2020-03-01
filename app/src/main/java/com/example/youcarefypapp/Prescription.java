package com.example.youcarefypapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Prescription extends AppCompatActivity {


    Button btnClear;
    Button btnSave;
    EditText namePatient, dateBirthPatient, phonePatient, addressPatient,prescriptions;
    FirebaseAuth FirebaseAuth;
    DatabaseReference databaseRef;
    AddPatient addPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        btnClear = findViewById(R.id.clear);
        namePatient = findViewById(R.id.nameP);
        dateBirthPatient = findViewById(R.id.dateBirthP);
        phonePatient = findViewById(R.id.phoneP);
        addressPatient = findViewById(R.id.addressP);
        prescriptions = findViewById(R.id.prescription);
        btnSave = findViewById(R.id.save);

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
                String Text4 = prescriptions.getText().toString();
                prescriptions.setText(" ");

            }
        });


        addPatient = new AddPatient();
        //reference to AddPatient class

        databaseRef= FirebaseDatabase.getInstance().getReference().child("Prescription");


        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String str = intent.getStringExtra("message_key");
        // display the string into textView

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name = namePatient.getText().toString();
                String dateOfB = dateBirthPatient.getText().toString();
                String phoneN = phonePatient.getText().toString();
                String address = addressPatient.getText().toString();
                String prescription = prescriptions.getText().toString();

                addPatient.setName(name);
                addPatient.setDateBirth(dateOfB);
                addPatient.setPhone(phoneN);
                addPatient.setAddress(address);
                addPatient.setPrescription(prescription);


                // databaseRef = databaseRef.child(name);
                databaseRef.push().setValue(addPatient);
                //databaseRef.child(name).setValue(addPatient);
                Toast.makeText(Prescription.this, "Data insert successfully!", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent (Prescription.this,SelectPage.class);
                startActivity(myIntent);
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
        if (id == R.id.deleteAcc) {
            Intent myIntent = new Intent(Prescription.this, DeleteAccount.class);
            startActivity(myIntent);
            return false;
        }
        if (id == R.id.goHomePage) {
            Intent myIntent = new Intent(Prescription.this, SelectPage.class);
            startActivity(myIntent);
            return false;
        }
        if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Prescription.this, StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            //startActivity(myIntent);
            return false;


        }
        return super.onOptionsItemSelected(item);
    }
}