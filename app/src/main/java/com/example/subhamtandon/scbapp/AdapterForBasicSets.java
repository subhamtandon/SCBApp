package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterForBasicSets extends RecyclerView.Adapter<AdapterForBasicSets.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> setsArrayList = new ArrayList<>();
    String professional;
    String subject;
    String type;
    String chapter;


    public AdapterForBasicSets(RecyclerView recyclerView, Context context, ArrayList<String> setsArrayList, String professional, String subject, String type, String chapter) {
        this.context = context;
        this.setsArrayList = setsArrayList;
        this.professional = professional;
        this.subject = subject;
        this.type = type;
        this.chapter = chapter;
    }

    public void update(String setName){

        setsArrayList.add(setName);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_sets, parent, false);
        return new AdapterForBasicSets.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final int pos = position;

        holder.setName.setText(setsArrayList.get(position));
        holder.setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<String> idsArrayList = new ArrayList<>();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                        .child("App")
                        .child("Study")
                        .child(professional)
                        .child(subject)
                        .child(type)
                        .child(chapter)
                        .child(setsArrayList.get(position));

                /*databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        if (dataSnapshot != null){
                            String uniqueId = dataSnapshot.getKey();
                            idsArrayList.add(uniqueId);
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*/

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String uniqueId = ds.getKey();
                                Log.d("uniqueId", uniqueId);
                                idsArrayList.add(uniqueId);
                            }

                            for (int i = 0; i < idsArrayList.size(); i++){
                                Log.d("TAG",idsArrayList.get(i));
                            }

                            Intent intent = new Intent(context, QuestionsActivity.class);
                            intent.putExtra("PROFESSIONAL",professional);
                            intent.putExtra("SUBJECT",subject);
                            intent.putExtra("TYPE", type);
                            intent.putExtra("CHAPTER", chapter);
                            intent.putExtra("SET",setsArrayList.get(position));
                            intent.putStringArrayListExtra("IDS",idsArrayList);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                /*for (int i = 0;i < idsArrayList.size(); i++) {
                    Toast.makeText(context, idsArrayList.get(i), Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < idsArrayList.size(); i++){
                    Log.d("TAG",idsArrayList.get(i));
                }*/



            }
        });

    }

    @Override
    public int getItemCount() {
        return setsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView setName;

        public ViewHolder(View itemView) {
            super(itemView);
            setName = itemView.findViewById(R.id.textViewSetName);
        }
    }
}
