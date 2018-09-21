package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForUserInformationBulletin extends RecyclerView.Adapter<AdapterForUserInformationBulletin.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> infosArrayList = new ArrayList<>();
    ArrayList<String> infoKeys = new ArrayList<>();
    ArrayList<String> infoDates = new ArrayList<>();
    ArrayList<String> infoTimes = new ArrayList<>();

    public AdapterForUserInformationBulletin(RecyclerView recyclerView, Context context, ArrayList<String> infosArrayList, ArrayList<String> infoKeys, ArrayList<String> infoDates, ArrayList<String> infoTimes) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.infosArrayList = infosArrayList;
        this.infoKeys = infoKeys;
        this.infoDates = infoDates;
        this.infoTimes = infoTimes;
    }

    public void update(String infoName, String infoKey, String infoDate, String infoTime){

        infosArrayList.add(infoName);
        infoKeys.add(infoKey);
        infoDates.add(infoDate);
        infoTimes.add(infoTime);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_information_bulletin_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.infoTextView.setText(infosArrayList.get(position));
        holder.dateAndTimeInfo.setText(infoDates.get(position) + " at " + infoTimes.get(position));
    }

    @Override
    public int getItemCount() {
        return infosArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView dateAndTimeInfo,infoTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            dateAndTimeInfo = itemView.findViewById(R.id.dateAndTimeInfo);
            infoTextView = itemView.findViewById(R.id.infoTextView);
        }
    }
}
