package com.company.ssDev.que9;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth;

    TextView textViewUserName, textViewUserEmail;

    DatabaseReference databaseReference;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        if(!isConnected(UserProfile.this)) buildDialog(UserProfile.this).show();
        else {
            Toast.makeText(UserProfile.this,"Welcome", Toast.LENGTH_SHORT).show();
            //setContentView(R.layout.activity_main);
        }
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(UserProfile.this);
        View header=navigationView.getHeaderView(0);

        Menu SCBSection = navigationView.getMenu();
        SCBSection.findItem(R.id.nav_submitQuestion).setVisible(false);

        textViewUserName = (TextView)header.findViewById(R.id.textViewUserName);
        textViewUserEmail = (TextView)header.findViewById(R.id.textViewUserEmail);

        firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserName.setText(user.getDisplayName());
        textViewUserEmail.setText(user.getEmail());

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("collegeName");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    String collegeName = dataSnapshot.getValue(String.class);

                    Toast.makeText(UserProfile.this, collegeName, Toast.LENGTH_SHORT).show();

                    if (!collegeName.equalsIgnoreCase("SCB Medical College, Cuttack")){
                        Menu SCBSection = navigationView.getMenu();
                        SCBSection.findItem(R.id.nav_scbSection).setVisible(false);
                        Menu infoBulletin = navigationView.getMenu();
                        infoBulletin.findItem(R.id.nav_scbInfoBulletin).setVisible(false);
                        Menu docInfo = navigationView.getMenu();
                        docInfo.findItem(R.id.nav_scbDoctorsInfo).setVisible(false);
                        Menu scbMap = navigationView.getMenu();
                        scbMap.findItem(R.id.nav_scbMap).setVisible(false);
                        Menu aboutSCB = navigationView.getMenu();
                        aboutSCB.findItem(R.id.nav_scbAboutSCB).setVisible(false);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserProfile.this, databaseError + "", Toast.LENGTH_SHORT).show();
            }
        });
        if(!UserProfile.this.isFinishing())
        {
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new HomeFragment());
            ft.commit();
        }

        final String alteringValue;

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if(dataSnapshot1.getKey().equalsIgnoreCase("Altering")){
                        String can = dataSnapshot1.getValue(String.class);
                        if (can.equalsIgnoreCase("false")){




                            //Menu nav_Menu = navigationView.getMenu();
                            //nav_Menu.findItem(R.id.nav_scbAboutSCB).setVisible(false);


                        }
                        else{

//                            Toast.makeText(UserProfile.this, "You can not access this time :<", Toast.LENGTH_SHORT).show();
//
//
//                            final AlertDialog.Builder dialog = new AlertDialog.Builder(UserProfile.this).setTitle("No access").setMessage("Maintenance is going on. Try again later...:)");
//                            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int whichButton) {
////                                    onStop();
//                                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                                    homeIntent.addCategory( Intent.CATEGORY_HOME );
//                                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(homeIntent);
//                                }
//                            });
//                            final AlertDialog alert = dialog.create();
//                            if(!UserProfile.this.isFinishing())
//                            {
//                                //show dialog
//                                alert.show();
//                            }
//
//
//// Hide after some seconds
//                            final Handler handler  = new Handler();
//                            final Runnable runnable = new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (alert.isShowing()) {
//                                        alert.dismiss();
//                                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                                        homeIntent.addCategory( Intent.CATEGORY_HOME );
//                                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                        startActivity(homeIntent);
//                                    }
//                                }
//                            };
//
//                            alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                                @Override
//                                public void onDismiss(DialogInterface dialog) {
//                                    handler.removeCallbacks(runnable);
//                                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                                    homeIntent.addCategory( Intent.CATEGORY_HOME );
//                                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(homeIntent);
//                                }
//                            });
//
//                            handler.postDelayed(runnable, 10000);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
//        databaseReference1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                    if(dataSnapshot1.getKey().equalsIgnoreCase("Altering")){
//                        String can = dataSnapshot1.getValue(String.class);
//                        if (can.equalsIgnoreCase("false")){
//
//
//                            final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//                            navigationView.setNavigationItemSelectedListener(UserProfile.this);
//                            View header=navigationView.getHeaderView(0);
//
//                            //Menu nav_Menu = navigationView.getMenu();
//                            //nav_Menu.findItem(R.id.nav_scbAboutSCB).setVisible(false);
//
//                            Menu SCBSection = navigationView.getMenu();
//                            SCBSection.findItem(R.id.nav_submitQuestion).setVisible(false);
//
//                            textViewUserName = (TextView)header.findViewById(R.id.textViewUserName);
//                            textViewUserEmail = (TextView)header.findViewById(R.id.textViewUserEmail);
//
//                            firebaseAuth = FirebaseAuth.getInstance();
//
//                            final FirebaseUser user = firebaseAuth.getCurrentUser();
//
//                            textViewUserName.setText(user.getDisplayName());
//                            textViewUserEmail.setText(user.getEmail());
//
//                            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .child("collegeName");
//
//                            databaseReference.addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    if (dataSnapshot != null){
//                                        String collegeName = dataSnapshot.getValue(String.class);
//
//                                        Toast.makeText(UserProfile.this, collegeName, Toast.LENGTH_SHORT).show();
//
//                                        if (!collegeName.equalsIgnoreCase("SCB Medical College, Cuttack")){
//                                            Menu SCBSection = navigationView.getMenu();
//                                            SCBSection.findItem(R.id.nav_scbSection).setVisible(false);
//                                            Menu infoBulletin = navigationView.getMenu();
//                                            infoBulletin.findItem(R.id.nav_scbInfoBulletin).setVisible(false);
//                                            Menu docInfo = navigationView.getMenu();
//                                            docInfo.findItem(R.id.nav_scbDoctorsInfo).setVisible(false);
//                                            Menu scbMap = navigationView.getMenu();
//                                            scbMap.findItem(R.id.nav_scbMap).setVisible(false);
//                                            Menu aboutSCB = navigationView.getMenu();
//                                            aboutSCB.findItem(R.id.nav_scbAboutSCB).setVisible(false);
//                                        }
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//                                    Toast.makeText(UserProfile.this, databaseError + "", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//
//                            if(!UserProfile.this.isFinishing())
//                            {
//                                FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
//                                ft.replace(R.id.flMain, new HomeFragment());
//                                ft.commit();
//                            }
//                        }
//                        else{
//
//                            Toast.makeText(UserProfile.this, "You can not access this time :<", Toast.LENGTH_SHORT).show();
//                            final AlertDialog.Builder dialog = new AlertDialog.Builder(UserProfile.this).setTitle("No access").setMessage("Maintenance is going on. Try again later...:)");
//                            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int whichButton) {
////                                    onStop();
//                                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                                    homeIntent.addCategory( Intent.CATEGORY_HOME );
//                                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(homeIntent);
//                                }
//                            });
//                            final AlertDialog alert = dialog.create();
//                            if(!UserProfile.this.isFinishing())
//                            {
//                                //show dialog
//                                alert.show();
//                            }
//
//// Hide after some seconds
//                            final Handler handler  = new Handler();
//                            final Runnable runnable = new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (alert.isShowing()) {
//                                        alert.dismiss();
//                                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                                        homeIntent.addCategory( Intent.CATEGORY_HOME );
//                                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                        startActivity(homeIntent);
//                                    }
//                                }
//                            };
//
//                            alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                                @Override
//                                public void onDismiss(DialogInterface dialog) {
//                                    handler.removeCallbacks(runnable);
//                                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                                    homeIntent.addCategory( Intent.CATEGORY_HOME );
//                                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(homeIntent);
//                                }
//                            });
//
//                            handler.postDelayed(runnable, 10000);
//                        }
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public android.app.AlertDialog.Builder buildDialog(Context c) {

        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

        return builder;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isConnected(UserProfile.this)) buildDialog(UserProfile.this).show();
        else {
            Toast.makeText(UserProfile.this,"Welcome", Toast.LENGTH_SHORT).show();
            //setContentView(R.layout.activity_main);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.user_profile, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_editProfile) {
            return true;
        }
        if(id == R.id.action_logout){



        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new HomeFragment());
            ft.commit();
        }
        else if (id == R.id.nav_scbSection) {
            // Handle the camera action
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new SCBSectionFragment());
            ft.commit();
        } else if (id == R.id.nav_scbInfoBulletin) {
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new InformationBulletinFragment());
            ft.commit();

        } else if (id == R.id.nav_scbAboutSCB) {
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new AboutSCBFragment());
            ft.commit();
            /*
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            View mView = getLayoutInflater().inflate(R.layout.activity_professionals_spinner, null);

            builder.setTitle("Choose your Professional");

            final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,
                    getResources().getStringArray(R.array.professionalsList));

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpinner.setAdapter(adapter);

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    final Intent intent;
                    switch (position){
                        case 1:
                            intent = new Intent(getApplicationContext(), Professional1stActivity.class);
                            intent.putExtra("PROFESSIONAL", "First Professional");
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(getApplicationContext(), Professional2ndActivity.class);
                            intent.putExtra("PROFESSIONAL", "Second Professional");
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(getApplicationContext(), Professional3rdActivity.class);
                            intent.putExtra("PROFESSIONAL", "Third Professional Part-1");
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(getApplicationContext(), Professional4thActivity.class);
                            startActivity(intent);
                            intent.putExtra("PROFESSIONAL", "Third Professional Part-2");
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Choose Your Professional", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    Toast.makeText(getApplicationContext(), "Choose Your Professional", Toast.LENGTH_SHORT).show();

                }
            });

            builder.setView(mView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            */

        } else if (id == R.id.nav_scbMap) {
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new SchematicMapFragment());
            ft.commit();
//            Intent intent = new Intent(UserProfile.this, SCBMapActivity.class);
//            startActivity(intent);
            //Toast.makeText(UserProfile.this, "Map is coming soon", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_scbDoctorsInfo) {

            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new DepartmentFragment());
            ft.commit();
            /*
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new QuestionBankFragment());
            ft.commit();
            */
        }else if (id == R.id.nav_submitQuestion) {
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new QuestionBankFragment());
            ft.commit();
        }else if (id == R.id.nav_feedback) {
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new FeedbackFragment());
            ft.commit();
        }else if (id == R.id.nav_logout) {

            firebaseAuth.signOut();
            firebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, MainActivity.class));
            finish();

        }else if (id == R.id.nav_fromMakersDesk) {
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain, new MakersDeskFragment());
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
