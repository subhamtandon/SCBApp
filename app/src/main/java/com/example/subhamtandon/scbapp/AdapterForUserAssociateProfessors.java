package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterForUserAssociateProfessors extends RecyclerView.Adapter<AdapterForUserAssociateProfessors.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<DoctorDetails> doctorDetailsArrayList = new ArrayList<DoctorDetails>();
    ArrayList<String> associateProfessorsIdsArrayList = new ArrayList<>();
    String departmentName;

    public AdapterForUserAssociateProfessors(RecyclerView recyclerView, Context context, ArrayList<DoctorDetails> doctorDetailsArrayList, ArrayList<String> associateProfessorsIdsArrayList, String departmentName) {
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
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_user_associate_professors, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DatabaseReference databaseReferenceAssociateProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Associate Professor").child(associateProfessorsIdsArrayList.get(position));
        holder.UserAssociateProfessorTextView.setText(doctorDetailsArrayList.get(position).getName());
        if (doctorDetailsArrayList.get(position).getDescription().equalsIgnoreCase("Empty")){
            holder.descriptionAssociateProfessor.setVisibility(View.GONE);
        }else {
            holder.descriptionAssociateProfessor.setVisibility(View.VISIBLE);
            holder.descriptionAssociateProfessor.setText(doctorDetailsArrayList.get(position).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return doctorDetailsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView UserAssociateProfessorTextView, descriptionAssociateProfessor;

        public ViewHolder(View itemView) {
            super(itemView);
            UserAssociateProfessorTextView = itemView.findViewById(R.id.UserAssociateProfessorTextView);
            descriptionAssociateProfessor = itemView.findViewById(R.id.descriptionAssociateProfessor);
        }
    }
}
