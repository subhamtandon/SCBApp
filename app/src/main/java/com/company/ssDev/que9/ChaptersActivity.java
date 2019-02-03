package com.company.ssDev.que9;

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

        String[] anatomyChaptersName = getResources().getStringArray(R.array.anatomyChapters);
        String[] physiologyChaptersName = getResources().getStringArray(R.array.physiologyChapters);
        String[] bioChemistryChaptersName = getResources().getStringArray(R.array.bioChemistryChapters);
        String[] bioChemistryDescription = getResources().getStringArray(R.array.bioChemistryDescription);
        String[] pathologyChaptersName = getResources().getStringArray(R.array.pathologyChapters);
        String[] pharmacologyChaptersName = getResources().getStringArray(R.array.pharmacologyChapters);
        String[] pharmacologyDescription = getResources().getStringArray(R.array.pharmacologyDescription);
        String[] microbiologyChaptersName = getResources().getStringArray(R.array.microbiologyChapters);
        String[] microbiologyDescription = getResources().getStringArray(R.array.microbiologyDescription);
        String[] fmtChaptersName = getResources().getStringArray(R.array.fmtChapters);
        String[] fmtDescription = getResources().getStringArray(R.array.fmtDescription);
        String[] spmChaptersName = getResources().getStringArray(R.array.spmChapters);
        String[] entChaptersName = getResources().getStringArray(R.array.entChapters);
        String[] opthaChaptersName = getResources().getStringArray(R.array.opthaChapters);
        String[] medicineChaptersName = getResources().getStringArray(R.array.medicineChapters);
        String[] surgeryChaptersName = getResources().getStringArray(R.array.surgeryChapters);
        String[] pediatricsChaptersName = getResources().getStringArray(R.array.pediatricsChapters);
        String[] orthopedicsChaptersName = getResources().getStringArray(R.array.orthopedicsChapters);
        String[] skinVdChaptersName = getResources().getStringArray(R.array.skinVdChapters);
        String[] anaesthesiologyChaptersName = getResources().getStringArray(R.array.anaesthesiologyChapters);
        String[] radiologyChaptersName = getResources().getStringArray(R.array.radiologyChapters);
        String[] ogChaptersName = getResources().getStringArray(R.array.ogChapters);

        listItemChapters = new ArrayList<>();

        if (subject.equalsIgnoreCase("Anatomy")){
            for(int i =0; i<anatomyChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(anatomyChaptersName[i], "");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Physiology")){
            for(int i =0; i<physiologyChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(physiologyChaptersName[i], "");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("BioChemistry")){
            for(int i =0; i<bioChemistryChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(bioChemistryChaptersName[i], bioChemistryDescription[i]);
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Pathology")){
            for(int i =0; i<pathologyChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(pathologyChaptersName[i], "");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Pharmacology")){
            for(int i =0; i<pharmacologyChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(pharmacologyChaptersName[i], pharmacologyDescription[i]);
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Microbiology")){
            for(int i =0; i<microbiologyChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(microbiologyChaptersName[i], microbiologyDescription[i]);
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("FMT")){
            for(int i =0; i<fmtChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(fmtChaptersName[i], fmtDescription[i]);
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("SPM")){
            for(int i =0; i<spmChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(spmChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("ENT")){
            for(int i =0; i<entChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(entChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Optha")){
            for(int i =0; i<opthaChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(opthaChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Medicine")){
            for(int i =0; i<medicineChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(medicineChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Surgery")){
            for(int i =0; i<surgeryChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(surgeryChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Pediatrics")){
            for(int i =0; i<pediatricsChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(pediatricsChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Orthopedics")){
            for(int i =0; i<orthopedicsChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(orthopedicsChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Skin & VD")){
            for(int i =0; i<skinVdChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(skinVdChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Anaesthesiology")){
            for(int i =0; i<anaesthesiologyChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(anaesthesiologyChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("Radiology")){
            for(int i =0; i<radiologyChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(radiologyChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
        }

        if (subject.equalsIgnoreCase("O & G")){
            for(int i =0; i<ogChaptersName.length; i++){
                ListItemChapters listItemChapter = new ListItemChapters(ogChaptersName[i], "Description is coming soon");
                listItemChapters.add(listItemChapter);
            }
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
