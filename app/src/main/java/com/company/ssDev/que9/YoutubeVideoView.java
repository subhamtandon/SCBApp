package com.company.ssDev.que9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

public class YoutubeVideoView extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Vector<YoutubeVideos> youtubeVideos = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video_view);
        String category = getIntent().getStringExtra("Category");
        Toast.makeText(this, category, Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recyclerViewYoutubeVideoListUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        /*
        youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KyJ71G2UxTQ\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/y8Rr39jKFKU\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8Hg1tqIwIfI\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uhQ7mh_o_cM\" frameborder=\"0\" allowfullscreen></iframe>") );
        */
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
                    ((AdapterForYoutubeVideos) recyclerView.getAdapter()).update(link);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        AdapterForYoutubeVideos adapterForYoutubeVideos = new AdapterForYoutubeVideos(recyclerView, YoutubeVideoView.this, new Vector<YoutubeVideos>());
        recyclerView.setAdapter(adapterForYoutubeVideos);

    }
}
