//package com.example.youcarefypapp;
//
//import androidx.annotation.NonNull;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FirebaseDatabaseHelper {
//    private FirebaseDatabase mDatabase;
//    private DatabaseReference mReferencePatient;
//    private List <AddPatient> patient = new ArrayList<>();
//
//    public interface DataStatus{
//        void DataIsLoaded(List<AddPatient> patient, List<String> keys);
//        void DataIsInserted();
//        void DataIsUpdated();
//        void DataIsDeleted();
//    }
//
//    public FirebaseDatabaseHelper() {
//        mDatabase = FirebaseDatabase.getInstance();
//        mReferencePatient = mDatabase.getReference("PatientName");
//    }
//
//    public void readPatients(final DataStatus dataStatus){
//        mReferencePatient.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                patient.clear();
//                List<String> keys = new ArrayList<>();
//                for (DataSnapshot keyNode :dataSnapshot.getChildren()){
//                    keys.add(keyNode.getKey());
//                    AddPatient AddPatient = keyNode.getValue(AddPatient.class);
//                    patient.add(AddPatient);
//                }
//                dataStatus.DataIsLoaded(patient,keys);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//}
