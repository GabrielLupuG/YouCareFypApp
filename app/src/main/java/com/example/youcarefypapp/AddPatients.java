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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPatients extends AppCompatActivity {

    Button btnSave,btnView,delete,btnClear,btnLogout;
    FirebaseAuth FirebaseAuth;
    EditText namePatient, dateBirthPatient, phonePatient, addressPatient;
    DatabaseReference databaseRef;
    ProgressBar progressBar;
    AddPatient addPatient;


//    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;

    private FirebaseAuth.AuthStateListener AuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patients);


        btnLogout = findViewById(R.id.logout);
        btnClear = findViewById(R.id.clear);
        btnSave = findViewById(R.id.save);
        delete = findViewById(R.id.deleteAcc);
        namePatient = findViewById(R.id.nameP);
        dateBirthPatient = findViewById(R.id.dateBirthP);
        phonePatient = findViewById(R.id.phoneP);
        addressPatient = findViewById(R.id.addressP);
        progressBar = findViewById(R.id.progressBar);

        btnView = findViewById(R.id.View);

        addPatient = new AddPatient();
        //reference to AddPatient class

        String name = namePatient.getText().toString();



        databaseRef= FirebaseDatabase.getInstance().getReference().child("AddPatient");

      //  receiver_msg = (TextView) findViewById(R.id.received_value_id);
        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
//        String str = intent.getStringExtra("message_key");
//        // display the string into textView
//        receiver_msg.setText(str);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToReq = new Intent(AddPatients.this, PatientView.class);
                startActivity(intToReq);
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


               // databaseRef = databaseRef.child(name);
                databaseRef.push().setValue(addPatient);
                ///databaseRef.child(name).setValue(addPatient);
                Toast.makeText(AddPatients.this, "Data insert successfully!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent (AddPatients.this,PatientView.class);
                startActivity(myIntent);

//                if (name.isEmpty()) {
//                    namePatient.setError("Please enter patient name");
//                    namePatient.requestFocus();
//                } else if (dateOfB.isEmpty()) {
//                    dateBirthPatient.setError("Please enter your password");
//                    dateBirthPatient.requestFocus();
//                    Toast.makeText(HomeActivity.this, "Please enter patient date of birth", Toast.LENGTH_LONG).show();
//                } else if (phoneN.isEmpty()) {
//                    phonePatient.setError("Please enter your password");
//                    phonePatient.requestFocus();
//                    Toast.makeText(HomeActivity.this, "Please enter patient date of birth", Toast.LENGTH_LONG).show();
//                } else if (address.isEmpty()) {
//                    addressPatient.setError("Please enter your password");
//                    addressPatient.requestFocus();
//                    Toast.makeText(HomeActivity.this, "Please enter patient date of birth", Toast.LENGTH_LONG).show();
//                } else if (name.isEmpty() && dateOfB.isEmpty() && phoneN.isEmpty() && address.isEmpty()) {
//                    Toast.makeText(HomeActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
//                }
//
//                else if(!(name.isEmpty() && dateOfB.isEmpty() && phoneN.isEmpty() && address.isEmpty())){
//
//
//                    Toast.makeText(HomeActivity.this, "Data insert successfully!", Toast.LENGTH_SHORT).show();
//
//
//                }
//                else {
//                    //toast error if any error occurred
//                    Toast.makeText(HomeActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
//
//


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
            Intent myIntent = new Intent (AddPatients.this,DeleteAccount.class);
            startActivity(myIntent);
            return false;
        }
        if(id==R.id.goHomePage){
            Intent myIntent = new Intent (AddPatients.this,SelectPage.class);
            startActivity(myIntent);
            return false;
        }
        if(id==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity( new Intent (AddPatients.this,StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            //startActivity(myIntent);
            return false;



        }
        return super.onOptionsItemSelected(item);
    }
}
