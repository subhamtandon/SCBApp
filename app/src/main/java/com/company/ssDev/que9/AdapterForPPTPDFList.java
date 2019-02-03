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

public class AdapterForPPTPDFList extends RecyclerView.Adapter<AdapterForPPTPDFList.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String > arrayList = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> ids = new ArrayList<>();
    String typeOfCard;
    String typeOfPaperPresentation;

    public AdapterForPPTPDFList(RecyclerView recyclerView, Context context, ArrayList<String> arrayList, ArrayList<String> urls, ArrayList<String> ids, String typeOfCard, String typeOfPaperPresentation) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.arrayList = arrayList;
        this.urls = urls;
        this.ids = ids;
        this.typeOfCard = typeOfCard;
        this.typeOfPaperPresentation = typeOfPaperPresentation;
    }
    public void update(String pdfName, String url, String id){
        arrayList.add(pdfName);
        urls.add(url);
        ids.add(id);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterForPPTPDFList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_new_ppt_pdf, parent, false);
        return new AdapterForPPTPDFList.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForPPTPDFList.ViewHolder holder, final int position) {

        holder.nameOfFile.setText(arrayList.get(position));
        holder.nameOfFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameOfFile;
        public ViewHolder(View itemView) {
            super(itemView);
            nameOfFile = itemView.findViewById(R.id.nameOfPPT);
        }
    }
}
