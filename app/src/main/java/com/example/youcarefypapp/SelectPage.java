package com.example.youcarefypapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SelectPage extends AppCompatActivity {

    ImageButton btnAddUser, viewList, receipt, calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);


        btnAddUser = findViewById(R.id.addUser);
        viewList = findViewById(R.id.viewListPatient);
        receipt = findViewById(R.id.prescription);
        calendar=findViewById(R.id.calendar);


        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToReq = new Intent(SelectPage.this, PatientView.class);
                startActivity(intToReq);
            }
        });


        receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToReq = new Intent(SelectPage.this, Prescription.class);
                startActivity(intToReq);
            }
        });


        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToReq = new Intent(SelectPage.this, Calendar.class);
                startActivity(intToReq);
            }
        });


        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToReq = new Intent(SelectPage.this, HomeActivity.class);
                startActivity(intToReq);

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
            Intent myIntent = new Intent (SelectPage.this,DeleteAccount.class);
            startActivity(myIntent);
            return false;
        }
        if(id==R.id.goHomePage){
            Intent myIntent = new Intent (SelectPage.this,SelectPage.class);
            startActivity(myIntent);
            return false;
        }
        if(id==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity( new Intent (SelectPage.this,StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            //startActivity(myIntent);
            return false;



        }
        return super.onOptionsItemSelected(item);
    }
}
