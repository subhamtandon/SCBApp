package com.company.ssDev.que9;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForSetsList extends RecyclerView.Adapter<AdapterForSetsList.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> setsArrayList = new ArrayList<>();
    String professional;
    String subject;
    String chapter;

    public AdapterForSetsList(RecyclerView recyclerView, Context context, ArrayList<String> setsArrayList, String professional, String subject, String chapter) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.setsArrayList = setsArrayList;
        this.professional = professional;
        this.subject = subject;
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
        return new AdapterForSetsList.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final int pos = position;

        holder.setName.setText(setsArrayList.get(position));    
        holder.setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListOfSetsActivity listOfSetsActivity = (ListOfSetsActivity) context;
                listOfSetsActivity.passActivity(setsArrayList,position);
                //context.startActivity(intent);

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
