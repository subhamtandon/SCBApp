package com.example.subhamtandon.scbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

public class AdminStudyActivity extends AppCompatActivity {



    AutoCompleteTextView ProfessionalsAutoCompleteTextView, SubjectsAutoCompleteTextView, TypeAutoCompleteTextView, ChaptersAutoCompleteTextView;
    ImageView image,image1,image2,image3;

    private static final String[] Professionals = new String[]{
       "1st Professional","2nd Professional","3rd Professional","4th Professional"
    };

    private static final String[] Subjects = new String[]{
      "Anatomy","Physiology","BioChemistry","Mock Test"
    };

    private static final String[] Type = new String[]{
      "MCQs","Record","Practical","PYQs"
    };

    private static final String[] Chapters = new String[]{
      "Chapter 1","Chapter 2","Chapter 3","Chapter 4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_study);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Study");

        ProfessionalsAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.ProfessionalsAutoCompleteTextView);
        SubjectsAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.SubjectsAutoCompleteTextView);
        TypeAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.TypeAutoCompleteTextView);
        ChaptersAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.ChaptersAutoCompleteTextView);

        image = (ImageView) findViewById(R.id.image);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);

        ArrayAdapter<String> ProfessionalsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Professionals);
        ProfessionalsAutoCompleteTextView.setAdapter(ProfessionalsAdapter);

        ArrayAdapter<String> SubjectsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Subjects);
        SubjectsAutoCompleteTextView.setAdapter(SubjectsAdapter);

        ArrayAdapter<String> TypeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Type);
        TypeAutoCompleteTextView.setAdapter(TypeAdapter);

        ArrayAdapter<String> ChaptersAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Type);
        ChaptersAutoCompleteTextView.setAdapter(ChaptersAdapter);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfessionalsAutoCompleteTextView.showDropDown();
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubjectsAutoCompleteTextView.showDropDown();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypeAutoCompleteTextView.showDropDown();
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChaptersAutoCompleteTextView.showDropDown();
            }
        });

        if (TypeAutoCompleteTextView.getText().toString().equalsIgnoreCase("MCQs")){
            ChaptersAutoCompleteTextView.setVisibility(View.VISIBLE);
            image3.setVisibility(View.VISIBLE);
        }

    }
}
