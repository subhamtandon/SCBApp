package com.company.ssDev.que9;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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

    public void update(String pictureUrl){
        picturesUrls.add(pictureUrl);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public AdapterForMedicalRelatedPictures.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_medical_related_pictures, parent, false);
        return new AdapterForMedicalRelatedPictures.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //holder.picImage.setImageURI(Uri.parse(picturesUrls.get(position)));
        Picasso.get().load(picturesUrls.get(position)).into(holder.picImage);
        holder.picImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: insert image dialog
                Toast.makeText(context, "coming soon ...", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View mView = LayoutInflater.from(context).inflate(R.layout.image_dialog, null);
                ImageView image = mView.findViewById(R.id.medicalRelatedPicImage);
                Picasso.get().load(picturesUrls.get(position)).into(image);
                builder.setCancelable(false)
                        .setView(mView)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
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
