//package com.example.youcarefypapp;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class RecyclerView_Config {
//    private Context mContext;
//    private  PatientAdapter mPatientAdapter;
//    private List<AddPatient> addPatients;
//
//    public void setConfig(RecyclerView recyclerView, com.example.youcarefypapp.PatientView patientView, List<AddPatient> patient, List<String> keys){
//
//        mContext = context;
//        mPatientAdapter = new PatientAdapter(addPatients, keys);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setAdapter(mPatientAdapter);
//    }
//
//
//    class PatientView extends RecyclerView.ViewHolder {
//        private TextView name;
//        private TextView dateBirth;
//        private TextView phone;
//        private TextView address;
//
//        private String key;
//
//        public PatientView(ViewGroup parent){
//            super(LayoutInflater.from(mContext).inflate(R.layout.patient, parent, false));
//
//            name = (TextView) itemView.findViewById(R.id.name);
//            dateBirth = (TextView) itemView.findViewById(R.id.dateBirth);
//            phone = (TextView) itemView.findViewById(R.id.phone);
//            address = (TextView) itemView.findViewById(R.id.address);
//        }
//
//        public void bind(AddPatient patient, String key){
//            name.setText(patient.getName());
//            dateBirth.setText(patient.getDateBirth());
//            phone.setText(patient.getPhone());
//            address.setText(patient.getAddress());
//            this.key = key;
//        }
//    }
//    class PatientAdapter extends RecyclerView.Adapter<PatientView>{
//        private List<AddPatient> mPatientViews;
//        private  List<String> mKeys;
//
//        public PatientAdapter(List<AddPatient> mPatientViews, List<String> mKeys) {
//            this.mPatientViews = mPatientViews;
//            this.mKeys = mKeys;
//        }
//
//        @NonNull
//        @Override
//        public PatientView onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new PatientView(parent);
//        }
//
//        @Override
//        public void onBindViewHolder( PatientView holder, int position) {
//
//            holder.bind(mPatientViews.get(position), mKeys.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return mPatientViews.size();
//        }
//    }
//}
