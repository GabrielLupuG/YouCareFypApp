package com.example.youcarefypapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    TextView receiver_msg;
    Button btnSave;
    Button btnLogout;
    FirebaseAuth FirebaseAuth;
    EditText namePatient, dateBirthPatient, phonePatient, addressPatient;

    DatabaseReference databaseRef;

    AddPatient addPatient;



    private FirebaseAuth.AuthStateListener AuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout = findViewById(R.id.logout);
        btnSave = findViewById(R.id.save);

        namePatient = findViewById(R.id.nameP);
        dateBirthPatient = findViewById(R.id.dateBirthP);
        phonePatient = findViewById(R.id.phoneP);
        addressPatient = findViewById(R.id.addressP);

        addPatient = new AddPatient();
        //reference to AddPatient class
        databaseRef= FirebaseDatabase.getInstance().getReference().child("AddPatient");



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
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = namePatient.getText().toString();
                String dateOfB = dateBirthPatient.getText().toString();
                String phoneN = phonePatient.getText().toString();
                String address = addressPatient.getText().toString();

                addPatient.setName(name);
                addPatient.setDateBirth(dateOfB);
                addPatient.setPhone(phoneN);
                addPatient.setAddress(address);

                databaseRef.push().setValue(addPatient);
                Toast.makeText(HomeActivity.this, "Data insert successfully!", Toast.LENGTH_SHORT).show();

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
//                }
            }
        });


//        public void basicReadWrite () {
//            // [START write_message]
//            // Write a message to the database
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference myRef = database.getReference("message");
//
//            myRef.setValue("Hello, World!");
//            // [END write_message]
//
//            // [START read_message]
//            // Read from the database
//        }
    }
}
