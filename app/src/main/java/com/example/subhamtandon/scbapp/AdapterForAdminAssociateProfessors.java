package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterForAdminAssociateProfessors extends RecyclerView.Adapter<AdapterForAdminAssociateProfessors.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<DoctorDetails> doctorDetailsArrayList = new ArrayList<DoctorDetails>();
    ArrayList<String> associateProfessorsIdsArrayList = new ArrayList<>();
    String departmentName;

    public AdapterForAdminAssociateProfessors(RecyclerView recyclerView, Context context, ArrayList<DoctorDetails> doctorDetailsArrayList, ArrayList<String> associateProfessorsIdsArrayList, String departmentName) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.doctorDetailsArrayList = doctorDetailsArrayList;
        this.associateProfessorsIdsArrayList = associateProfessorsIdsArrayList;
        this.departmentName = departmentName;
    }

    public void update(DoctorDetails doctorDetails, String associateProfessorId){
        doctorDetailsArrayList.add(doctorDetails);
        associateProfessorsIdsArrayList.add(associateProfessorId);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_associate_professors, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DatabaseReference databaseReferenceAssociateProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Associate Professor").child(associateProfessorsIdsArrayList.get(position));
        holder.associateProfessorTextView.setText(doctorDetailsArrayList.get(position).getName());
        if (doctorDetailsArrayList.get(position).getDescription().equalsIgnoreCase("Empty")){
            holder.descriptionAssociateProfessor.setVisibility(View.GONE);
        }else {
            holder.descriptionAssociateProfessor.setText(doctorDetailsArrayList.get(position).getDescription());
        }
        holder.deleteAssociateProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReferenceAssociateProfessor.removeValue();
                AdminDoctorsDetails adminDoctorsDetails = (AdminDoctorsDetails)context;
                adminDoctorsDetails.reloadActivity();
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorDetailsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView associateProfessorTextView, descriptionAssociateProfessor;
        public ImageView deleteAssociateProfessor;

        public ViewHolder(View itemView) {
            super(itemView);
            associateProfessorTextView = itemView.findViewById(R.id.associateProfessorTextView);
            descriptionAssociateProfessor = itemView.findViewById(R.id.descriptionAssociateProfessor);
            deleteAssociateProfessor = itemView.findViewById(R.id.deleteAssociateProfessor);
        }
    }
}
