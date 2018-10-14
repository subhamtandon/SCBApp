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


public class AdapterForAdminProfessors extends RecyclerView.Adapter<AdapterForAdminProfessors.ViewHolder> {
    RecyclerView recyclerView;
    Context context;
    ArrayList<DoctorDetails> doctorDetailsArrayList = new ArrayList<DoctorDetails>();
    ArrayList<String> professorsIdsArrayList = new ArrayList<String>();
    String departmentName;

    public AdapterForAdminProfessors(RecyclerView recyclerView, Context context, ArrayList<DoctorDetails> doctorDetailsArrayList, ArrayList<String> professorsIdsArrayList, String departmentName) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.doctorDetailsArrayList = doctorDetailsArrayList;
        this.professorsIdsArrayList = professorsIdsArrayList;
        this.departmentName = departmentName;
    }

    public void update(DoctorDetails doctorDetails, String professorId){
        doctorDetailsArrayList.add(doctorDetails);
        professorsIdsArrayList.add(professorId);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_professors, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DatabaseReference databaseReferenceProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Professor").child(professorsIdsArrayList.get(position));
        holder.professorTextView.setText(doctorDetailsArrayList.get(position).getName());
        if (doctorDetailsArrayList.get(position).getDescription().equalsIgnoreCase("Empty")){
            holder.descriptionProfessor.setVisibility(View.GONE);
        }else {
            holder.descriptionProfessor.setText(doctorDetailsArrayList.get(position).getDescription());
        }
        holder.deleteprofessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReferenceProfessor.removeValue();
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

        public TextView professorTextView, descriptionProfessor;
        public ImageView deleteprofessor;

        public ViewHolder(View itemView) {
            super(itemView);
            professorTextView = itemView.findViewById(R.id.professorTextView);
            descriptionProfessor = itemView.findViewById(R.id.descriptionProfessor);
            deleteprofessor = itemView.findViewById(R.id.deleteprofessor);
        }
    }
}
