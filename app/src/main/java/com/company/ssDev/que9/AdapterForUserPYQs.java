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

public class AdapterForUserPYQs extends RecyclerView.Adapter<AdapterForUserPYQs.ViewHolder>{

    RecyclerView recyclerView;
    Context context;
    ArrayList<String > arrayList = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> ids = new ArrayList<>();
    String professional;
    String subject;

    public AdapterForUserPYQs(RecyclerView recyclerView, Context context, String professional, String subject) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.professional = professional;
        this.subject = subject;
    }

    public void update(String PYQName, String url, String id){
        arrayList.add(PYQName);
        urls.add(url);
        ids.add(id);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_user_pyqs, parent, false);
        return new AdapterForUserPYQs.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.textViewPYQName.setText(arrayList.get(position));
        holder.textViewPYQName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Uri webpage = Uri.parse(urls.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }*/
                Intent intent = new Intent(context, PDFViewerActivity.class);
                intent.putExtra("PDF_STRING", urls.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewPYQName;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPYQName = itemView.findViewById(R.id.textViewPYQName);
        }
    }
}
