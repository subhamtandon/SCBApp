package com.example.subhamtandon.scbapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterForQuestionsList extends RecyclerView.Adapter<AdapterForQuestionsList.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> questionsArrayList = new ArrayList<>();
    ArrayList<String> idsArrayList = new ArrayList<>();
    String professional;
    String subject;
    String chapter;
    String set;

    public AdapterForQuestionsList(RecyclerView recyclerView, Context context, ArrayList<String> questionsArrayList, ArrayList<String> idsArrayList, String professional, String subject, String chapter, String set){

        this.recyclerView = recyclerView;
        this.context = context;
        this.questionsArrayList = questionsArrayList;
        this.idsArrayList = idsArrayList;
        this.professional = professional;
        this.subject = subject;
        this.chapter = chapter;
        this.set = set;
    }

    public void update(String questionName, String uniqueId){

        questionsArrayList.add(questionName);
        idsArrayList.add(uniqueId);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item_questions, parent, false);
        return new AdapterForQuestionsList.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final int pos = position;

        holder.questionName.setText(questionsArrayList.get(pos));

        holder.questionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.d("unique id", idsArrayList.get(position));

                String id = idsArrayList.get(pos);

                Intent next = new Intent(context, AdminQuestionDetailsActivity.class);
                next.putExtra("PROFESSIONAL", professional);
                next.putExtra("SUBJECT", subject);
                next.putExtra("CHAPTER", chapter);
                next.putExtra("SET",set);
                next.putExtra("ID",id);
                context.startActivity(next);
            }
        });

        holder.deleteQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Delete Question");
                alertDialog.setMessage("Do you want to delete this question? ");
                alertDialog.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("MCQs").child("Chapters").child(chapter).child("Sets").child(set);

                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child(professional).child(subject).child("MCQs").child("Questions");

                        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child("Random").child(professional).child(subject).child("Questions");

                        DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference().child("App").child("Study").child("Random").child(professional).child("Questions");


                        String questionId = idsArrayList.get(pos);

                        databaseReference.child(questionId).removeValue();
                        databaseReference1.child(questionId).removeValue();
                        databaseReference2.child(questionId).removeValue();
                        databaseReference3.child(questionId).removeValue();


                        idsArrayList.remove(pos);
                        //urls.remove(pos);
                        questionsArrayList.remove(pos);
                        //notifyDataSetChanged();
                        Toast.makeText(context, "Deletion done successfully" , Toast.LENGTH_SHORT).show();

                        ListOfQuestionsActivity listOfQuestionsActivity = (ListOfQuestionsActivity) context;
                        listOfQuestionsActivity.onBackPressed();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView questionName;
        ImageView deleteQuestion;

        public ViewHolder(View itemView) {
            super(itemView);
            questionName = itemView.findViewById(R.id.textViewQuestionName);
            deleteQuestion = itemView.findViewById(R.id.deleteQuestion);
        }
    }
}
