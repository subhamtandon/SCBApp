package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterForPPTUser extends RecyclerView.Adapter<AdapterForPPTUser.ViewHolder> {
    RecyclerView recyclerView;
    Context context;
    ArrayList<String> picturesUrls= new ArrayList<>();
    ArrayList<String> pdfpptUrls, pptNames, ids ;

    public void update(String pptName, String pictureUrl, String pdfpptUrl, String id){
        pptNames.add(pptName);
        picturesUrls.add(pictureUrl);
        pdfpptUrls.add(pdfpptUrl);
        ids.add(id);
        notifyDataSetChanged();
    }

    public AdapterForPPTUser(RecyclerView recyclerView, Context context, ArrayList<String> picturesUrls, ArrayList<String> pdfpptUrls, ArrayList<String> pptNames, ArrayList<String> ids) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.picturesUrls = picturesUrls;
        this.pdfpptUrls = pdfpptUrls;
        this.pptNames = pptNames;
        this.ids = ids;
    }

    @NonNull
    @Override
    public AdapterForPPTUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_ppts_user, parent, false);
        return new AdapterForPPTUser.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForPPTUser.ViewHolder holder, final int position) {

        Picasso.get().load(picturesUrls.get(position)).placeholder(R.mipmap.ic_launcher).into(holder.picImage);
        holder.pptName.setText(pptNames.get(position));
        holder.picImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PDFViewerActivity.class);
                intent.putExtra("PDF_STRING", pdfpptUrls.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return picturesUrls.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picImage;
        TextView pptName;
        public ViewHolder(View itemView) {
            super(itemView);
            picImage = itemView.findViewById(R.id.imageViewPPTThumbnail);
            pptName = itemView.findViewById(R.id.textViewPPTName);
        }
    }
}
