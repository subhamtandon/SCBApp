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

public class AdapterForUserRecords extends RecyclerView.Adapter<AdapterForUserRecords.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String > arrayList = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> ids = new ArrayList<>();
    String professional;
    String subject;

    public AdapterForUserRecords(RecyclerView recyclerView, Context context, String professional, String subject) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.professional = professional;
        this.subject = subject;
    }

    public void update(String recordName, String url, String id){
        arrayList.add(recordName);
        urls.add(url);
        ids.add(id);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_user_records, parent, false);
        return new AdapterForUserRecords.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.textViewRecordName.setText(arrayList.get(position));
        holder.textViewRecordName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PDFViewerActivity.class);
                intent.putExtra("PDF_STRING", urls.get(position));
                context.startActivity(intent);
                /*Uri webpage = Uri.parse(urls.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewRecordName;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewRecordName = itemView.findViewById(R.id.textViewRecordName);
        }
    }
}
