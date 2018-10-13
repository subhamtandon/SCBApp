package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterForUserInformationBulletin extends RecyclerView.Adapter<AdapterForUserInformationBulletin.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> infosArrayList = new ArrayList<>();
    ArrayList<String> infoKeys = new ArrayList<>();
    ArrayList<String> infoDates = new ArrayList<>();
    ArrayList<String> infoTimes = new ArrayList<>();
    ArrayList<String> infoImageUris ;

    public AdapterForUserInformationBulletin(RecyclerView recyclerView, Context context, ArrayList<String> infosArrayList, ArrayList<String> infoKeys, ArrayList<String> infoDates, ArrayList<String> infoTimes, ArrayList<String> infoImageUris) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.infosArrayList = infosArrayList;
        this.infoKeys = infoKeys;
        this.infoDates = infoDates;
        this.infoTimes = infoTimes;
        this.infoImageUris = infoImageUris;
    }

    public void update(String infoName, String infoKey, String infoDate, String infoTime, String infoImageUri){

        infosArrayList.add(infoName);
        infoKeys.add(infoKey);
        infoDates.add(infoDate);
        infoTimes.add(infoTime);
        infoImageUris.add(infoImageUri);
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
        if(!infoImageUris.get(position).equals("No image selected"))
        {
            Picasso.get().load(infoImageUris.get(position)).into(holder.infoImageView);
        }
    }

    @Override
    public int getItemCount() {
        return infosArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView dateAndTimeInfo,infoTextView;
        public ImageView infoImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            dateAndTimeInfo = itemView.findViewById(R.id.dateAndTimeInfo);
            infoTextView = itemView.findViewById(R.id.infoTextView);
            infoImageView = itemView.findViewById(R.id.infoImageView);
        }
    }
}
