package com.example.subhamtandon.scbapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    public void update(String pictureUrl){
        listOfPics.add(pictureUrl);
        notifyDataSetChanged();
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
        holder.deletePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Delete File");
                alertDialog.setMessage("Do you want to delete this file? ");
                alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*

                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Information");

                        String uploadPDFID = databaseReference.child(infoKeys.get(pos)).getKey();

                        databaseReference.child(uploadPDFID).removeValue();
                        infosArrayList.remove(pos);
                        //urls.remove(pos);
                        infoDates.remove(pos);
                        infoTimes.remove(pos);
                        infoKeys.remove(pos);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Deletion done successfully" , Toast.LENGTH_SHORT).show();

                        InformationBulletinActivity informationBulletinActivity = (InformationBulletinActivity)context;
                        informationBulletinActivity.reloadActivity();
                        */


                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
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
