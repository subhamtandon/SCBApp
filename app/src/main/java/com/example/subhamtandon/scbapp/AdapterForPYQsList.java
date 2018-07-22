package com.example.subhamtandon.scbapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AdapterForPYQsList extends RecyclerView.Adapter<AdapterForPYQsList.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String > arrayList = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> ids = new ArrayList<>();

    String professional;
    String subject;

    public AdapterForPYQsList(RecyclerView recyclerView, Context context, ArrayList<String> arrayList, ArrayList<String> urls, ArrayList<String> ids, String professional, String subject) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.arrayList = arrayList;
        this.urls = urls;
        this.ids = ids;
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
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_pyqs, parent, false);
        return new AdapterForPYQsList.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int pos = position;

        holder.nameOfFile.setText(arrayList.get(position));
        /*

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Edit clicked", Toast.LENGTH_SHORT).show();
            }
        });
        */

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Delete clicked", Toast.LENGTH_SHORT).show();
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

                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("PYQs");

                        StorageReference imgRef = FirebaseStorage.getInstance().getReferenceFromUrl(urls.get(pos));
                        imgRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                String uploadPDFID = databaseReference.child(ids.get(pos)).getKey();

                                databaseReference.child(uploadPDFID).removeValue();
                                arrayList.remove(pos);
                                urls.remove(pos);
                                ids.remove(pos);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Deletion done successfully" , Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });

        holder.nameOfFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = recyclerView.getChildLayoutPosition(v);
                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urls.get(pos)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfFile;
        ImageView /*edit ,*/ delete;

        public ViewHolder(View itemView) {
            super(itemView);
            nameOfFile = itemView.findViewById(R.id.textViewPYQName);
            //edit = itemView.findViewById(R.id.editPYQ);
            delete = itemView.findViewById(R.id.deletePYQ);

            /*

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = recyclerView.getChildLayoutPosition(v);
                    Intent intent = new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                }
            });
            */
        }
    }

}
