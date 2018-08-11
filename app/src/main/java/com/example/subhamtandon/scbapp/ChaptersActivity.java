package com.example.subhamtandon.scbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChaptersActivity extends AppCompatActivity {

    RecyclerView recyclerViewChapters;
    RecyclerView.Adapter adapterChapters;
    List<ListItemChapters> listItemChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Chapters");
        }

        final String professional= getIntent().getStringExtra("PROFESSIONAL");
        final String subject = getIntent().getStringExtra("SUBJECT");

        Toast.makeText(ChaptersActivity.this, professional + ":" + subject, Toast.LENGTH_SHORT).show();

        recyclerViewChapters =findViewById(R.id.recyclerViewChapters);
        recyclerViewChapters.setHasFixedSize(true);
        recyclerViewChapters.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        listItemChapters = new ArrayList<>();

        for(int i =0; i<=10; i++){
            ListItemChapters listItemChapter = new ListItemChapters("Chapter 0"+ (i+1), "Description is coming soon");
            listItemChapters.add(listItemChapter);
        }

        adapterChapters = new AdapterForBinding(listItemChapters, ChaptersActivity.this, professional, subject);

        recyclerViewChapters.setAdapter(adapterChapters);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
