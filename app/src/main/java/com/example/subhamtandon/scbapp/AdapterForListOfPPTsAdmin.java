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

public class AdapterForListOfPPTsAdmin extends RecyclerView.Adapter<AdapterForListOfPPTsAdmin.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> picturesUrls= new ArrayList<>();
    ArrayList<String> pdfpptUrls, pptNames ;

    public AdapterForListOfPPTsAdmin(RecyclerView recyclerView, Context context, ArrayList<String> pptNames, ArrayList<String> picturesUrls, ArrayList<String> pdfpptUrls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.pptNames = pptNames;
        this.picturesUrls = picturesUrls;
        this.pdfpptUrls = pdfpptUrls;
    }

    public void update(String pptName, String pictureUrl, String pdfpptUrl){
        pptNames.add(pptName);
        picturesUrls.add(pictureUrl);
        pdfpptUrls.add(pdfpptUrl);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AdapterForListOfPPTsAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_ppts_admin, parent, false);
        return new AdapterForListOfPPTsAdmin.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForListOfPPTsAdmin.ViewHolder holder, int position) {

        Picasso.get().load(picturesUrls.get(position)).into(holder.picImage);
        holder.pptName.setText(pptNames.get(position));
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
