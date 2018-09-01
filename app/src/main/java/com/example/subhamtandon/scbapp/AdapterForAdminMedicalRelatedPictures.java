package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterForAdminMedicalRelatedPictures extends RecyclerView.Adapter<AdapterForAdminMedicalRelatedPictures.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> listOfPics;

    public AdapterForAdminMedicalRelatedPictures(RecyclerView recyclerView, Context context, ArrayList<String> listOfPics) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.listOfPics = listOfPics;
    }

    @NonNull
    @Override
    public AdapterForAdminMedicalRelatedPictures.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_medical_related_pics_admin, parent, false);
        return new AdapterForAdminMedicalRelatedPictures.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForAdminMedicalRelatedPictures.ViewHolder holder, int position) {

        Picasso.get().load(listOfPics.get(position)).into(holder.medicalRelatedPic);
        holder.medicalRelatedPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfPics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView medicalRelatedPic;
        ImageView deletePic;
        public ViewHolder(View itemView) {
            super(itemView);
            medicalRelatedPic = itemView.findViewById(R.id.imageViewAdminMedicalRelatedPics);
            deletePic = itemView.findViewById(R.id.deleteAdminMedicalRelatedPic);
        }
    }
}
