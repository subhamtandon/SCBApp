package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

public class AdminStudyActivity extends AppCompatActivity {



    AutoCompleteTextView ProfessionalsAutoCompleteTextView, SubjectsAutoCompleteTextView, TypeAutoCompleteTextView, ChaptersAutoCompleteTextView;
    ImageView image,image1,image2,image3;

    public void submitButton(View view){

        String whichProfessional = ProfessionalsAutoCompleteTextView.getText().toString();
        String whichSubject = SubjectsAutoCompleteTextView.getText().toString();
        String whichType = TypeAutoCompleteTextView.getText().toString();
        String whichChapter = null;

        String ready= "true";
        if (TextUtils.isEmpty(whichProfessional)){

            ProfessionalsAutoCompleteTextView.setError(getString(R.string.error_field_required));
            ready = "false";
        }
        if (TextUtils.isEmpty(whichSubject)){

            SubjectsAutoCompleteTextView.setError(getString(R.string.error_field_required));
            ready = "false";
        }
        if (TextUtils.isEmpty(whichType)){

            TypeAutoCompleteTextView.setError(getString(R.string.error_field_required));
            ready = "false";
        }

        if(whichType.equalsIgnoreCase("MCQs")){
            whichChapter = ChaptersAutoCompleteTextView.getText().toString();
            if (TextUtils.isEmpty(whichChapter)){

                ChaptersAutoCompleteTextView.setError(getString(R.string.error_field_required));
                ready = "false";
            }
        }

        if(ready.equalsIgnoreCase("true")){

            Intent next = new Intent(AdminStudyActivity.this, StudyAlteringActivity.class);
            next.putExtra("PROFESSIONAL", whichProfessional);
            next.putExtra("SUBJECT", whichSubject);
            next.putExtra("TYPE", whichType);
            next.putExtra("CHAPTER", whichChapter);
            startActivity(next);
        }

    }

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

        final ArrayAdapter<String> ChaptersAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Chapters);
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

        TypeAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TypeAutoCompleteTextView.getText().toString().equalsIgnoreCase("MCQs")){
                    ChaptersAutoCompleteTextView.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                }
                else
                {
                    ChaptersAutoCompleteTextView.setVisibility(View.GONE);
                    image3.setVisibility(View.GONE);
                }
            }
        });



    }
}
