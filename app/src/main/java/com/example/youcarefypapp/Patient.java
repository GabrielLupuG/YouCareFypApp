//package com.example.youcarefypapp;
//
//import android.os.Bundle;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class Patient extends AppCompatActivity {
//
//    private MyAdapter mAdapter;
//    private ArrayList<AddPatient> userslist = new ArrayList<AddPatient>();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_patient);
//
//        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.RecycleView);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//
//        mAdapter = new MyAdapter(Patient.this, userslist);
//        //Add the divider line
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//
//        mRecyclerView.setAdapter(mAdapter);
//
//        retrieveUser_firebase();
//    }
//
//    public void retrieveUser_firebase(){ // dataStatus is accessed within an inner class declare final
//
//        DatabaseReference fireDBUser = FirebaseDatabase.getInstance().getReference("AddPatient");
//
//        fireDBUser.addValueEventListener(new ValueEventListener() {//that process is async process, to link with the main thread/process need
//            //to create public interface
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {//every time change data the event lister will execute ondatachange method
//
//                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) { //retrieve all nodes
//
//                    AddPatient userObj = userSnapshot.getValue(AddPatient.class);
//                    userslist.add(userObj);
//                    mAdapter.notifyItemInserted(userslist.size()-1);
//                    Log.w("USER", userObj.getName() + userObj.getPhone());
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.w("DBError", "Cancel Access DB");
//            }
//        });
//
//
//    }
//
//
//}
//
