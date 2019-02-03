package com.company.ssDev.que9;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForVideoCategories extends RecyclerView.Adapter<AdapterForVideoCategories.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> listOfNamesOfVideoCategories= new ArrayList<>();

    public AdapterForVideoCategories(RecyclerView recyclerView, Context context, ArrayList<String> listOfNamesOfVideoCategories) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.listOfNamesOfVideoCategories = listOfNamesOfVideoCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_video_categories, parent, false);
        return new AdapterForVideoCategories.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.nameOfVideoCategories.setText(listOfNamesOfVideoCategories.get(position));
        holder.nameOfVideoCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, YoutubeVideoView.class);
                i.putExtra("Category", listOfNamesOfVideoCategories.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listOfNamesOfVideoCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameOfVideoCategories;

        public ViewHolder(View itemView) {
            super(itemView);
            nameOfVideoCategories = itemView.findViewById(R.id.nameOfVideoCategories);
        }
    }
}
