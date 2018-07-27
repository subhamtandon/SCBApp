package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForQuestionsList extends RecyclerView.Adapter<AdapterForQuestionsList.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> questionsArrayList = new ArrayList<>();
    ArrayList<String> idsArrayList = new ArrayList<>();
    String professional;
    String subject;
    String chapter;
    String mode;
    String set;

    public AdapterForQuestionsList(RecyclerView recyclerView, Context context, ArrayList<String> questionsArrayList, ArrayList<String> idsArrayList, String professional, String subject, String chapter, String mode, String set){

        this.recyclerView = recyclerView;
        this.context = context;
        this.questionsArrayList = questionsArrayList;
        this.idsArrayList = idsArrayList;
        this.professional = professional;
        this.subject = subject;
        this.chapter = chapter;
        this.mode = mode;
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

                Intent next = new Intent(context, CheckQuestionDetails.class);
                next.putExtra("PROFESSIONAL", professional);
                next.putExtra("SUBJECT", subject);
                next.putExtra("CHAPTER", chapter);
                next.putExtra("MODE",mode);
                next.putExtra("SET",set);
                next.putExtra("ID",id);
                context.startActivity(next);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView questionName;

        public ViewHolder(View itemView) {
            super(itemView);
            questionName = itemView.findViewById(R.id.textViewQuestionName);
        }
    }
}
