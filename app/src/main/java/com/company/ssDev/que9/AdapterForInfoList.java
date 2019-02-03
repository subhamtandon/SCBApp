package com.company.ssDev.que9;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterForInfoList extends RecyclerView.Adapter<AdapterForInfoList.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> infoTitleArrayList = new ArrayList<>();
    ArrayList<String> infoDescriptionArrayList = new ArrayList<>();
    ArrayList<String> infoKeys = new ArrayList<>();
    ArrayList<String> infoDates = new ArrayList<>();
    ArrayList<String> infoTimes = new ArrayList<>();
    ArrayList<String> infoImageUris ;

    public AdapterForInfoList(RecyclerView recyclerView, Context context, ArrayList<String> infoTitleArrayList, ArrayList<String> infoDescriptionArrayList, ArrayList<String> infoKeys, ArrayList<String> infoDates, ArrayList<String> infoTimes, ArrayList<String> infoImageUris) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.infoTitleArrayList = infoTitleArrayList;
        this.infoDescriptionArrayList = infoDescriptionArrayList;
        this.infoKeys = infoKeys;
        this.infoDates = infoDates;
        this.infoTimes = infoTimes;
        this.infoImageUris = infoImageUris;
    }
    public void update(String infoTitle, String infoDescription, String infoKey, String infoDate, String infoTime, String infoImageUri){

        infoTitleArrayList.add(infoTitle);
        infoDescriptionArrayList.add(infoDescription);
        infoKeys.add(infoKey);
        infoDates.add(infoDate);
        infoTimes.add(infoTime);
        infoImageUris.add(infoImageUri);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_info, parent, false);
        return new AdapterForInfoList.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int pos = position;

        holder.infoTitleTextView.setText(infoTitleArrayList.get(position));
        holder.infoDescriptionTextView.setText(infoDescriptionArrayList.get(position));
        holder.dateAndTimeInfo.setText(infoDates.get(position) + " at " + infoTimes.get(position));
        if(!infoImageUris.get(position).equals("No image selected"))
        {
            Picasso.get().load(infoImageUris.get(position)).into(holder.infoImageViewAdmin);
        }
        holder.deleteInfo.setOnClickListener(new View.OnClickListener() {
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

                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Information");

                        String uploadPDFID = infoKeys.get(pos);

                        databaseReference.child(uploadPDFID).removeValue();
                        infoTitleArrayList.remove(pos);
                        infoDescriptionArrayList.remove(pos);
                        //urls.remove(pos);
                        infoDates.remove(pos);
                        infoTimes.remove(pos);
                        infoKeys.remove(pos);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Deletion done successfully" , Toast.LENGTH_SHORT).show();

                        InformationBulletinActivity informationBulletinActivity = (InformationBulletinActivity)context;
                        informationBulletinActivity.reloadActivity();


                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return infoTitleArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView infoTitleTextView, infoDescriptionTextView;
        TextView dateAndTimeInfo;
        ImageView deleteInfo, infoImageViewAdmin;

        public ViewHolder(View itemView){
            super(itemView);
            infoTitleTextView = itemView.findViewById(R.id.infoTitleTextView);
            infoDescriptionTextView = itemView.findViewById(R.id.infoDescriptionTextView);
            dateAndTimeInfo = itemView.findViewById(R.id.dateAndTimeInfo);
            deleteInfo = itemView.findViewById(R.id.deleteInfo);
            infoImageViewAdmin = itemView.findViewById(R.id.infoImageViewAdmin);
        }
    }
}
