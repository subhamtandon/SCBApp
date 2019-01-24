package com.example.subhamtandon.scbapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SCBMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button schematicMapButton;
    ImageView schematicMap;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scbmap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        schematicMapButton = findViewById(R.id.schematicMapButton);
        schematicMap = findViewById(R.id.schematicMap);

        schematicMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count ++;
                if(count%2==1){
                    schematicMap.setVisibility(View.VISIBLE);
                    schematicMapButton.setText("GOOGLE MAP");
                }
                else {
                    schematicMap.setVisibility(View.INVISIBLE);
                    schematicMapButton.setText("SCHEMATIC MAP");
                }

            }
        });


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng SCBCollege = new LatLng(20.4721281, 85.8893386);
        mMap.addMarker(new MarkerOptions().position(SCBCollege).title("Marker in SCB College"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SCBCollege,16));
    }
}
