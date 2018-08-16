package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class AdminStudyActivity extends AppCompatActivity {



    AutoCompleteTextView ProfessionalsAutoCompleteTextView, SubjectsAutoCompleteTextView, TypeAutoCompleteTextView, ChaptersAutoCompleteTextView, modeAutoCompleteTextView;
    ImageView image,image1,image2,image3,image4;
    //Spinner spinnerProfessionals, spinnerSubjects, spinnerType, spinnerChapters, spinnerMode;

    ArrayAdapter<String> SubjectsProfFirstAdapter, SubjectsProfSecondAdapter, SubjectsProfThird1Adapter, SubjectsProfThird2Adapter, ChaptersAnatomyAdapter;

    String whichProfessional, whichSubject, whichType, whichChapter, whichMode;

    public void submitButton(View view){

        whichProfessional = ProfessionalsAutoCompleteTextView.getText().toString();
        whichSubject = SubjectsAutoCompleteTextView.getText().toString();
        whichType = TypeAutoCompleteTextView.getText().toString();
        whichChapter = null;
        whichMode = null;

        String ready= "true";

        /*for (int i = 0 ; i < Professionals.length - 1 ; i++){

            if (!whichProfessional.equalsIgnoreCase(Professionals[i])){
                ProfessionalsAutoCompleteTextView.setError("Select from dropdown");
                ready = "false";
            }
        }*/
        if (TextUtils.isEmpty(whichProfessional)){

            ProfessionalsAutoCompleteTextView.setError(getString(R.string.error_field_required));
            ready = "false";
        }

        if (TextUtils.isEmpty(whichSubject)){

            SubjectsAutoCompleteTextView.setError(getString(R.string.error_field_required));
            ready = "false";
        }

        /*for (int i = 0 ; i < Type.length - 1 ; i++){

            if (!whichType.equalsIgnoreCase(Type[i])){
                TypeAutoCompleteTextView.setError("Select from dropdown");
                ready = "false";
            }
        }*/
        if (TextUtils.isEmpty(whichType)){

            TypeAutoCompleteTextView.setError(getString(R.string.error_field_required));
            ready = "false";
        }

        if(whichType.equalsIgnoreCase("MCQs")){
            whichChapter = ChaptersAutoCompleteTextView.getText().toString();
            whichMode = modeAutoCompleteTextView.getText().toString();

            /*for (int i = 0 ; i < Chapters.length - 1 ; i++){

                if (!whichChapter.equalsIgnoreCase(Chapters[i])){
                    ChaptersAutoCompleteTextView.setError("Select from dropdown");
                    ready = "false";
                }
            }*/
            if (TextUtils.isEmpty(whichChapter)){

                ChaptersAutoCompleteTextView.setError(getString(R.string.error_field_required));
                ready = "false";
            }

            /*for (int i = 0 ; i < Modes.length - 1 ; i++){

                if (!whichMode.equalsIgnoreCase(Modes[i])){
                    modeAutoCompleteTextView.setError("Select from dropdown");
                    ready = "false";
                }
            }*/
            if (TextUtils.isEmpty(whichMode)){

                modeAutoCompleteTextView.setError(getString(R.string.error_field_required));
                ready = "false";
            }
        }

        if(ready.equalsIgnoreCase("true")){

            if(whichType.equalsIgnoreCase("MCQs")){
                Intent next = new Intent(AdminStudyActivity.this, ListOfSetsActivity.class);
                next.putExtra("PROFESSIONAL", whichProfessional);
                next.putExtra("SUBJECT", whichSubject);
                next.putExtra("CHAPTER", whichChapter);
                next.putExtra("MODE",whichMode);
                startActivity(next);
            }
            else if(whichType.equalsIgnoreCase("Record")){
                Intent next = new Intent(AdminStudyActivity.this, ListOfRecordsActivity.class);
                next.putExtra("PROFESSIONAL", whichProfessional);
                next.putExtra("SUBJECT", whichSubject);
                startActivity(next);
            }
            else if(whichType.equalsIgnoreCase("Practical")){
                Intent next = new Intent(AdminStudyActivity.this, ListOfPracticalsActivity.class);
                next.putExtra("PROFESSIONAL", whichProfessional);
                next.putExtra("SUBJECT", whichSubject);
                startActivity(next);
            }
            else if(whichType.equalsIgnoreCase("PYQs")){
                Intent next = new Intent(AdminStudyActivity.this, ListOfPYQsActivity.class);
                next.putExtra("PROFESSIONAL", whichProfessional);
                next.putExtra("SUBJECT", whichSubject);
                startActivity(next);
            }
        }
    }

    private static final String[] Professionals = new String[]{
            "First Professional","Second Professional","Third Professional Part-1","Third Professional Part-2"
    };

    private static final String[] SubjectsProfFirst = new String[]{
            "Anatomy","Physiology","BioChemistry","Mock Test"
    };

    private static final String[] SubjectsProfSecond = new String[]{
            "Pathology","Pharmacology","Microbiology","FMT"
    };

    private static final String[] SubjectsProfThird1 = new String[]{
            "SPM","ENT","Optha"
    };

    private static final String[] SubjectsProfThird2 = new String[]{
            "Medicine","Surgery","Pediatrics","Orthopedics","Skin & VD","Anaesthesiology","Radiology","O & G"
    };

    private static final String[] Type = new String[]{
            "MCQs","Record","Practical","PYQs"
    };

    private static final String[] Chapters = new String[]{
            "Chapter 01","Chapter 02","Chapter 03","Chapter 04"
    };

    private static final String[] Modes = new String[]{
            "Basic","Advanced"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_study);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Study");
        }

        ProfessionalsAutoCompleteTextView = findViewById(R.id.professionalsAutoCompleteTextView);
        SubjectsAutoCompleteTextView = findViewById(R.id.subjectsAutoCompleteTextView);
        TypeAutoCompleteTextView = findViewById(R.id.typeAutoCompleteTextView);
        ChaptersAutoCompleteTextView = findViewById(R.id.chaptersAutoCompleteTextView);
        modeAutoCompleteTextView = findViewById(R.id.modeAutoCompleteTextView);

        image = (ImageView) findViewById(R.id.image);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);

        //spinnerProfessionals = findViewById(R.id.spinnerProfessionals);
        //spinnerSubjects = findViewById(R.id.spinnerSubjects);
        //spinnerType = findViewById(R.id.spinnerType);
        //spinnerChapters = findViewById(R.id.spinnerChapters);
        //spinnerMode = findViewById(R.id.spinnerMode);

        ArrayAdapter<String> ProfessionalsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Professionals);
        ProfessionalsAutoCompleteTextView.setAdapter(ProfessionalsAdapter);

        SubjectsProfFirstAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,SubjectsProfFirst);

        SubjectsProfSecondAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,SubjectsProfSecond);

        SubjectsProfThird1Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,SubjectsProfThird1);

        SubjectsProfThird2Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,SubjectsProfThird2);

        ArrayAdapter<String> TypeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Type);
        TypeAutoCompleteTextView.setAdapter(TypeAdapter);

        ChaptersAnatomyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.anatomyChapters));

        final ArrayAdapter<String> ChaptersAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Chapters);
        ChaptersAutoCompleteTextView.setAdapter(ChaptersAdapter);

        ArrayAdapter<String> ModeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Modes);
        modeAutoCompleteTextView.setAdapter(ModeAdapter);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfessionalsAutoCompleteTextView.showDropDown();
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichProfessional = ProfessionalsAutoCompleteTextView.getText().toString();
                //whichSubject = SubjectsAutoCompleteTextView.getText().toString();

                if (whichProfessional.equalsIgnoreCase("First Professional")){
                    SubjectsAutoCompleteTextView.setAdapter(SubjectsProfFirstAdapter);
                }
                if (whichProfessional.equalsIgnoreCase("Second Professional")){
                    SubjectsAutoCompleteTextView.setAdapter(SubjectsProfSecondAdapter);
                }
                if (whichProfessional.equalsIgnoreCase("Third Professional Part-1")){
                    SubjectsAutoCompleteTextView.setAdapter(SubjectsProfThird1Adapter);
                }
                if (whichProfessional.equalsIgnoreCase("Third Professional Part-2")){
                    SubjectsAutoCompleteTextView.setAdapter(SubjectsProfThird2Adapter);
                }
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
                whichSubject = SubjectsAutoCompleteTextView.getText().toString();

                if (whichSubject.equalsIgnoreCase("Anatomy")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersAnatomyAdapter);
                }

                ChaptersAutoCompleteTextView.showDropDown();
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeAutoCompleteTextView.showDropDown();
            }
        });

        TypeAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TypeAutoCompleteTextView.getText().toString().equalsIgnoreCase("MCQs")){
                    ChaptersAutoCompleteTextView.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                    modeAutoCompleteTextView.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                }
                else
                {
                    ChaptersAutoCompleteTextView.setVisibility(View.GONE);
                    image3.setVisibility(View.GONE);
                    modeAutoCompleteTextView.setVisibility(View.GONE);
                    image4.setVisibility(View.GONE);
                }
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
