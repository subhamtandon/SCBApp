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

public class AdapterForAdminAssistantProfessors extends RecyclerView.Adapter<AdapterForAdminAssistantProfessors.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> assistantProfessorsArrayList = new ArrayList<>();
    ArrayList<String> assistantProfessorsIdsArrayList = new ArrayList<>();
    String departmentName;

    public AdapterForAdminAssistantProfessors(RecyclerView recyclerView, Context context, ArrayList<String> assistantProfessorsArrayList, ArrayList<String> assistantProfessorsIdsArrayList, String departmentName) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.assistantProfessorsArrayList = assistantProfessorsArrayList;
        this.assistantProfessorsIdsArrayList = assistantProfessorsIdsArrayList;
        this.departmentName = departmentName;
    }

    public void update(String assistantProfessorName, String assistantProfessorId){
        assistantProfessorsArrayList.add(assistantProfessorName);
        assistantProfessorsIdsArrayList.add(assistantProfessorId);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.list_item_assistant_professors, parent, false);
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DatabaseReference databaseReferenceAssistantProfessor = FirebaseDatabase.getInstance().getReference("App").child("Departments").child(departmentName).child("Assistant Professor").child(assistantProfessorsIdsArrayList.get(position));
        holder.assistantProfessorTextView.setText(assistantProfessorsArrayList.get(position));

        holder.deleteAssistantProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReferenceAssistantProfessor.removeValue();
                AdminDoctorsDetails adminDoctorsDetails = (AdminDoctorsDetails)context;
                adminDoctorsDetails.reloadActivity();
            }
        });
    }

    @Override
    public int getItemCount() {
        return assistantProfessorsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView assistantProfessorTextView;
        public ImageView deleteAssistantProfessor;

        public ViewHolder(View itemView) {
            super(itemView);
            assistantProfessorTextView = itemView.findViewById(R.id.assistantProfessorTextView);
            deleteAssistantProfessor = itemView.findViewById(R.id.deleteAssistantProfessor);
        }
    }
}
