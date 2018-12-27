package com.example.subhamtandon.scbapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;

public class AdminHomeActivity extends AppCompatActivity {

    CardView StudyCard,DepartmentCard,InformationBulletinCard, MedicalRelatedPicsCard, PPTCard, featuredVideosCard, newPPTPDFCard;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    Switch switchOnOff;
    Boolean statusOfAltering;
    String statusRetrievedAltering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        StudyCard = (CardView) findViewById(R.id.StudyCard);
        DepartmentCard = (CardView) findViewById(R.id.DepartmentCard);
        InformationBulletinCard = findViewById(R.id.InformationBulletinCard);
        MedicalRelatedPicsCard = findViewById(R.id.MedicalRelatedPicturesCard);
        PPTCard = findViewById(R.id.PPTCard);
        featuredVideosCard = findViewById(R.id.featuredVideosCard);
        newPPTPDFCard = findViewById(R.id.newPPTsCard);
        switchOnOff = findViewById(R.id.switchOnOff);

        database = FirebaseDatabase.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        statusOfAltering = switchOnOff.isChecked();
        Toast.makeText(AdminHomeActivity.this,statusOfAltering.toString(), Toast.LENGTH_SHORT).show();

        StudyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();

            }
        });

        DepartmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();

            }
        });

        InformationBulletinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();
            }
        });

        MedicalRelatedPicsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();                        }
        });
        PPTCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();

            }
        });

        featuredVideosCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();

            }
        });

        newPPTPDFCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();

            }
        });

        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                statusOfAltering = switchOnOff.isChecked();
                Toast.makeText(AdminHomeActivity.this,statusOfAltering.toString(), Toast.LENGTH_SHORT).show();
                DatabaseReference databaseReference = database.getReference();
                databaseReference.child("Altering").setValue(statusOfAltering.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AdminHomeActivity.this, "Altering Status changed!!!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    if(dataSnapshot1.getKey().equalsIgnoreCase("Altering")){
                        String can = dataSnapshot1.getValue(String.class);
                        if (can.equalsIgnoreCase("true")){
                            Toast.makeText(AdminHomeActivity.this, "Its working", Toast.LENGTH_SHORT).show();
                            StudyCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    startActivity(new Intent(getApplicationContext(), AdminStudyActivity.class));

                                }
                            });

                            DepartmentCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    startActivity(new Intent(getApplicationContext(), AdminDepartmentActivity.class));

                                }
                            });

                            InformationBulletinCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    startActivity(new Intent(getApplicationContext(), InformationBulletinActivity.class));
                                }
                            });

                            MedicalRelatedPicsCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(getApplicationContext(), AdminMedicalRelatedPicsActivity.class));
                                }
                            });
                            PPTCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //TODO: to hide
                                    Toast.makeText(AdminHomeActivity.this, "This feature is no more available", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(getApplicationContext(), AdminPPTActivity.class));
                                }
                            });

                            featuredVideosCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //TODO: to remove
                                    Toast.makeText(AdminHomeActivity.this, "This feature is no more available", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(getApplicationContext(), AdminFeaturedVideos.class));
                                }
                            });

                            newPPTPDFCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(AdminHomeActivity.this, NewPPTsAndPDFsTypes.class);
                                    startActivity(i);
                                }
                            });
                        }
                        else {
                            Toast.makeText(AdminHomeActivity.this, "Its working not", Toast.LENGTH_SHORT).show();
                            StudyCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();

                                }
                            });

                            DepartmentCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();

                                }
                            });

                            InformationBulletinCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

//                                    Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), InformationBulletinActivity.class));
                                }
                            });

                            MedicalRelatedPicsCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();
                                }
                            });
                            PPTCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();
                                }
                            });

                            featuredVideosCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();

                                }
                            });

                            newPPTPDFCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(AdminHomeActivity.this, "Make it alterable", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void logout(View view){

        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Altering").setValue("false").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AdminHomeActivity.this, "Altering Status changed!!!", Toast.LENGTH_SHORT).show();
            }
        });

        firebaseAuth.signOut();
        firebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Altering").setValue("false").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AdminHomeActivity.this, "Altering Status changed!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Altering").setValue("false").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AdminHomeActivity.this, "Altering Status changed!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
//        DatabaseReference databaseReference = database.getReference();
//        databaseReference.child("Altering").setValue("false").addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Toast.makeText(AdminHomeActivity.this, "Altering Status changed!!!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        switchOnOff.setChecked(false);
    }
}
