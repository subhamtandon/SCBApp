package com.company.ssDev.que9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

public class AdminStudyActivity extends AppCompatActivity {

    AutoCompleteTextView ProfessionalsAutoCompleteTextView, SubjectsAutoCompleteTextView, TypeAutoCompleteTextView, ChaptersAutoCompleteTextView;
    ImageView image,image1,image2,image3;
    //Spinner spinnerProfessionals, spinnerSubjects, spinnerType, spinnerChapters, spinnerMode;

    ArrayAdapter<String> SubjectsProfFirstAdapter, SubjectsProfSecondAdapter, SubjectsProfThird1Adapter, SubjectsProfThird2Adapter,
            ChaptersAnatomyAdapter, ChaptersPhysiologyAdapter, ChaptersBiochemistryAdapter, ChaptersPathologyAdapter, ChaptersPharmacologyAdapter,
            ChaptersMicrobiologyAdapter, ChaptersFMTAdapter, ChaptersSPMAdapter, ChaptersENTAdapter, ChaptersOpthaAdapter, ChaptersMedicineAdapter,
            ChaptersSurgeryAdapter, ChaptersPediatricsAdapter, ChaptersOrthopedicsAdapter, ChaptersSkinVdAdapter, ChaptersAnaesthesiologyAdapter,
            ChaptersRadiologyAdapter, ChaptersOGAdapter;

    String whichProfessional, whichSubject, whichType, whichChapter;

    public void submitButton(View view){

        whichProfessional = ProfessionalsAutoCompleteTextView.getText().toString();
        whichSubject = SubjectsAutoCompleteTextView.getText().toString();
        whichType = TypeAutoCompleteTextView.getText().toString();
        whichChapter = null;

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
            /*if (TextUtils.isEmpty(whichMode)){

                modeAutoCompleteTextView.setError(getString(R.string.error_field_required));
                ready = "false";
            }*/
        }

        if(ready.equalsIgnoreCase("true")){

            if(whichType.equalsIgnoreCase("MCQs")){
                Intent next = new Intent(AdminStudyActivity.this, ListOfSetsActivity.class);
                next.putExtra("PROFESSIONAL", whichProfessional);
                next.putExtra("SUBJECT", whichSubject);
                next.putExtra("CHAPTER", whichChapter);
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

        image = (ImageView) findViewById(R.id.image);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        //image4 = (ImageView) findViewById(R.id.image4);

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
        ChaptersPhysiologyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.physiologyChapters));
        ChaptersBiochemistryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.bioChemistryChapters));
        ChaptersPathologyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.pathologyChapters));
        ChaptersPharmacologyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.pharmacologyChapters));
        ChaptersMicrobiologyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.microbiologyChapters));
        ChaptersFMTAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.fmtChapters));
        ChaptersSPMAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spmChapters));
        ChaptersENTAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.entChapters));
        ChaptersOpthaAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.opthaChapters));
        ChaptersMedicineAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.medicineChapters));
        ChaptersSurgeryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.surgeryChapters));
        ChaptersPediatricsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.pediatricsChapters));
        ChaptersOrthopedicsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.orthopedicsChapters));
        ChaptersSkinVdAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.skinVdChapters));
        ChaptersAnaesthesiologyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.anaesthesiologyChapters));
        ChaptersRadiologyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.radiologyChapters));
        ChaptersOGAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.ogChapters));

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

                if (whichSubject.equalsIgnoreCase("Physiology")) {
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersPhysiologyAdapter);
                }

                if (whichSubject.equalsIgnoreCase("BioChemistry")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersBiochemistryAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Pathology")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersPathologyAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Pharmacology")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersPharmacologyAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Microbiology")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersMicrobiologyAdapter);
                }

                if (whichSubject.equalsIgnoreCase("FMT")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersFMTAdapter);
                }

                if (whichSubject.equalsIgnoreCase("SPM")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersSPMAdapter);
                }

                if (whichSubject.equalsIgnoreCase("ENT")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersENTAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Optha")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersOpthaAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Medicine")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersMedicineAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Surgery")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersSurgeryAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Pediatrics")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersPediatricsAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Orthopedics")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersOrthopedicsAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Skin & VD")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersSkinVdAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Anaesthesiology")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersAnaesthesiologyAdapter);
                }

                if (whichSubject.equalsIgnoreCase("Radiology")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersRadiologyAdapter);
                }

                if (whichSubject.equalsIgnoreCase("O & G")){
                    ChaptersAutoCompleteTextView.setAdapter(ChaptersOGAdapter);
                }

                ChaptersAutoCompleteTextView.showDropDown();
            }
        });

        /*image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeAutoCompleteTextView.showDropDown();
            }
        });*/

        TypeAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TypeAutoCompleteTextView.getText().toString().equalsIgnoreCase("MCQs")){
                    ChaptersAutoCompleteTextView.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                    //modeAutoCompleteTextView.setVisibility(View.VISIBLE);
                    //image4.setVisibility(View.VISIBLE);
                }
                else
                {
                    ChaptersAutoCompleteTextView.setVisibility(View.GONE);
                    image3.setVisibility(View.GONE);
                    //modeAutoCompleteTextView.setVisibility(View.GONE);
                    //image4.setVisibility(View.GONE);
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
