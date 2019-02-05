package com.company.ssDev.que9;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.barteksc.pdfviewer.PDFView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MakersDeskFragment extends Fragment {

    PDFView makersDeskPDF;
    LinearLayout linearLayout;
    Button button1, button2, button3, button4, makerDeskButton;
    WebView webViewScbcloud;
    public MakersDeskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((UserProfile) getActivity())
                .setActionBarTitle("From Maker's Desk");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_makers_desk, container, false);
        makersDeskPDF = view.findViewById(R.id.makersDeskPDF);
        makersDeskPDF.fromAsset("from_makers_desk.pdf").load();

        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        webViewScbcloud = view.findViewById(R.id.webViewScbcloud);
        linearLayout = view.findViewById(R.id.linearLayoutMakersDesk);
        makerDeskButton = view.findViewById(R.id.makersDeskButton);

        makerDeskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                makerDeskButton.setVisibility(View.INVISIBLE);
                makersDeskPDF.fromAsset("from_makers_desk.pdf").load();
                makersDeskPDF.setVisibility(View.VISIBLE);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.INVISIBLE);
                makerDeskButton.setVisibility(View.VISIBLE);
                makersDeskPDF.fromAsset("su_2017-18.pdf").load();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.INVISIBLE);
                makerDeskButton.setVisibility(View.VISIBLE);
                makersDeskPDF.fromAsset("su_2018-19.pdf").load();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.INVISIBLE);
                makerDeskButton.setVisibility(View.VISIBLE);
                makersDeskPDF.fromAsset("contributor.pdf").load();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.INVISIBLE);
                makerDeskButton.setVisibility(View.VISIBLE);
                webViewScbcloud.setVisibility(View.VISIBLE);
                webViewScbcloud.setWebViewClient(new WebViewClient());
                webViewScbcloud.loadUrl("https://scbcloud.blogspot.com/?m=1");
                WebSettings webSettings = webViewScbcloud.getSettings();
                makersDeskPDF.setVisibility(View.INVISIBLE);
                webSettings.setJavaScriptEnabled(true);
            }
        });
        return view;
    }

}
