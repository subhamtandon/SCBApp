package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForUserProfessors extends RecyclerView.Adapter<AdapterForUserProfessors.ViewHolder> {
    RecyclerView recyclerView;
    Context context;
    ArrayList<DoctorDetails> doctorDetailsArrayList = new ArrayList<DoctorDetails>();
    ArrayList<String> professorsIdsArrayList = new ArrayList<String>();
    String departmentName;

    public AdapterForUserProfessors(RecyclerView recyclerView, Context context, ArrayList<DoctorDetails> doctorDetailsArrayList, ArrayList<String> professorsIdsArrayList, String departmentName) {
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
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_user_professor, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userProfessorTextView.setText(doctorDetailsArrayList.get(position).getName());
        holder.descriptionProfessor.setText(doctorDetailsArrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return doctorDetailsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView userProfessorTextView, descriptionProfessor;

        public ViewHolder(View itemView) {
            super(itemView);
            userProfessorTextView = itemView.findViewById(R.id.userProfessorTextView);
            descriptionProfessor = itemView.findViewById(R.id.descriptionProfessor);
        }
    }
}
