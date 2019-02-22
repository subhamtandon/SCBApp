package com.company.ssDev.que9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class OfflinePDFViewerActivity extends AppCompatActivity {

    PDFView pdfViewOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_pdfviewer);

        pdfViewOffline = findViewById(R.id.pdfViewOffline);
        String fileName = getIntent().getStringExtra("filename");
        File file = getFileStreamPath(fileName);
        pdfViewOffline.fromFile(file)
                .load();
    }
}
