package com.example.subhamtandon.scbapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Vector;

public class AdminYoutubeVideos extends AppCompatActivity {

    RecyclerView recyclerViewYoutubeVideoListAdmin;
    FloatingActionButton addVideo;
    DatabaseReference databaseReference;
    Vector<YoutubeVideos> youtubeVideos = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_youtube_videos);

        String category = getIntent().getStringExtra("CATEGORY");
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();

        recyclerViewYoutubeVideoListAdmin = findViewById(R.id.recyclerViewYoutubeVideoListAdmin);
        recyclerViewYoutubeVideoListAdmin.setHasFixedSize(true);
        recyclerViewYoutubeVideoListAdmin.setLayoutManager( new LinearLayoutManager(this));
        addVideo = findViewById(R.id.addVideo);
        databaseReference = FirebaseDatabase.getInstance().getReference("App").child("Featured Videos").child(category);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String uniqueId = ds.getKey();
                    //if (ds.child(uniqueId).getKey().toString().equalsIgnoreCase("Title")){
                     //   String title =
                    //}
                    //String title = ds.child(uniqueId).child("Title").getValue(String.class);
                    String link = ds.child("Link").getValue(String.class);
                    Log.d("Link", link + "");
                    ((AdapterForAdminYoutubeVideos) recyclerViewYoutubeVideoListAdmin.getAdapter()).update(link);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerViewYoutubeVideoListAdmin.setLayoutManager(new LinearLayoutManager(this));
        AdapterForAdminYoutubeVideos adapterForAdminYoutubeVideos = new AdapterForAdminYoutubeVideos(recyclerViewYoutubeVideoListAdmin, AdminYoutubeVideos.this, new Vector<YoutubeVideos>());
        recyclerViewYoutubeVideoListAdmin.setAdapter(adapterForAdminYoutubeVideos);

        addVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminYoutubeVideos.this);
                View mView = getLayoutInflater().inflate(R.layout.adding_video_details, null);

                final TextInputEditText videoTitle = mView.findViewById(R.id.videoTitle);
                final TextInputEditText videoLink = mView.findViewById(R.id.videoLink);
                Button videoDone = mView.findViewById(R.id.videoDone);

                mBuilder.setCancelable(false);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                videoDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = videoTitle.getText().toString();
                        String link = videoLink.getText().toString();
                        String ready = "true";
                        if (TextUtils.isEmpty(title)){
                            videoTitle.setError(getString(R.string.error_field_required));
                            ready = "false";
                        }
                        if (TextUtils.isEmpty(link)){
                            videoLink.setError(getString(R.string.error_field_required));
                            ready = "false";
                        }
                        if (ready.equals("true")){
                            final String id = databaseReference.push().getKey();
                            databaseReference.child(id).child("Title").setValue(title);
                            databaseReference.child(id).child("Link").setValue(link);
                            dialog.dismiss();
                        }

                    }
                });
            }
        });
    }
}
