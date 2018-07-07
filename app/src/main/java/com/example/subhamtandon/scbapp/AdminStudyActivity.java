package com.example.subhamtandon.scbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AdminStudyActivity extends AppCompatActivity {



    AutoCompleteTextView ProfessionalsAutoCompleteTextView, SubjectsAutoCompleteTextView, TypeAutoCompleteTextView;

    private static final String[] Professionals = new String[]{
       "1st Professional","2nd Professional","3rd Professional","4th Professional"
    };

    private static final String[] Subjects = new String[]{
      "Anatomy","Physiology","BioChemistry","Mock Test"
    };

    private static final String[] Type = new String[]{
      "MCQs","Record","Practical","PYQs"
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

        ArrayAdapter<String> ProfessionalsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Professionals);
        ProfessionalsAutoCompleteTextView.setAdapter(ProfessionalsAdapter);

        ArrayAdapter<String> SubjectsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Subjects);
        SubjectsAutoCompleteTextView.setAdapter(SubjectsAdapter);

        ArrayAdapter<String> TypeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Type);
        TypeAutoCompleteTextView.setAdapter(TypeAdapter);

    }
}
