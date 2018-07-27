package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForInfoList extends RecyclerView.Adapter<AdapterForInfoList.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> infosArrayList = new ArrayList<>();

    public AdapterForInfoList(RecyclerView recyclerView, Context context, ArrayList<String> infosArrayList) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.infosArrayList = infosArrayList;
    }
    public void update(String infoName){

        infosArrayList.add(infoName);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_info, parent, false);
        return new AdapterForInfoList.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.infoName.setText(infosArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return infosArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView infoName;

        public ViewHolder(View itemView){
            super(itemView);
            infoName = itemView.findViewById(R.id.infoTextView);
        }
    }
}
