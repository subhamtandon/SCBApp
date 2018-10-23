package com.example.subhamtandon.scbapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterForListOfPPTsAdmin extends RecyclerView.Adapter<AdapterForListOfPPTsAdmin.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> picturesUrls= new ArrayList<>();
    ArrayList<String> pdfpptUrls, pptNames, ids ;

    public AdapterForListOfPPTsAdmin(RecyclerView recyclerView, Context context, ArrayList<String> pptNames, ArrayList<String> picturesUrls, ArrayList<String> pdfpptUrls, ArrayList<String> ids) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.pptNames = pptNames;
        this.picturesUrls = picturesUrls;
        this.pdfpptUrls = pdfpptUrls;
        this.ids = ids;
    }

    public void update(String pptName, String pictureUrl, String pdfpptUrl, String id){
        pptNames.add(pptName);
        picturesUrls.add(pictureUrl);
        pdfpptUrls.add(pdfpptUrl);
        ids.add(id);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AdapterForListOfPPTsAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_ppts_admin, parent, false);
        return new AdapterForListOfPPTsAdmin.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForListOfPPTsAdmin.ViewHolder holder, final int position) {

        Picasso.get().load(picturesUrls.get(position)).placeholder(R.mipmap.ic_launcher).into(holder.picImage);
        holder.pptName.setText(pptNames.get(position));
        holder.deletePPT.setOnClickListener(new View.OnClickListener() {
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

                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("PPTPDFs");

                        StorageReference imgRef = FirebaseStorage.getInstance().getReferenceFromUrl(picturesUrls.get(position));
                        imgRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                StorageReference pdfRef = FirebaseStorage.getInstance().getReferenceFromUrl(pdfpptUrls.get(position));
                                pdfRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        String uploadPDFPPTID = databaseReference.child(ids.get(position)).getKey();

                                        databaseReference.child(uploadPDFPPTID).removeValue();
                                        pptNames.remove(position);
                                        pdfpptUrls.remove(position);
                                        picturesUrls.remove(position);
                                        notifyDataSetChanged();
                                        Toast.makeText(context, "Deletion done successfully" , Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "inner error: " + e, Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "error: " + e, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
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
        ImageView picImage, deletePPT;
        TextView pptName;
        public ViewHolder(View itemView) {
            super(itemView);
            picImage = itemView.findViewById(R.id.imageViewPPTThumbnail);
            pptName = itemView.findViewById(R.id.textViewPPTName);
            deletePPT = itemView.findViewById(R.id.deletePPT);
        }
    }
}
