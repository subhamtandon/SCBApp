package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForBasicSets extends RecyclerView.Adapter<AdapterForBasicSets.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> setsArrayList = new ArrayList<>();
    String professional;
    String subject;
    String type;
    String chapter;
    String mode;

    public AdapterForBasicSets(RecyclerView recyclerView, Context context, ArrayList<String> setsArrayList, String professional, String subject, String type, String chapter, String mode) {
        this.context = context;
        this.setsArrayList = setsArrayList;
        this.professional = professional;
        this.subject = subject;
        this.type = type;
        this.chapter = chapter;
        this.mode = mode;
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

                Intent intent = new Intent(context, QuestionsActivity.class);
                intent.putExtra("PROFESSIONAL",professional);
                intent.putExtra("SUBJECT",subject);
                intent.putExtra("TYPE", type);
                intent.putExtra("CHAPTER", chapter);
                intent.putExtra("MODE",mode);
                intent.putExtra("SET",setsArrayList.get(position));
                context.startActivity(intent);

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
