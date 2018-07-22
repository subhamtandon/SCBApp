package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForQuestionsList extends RecyclerView.Adapter<AdapterForQuestionsList.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> questionsArrayList = new ArrayList<>();
    String professional;
    String subject;
    String chapter;
    String mode;
    String set;

    public AdapterForQuestionsList(RecyclerView recyclerView, Context context, ArrayList<String> questionsArrayList, String professional, String subject, String chapter, String mode, String set){

        this.recyclerView = recyclerView;
        this.context = context;
        this.questionsArrayList = questionsArrayList;
        this.professional = professional;
        this.subject = subject;
        this.chapter = chapter;
        this.mode = mode;
        this.set = set;
    }

    public void update(String questionName){

        questionsArrayList.add(questionName);
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

        holder.questionName.setText(questionsArrayList.get(position));
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
