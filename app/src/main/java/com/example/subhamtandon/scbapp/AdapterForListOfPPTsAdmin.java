package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterForListOfPPTsAdmin extends RecyclerView.Adapter<AdapterForListOfPPTsAdmin.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> picturesUrls= new ArrayList<>();

    public AdapterForListOfPPTsAdmin(RecyclerView recyclerView, Context context, ArrayList<String> picturesUrls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.picturesUrls = picturesUrls;
    }

    public void update(String pictureUrl){
        picturesUrls.add(pictureUrl);
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
    }

    @Override
    public int getItemCount() {
        return picturesUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picImage;
        public ViewHolder(View itemView) {
            super(itemView);
            picImage = itemView.findViewById(R.id.imageViewPPTThumbnail);
        }
    }
}
