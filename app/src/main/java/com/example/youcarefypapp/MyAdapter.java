//package com.example.youcarefypapp;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//    private ArrayList<AddPatient> values;
//    public static final String MESSAGE_KEY1 ="text";
//    public static final String MESSAGE_KEY2 ="position";
//    // Provide a reference to the views for each data item
//    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        public TextView txtView_name;
//        public TextView txtView_phone;
//
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            txtView_name = (TextView) itemView.findViewById(R.id.address);
//            txtView_phone = (TextView) itemView.findViewById(R.id.dateBirth);
//
//        }
//        @Override
//        public void onClick(View view){
//            int position = this.getLayoutPosition();
//            String name = values.get(position).getName();
//            //Intent intent = new Intent(view.getContext(), UpdateActivity.class );
//            //intent.putExtra(MESSAGE_KEY1 ,name);
//            //intent.putExtra(MESSAGE_KEY2, position);
//            //view.getContext().startActivity(intent); //start activity from another activity, here we are in MyAdapter class,
//            // need to call start from the activity within that viewholder
//
//        }
//
//    }
//
//
//    // Provide the dataset to the Adapter
//    public MyAdapter(PatientView activity_patient, ArrayList<AddPatient> myDataset) {
//        values = myDataset;
//
//    }
//
//    // Create new views (invoked by the layout manager)
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent,
//                                           int viewType) {
//        // create a new view
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.activity_patient, parent, false);
//        MyViewHolder vh = new MyViewHolder(view);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        final String name = values.get(position).getName();
//        final String phone = values.get(position).getPhone();
//        holder.txtView_name.setText(name);
//        holder.txtView_phone.setText(phone);
//
//    }
//
//    // Return the size of your dataset
//    @Override
//    public int getItemCount() {
//        return values.size();
//    }
//
//}
