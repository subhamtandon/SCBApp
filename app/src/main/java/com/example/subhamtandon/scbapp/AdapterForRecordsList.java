package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterForRecordsList extends RecyclerView.Adapter<AdapterForRecordsList.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String > arrayList = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();

    public AdapterForRecordsList(RecyclerView recyclerView, Context context, ArrayList<String> arrayList, ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.arrayList = arrayList;
        this.urls = urls;
    }

    public void update(String recordName, String url){
        arrayList.add(recordName);
        urls.add(url);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_records, parent, false);
        return new AdapterForRecordsList.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final int pos = position;

        holder.nameOfFile.setText(arrayList.get(position));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Edit clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Delete clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.nameOfFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = recyclerView.getChildLayoutPosition(v);
                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urls.get(pos)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameOfFile;
        ImageView edit , delete;

        public ViewHolder(View itemView) {
            super(itemView);
            nameOfFile = itemView.findViewById(R.id.textViewRecordName);
            edit = itemView.findViewById(R.id.editRecord);
            delete = itemView.findViewById(R.id.deleteRecord);
            /*

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = recyclerView.getChildLayoutPosition(v);
                    Intent intent = new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                }
            });
            */

        }
    }
}
