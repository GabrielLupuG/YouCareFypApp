package com.example.youcarefypapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientView extends AppCompatActivity {
    private static final String TAG = "ViewDatabase";

    //add Firebase Database stuff
     FirebaseDatabase mFirebaseDatabase;
     FirebaseAuth mAuth;
     FirebaseAuth.AuthStateListener mAuthListener;
     DatabaseReference myRef;
      String userID;

     ListView mListView;

     ArrayList<AddPatient> list;
     ArrayAdapter<AddPatient>adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view);

        mListView = (ListView) findViewById(R.id.listview);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("AddPatient");
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                   //toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
              //  showData(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

//    private void showData(DataSnapshot dataSnapshot) {
//        for(DataSnapshot ds : dataSnapshot.getChildren()){
//            AddPatient uInfo = new AddPatient();
//            uInfo.setName(ds.child(userID).getValue(AddPatient.class).getName());
//            uInfo.setAddress(ds.child(userID).getValue(AddPatient.class).getAddress());
//            uInfo.setDateBirth(ds.child(userID).getValue(AddPatient.class).getDateBirth());
//            uInfo.setPhone(ds.child(userID).getValue(AddPatient.class).getPhone());
//
//            //display all the information
//            Log.d(TAG, "showData: name: " + uInfo.getName());
//            Log.d(TAG, "showData: email: " + uInfo.getAddress());
//            Log.d(TAG, "showData: phone_num: " + uInfo.getDateBirth());
//            Log.d(TAG, "showData: phone_num: " + uInfo.getPhone());
//
//            ArrayList<String> array  = new ArrayList<>();
//            array.add(uInfo.getName());
//            array.add(uInfo.getAddress());
//            array.add(uInfo.getDateBirth());
//            array.add(uInfo.getPhone());
//            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
//            mListView.setAdapter(adapter);
//        }
//    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
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
            Intent myIntent = new Intent (PatientView.this,DeleteAccount.class);
            startActivity(myIntent);
            return false;
        }
        if(id==R.id.goHomePage){
            Intent myIntent = new Intent (PatientView.this,SelectPage.class);
            startActivity(myIntent);
            return false;
        }
        if(id==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity( new Intent (PatientView.this,StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            //startActivity(myIntent);
            return false;



        }
        return super.onOptionsItemSelected(item);
    }
}
