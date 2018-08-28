package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterForMedicalRelatedPictures extends RecyclerView.Adapter<AdapterForMedicalRelatedPictures.ViewHolder> {
    RecyclerView recyclerView;
    Context context;
    ArrayList<String> picturesUrls= new ArrayList<>();

    public AdapterForMedicalRelatedPictures(RecyclerView recyclerView, Context context, ArrayList<String> picturesUrls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.picturesUrls = picturesUrls;
    }


    @NonNull
    @Override
    public AdapterForMedicalRelatedPictures.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_medical_related_pictures, parent, false);
        return new AdapterForMedicalRelatedPictures.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.picImage.setImageURI(Uri.parse(picturesUrls.get(position)));
        Picasso.get().load(picturesUrls.get(position)).into(holder.picImage);
    }

    @Override
    public int getItemCount() {
        return picturesUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView picImage;
        public ViewHolder(View itemView) {
            super(itemView);
            picImage = itemView.findViewById(R.id.imageViewMedicalRelatedPeople);
        }
    }
}
