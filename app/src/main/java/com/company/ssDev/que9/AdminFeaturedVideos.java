package com.company.ssDev.que9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class AdminFeaturedVideos extends AppCompatActivity {

    Spinner spinnerCategories;
    Button categorySubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_featured_videos);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Featured Videos");
        }

        spinnerCategories = findViewById(R.id.spinnerCategories);
        categorySubmitButton = findViewById(R.id.categorySubmitButton);

        categorySubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminFeaturedVideos.this, AdminYoutubeVideos.class);
                intent.putExtra("CATEGORY", spinnerCategories.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
