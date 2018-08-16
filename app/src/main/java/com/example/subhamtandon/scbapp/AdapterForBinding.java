package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterForBinding extends RecyclerView.Adapter<AdapterForBinding.ViewHolder> {

    List<ListItemChapters> listItemChapters;
    Context context;
    String professional;
    String subject;

    public AdapterForBinding(List<ListItemChapters> listItemChapters, Context context, String professional, String subject) {
        this.listItemChapters = listItemChapters;
        this.context = context;
        this.professional = professional;
        this.subject = subject;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chapters, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ListItemChapters listItemChapter = listItemChapters.get(position);

        holder.chapterHead.setText(listItemChapter.getChapterHead());
        holder.chapterDesc.setText(listItemChapter.getChapterDesc());

        holder.linearLayoutChapters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BasicQuestionSetActivity.class);
                intent.putExtra("PROFESSIONAL", professional);
                intent.putExtra("SUBJECT", subject);
                intent.putExtra("TYPE", "MCQs");
                intent.putExtra("CHAPTER", listItemChapter.getChapterHead());
                context.startActivity(intent);
            }
        });

        /*holder.linearLayoutChapters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Work is going on on "+listItemChapter.getChapterHead(), Toast.LENGTH_SHORT).show();


                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View mView = LayoutInflater.from(context).inflate(R.layout.activity_professionals_spinner, null);

                builder.setTitle("Choose your Mode")
                    .setCancelable(false);

                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_spinner_item,
                        context.getResources().getStringArray(R.array.modeList));

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                mSpinner.setAdapter(adapter);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("-Select-")){
                            if (mSpinner.getSelectedItem().toString().equalsIgnoreCase("Basic")){
                                Intent intent = new Intent(context, BasicQuestionSetActivity.class);
                                intent.putExtra("PROFESSIONAL", professional);
                                intent.putExtra("SUBJECT", subject);
                                intent.putExtra("TYPE", "MCQs");
                                intent.putExtra("CHAPTER", listItemChapter.getChapterHead());
                                intent.putExtra("MODE", "Basic");
                                context.startActivity(intent);
                            }
                            if (mSpinner.getSelectedItem().toString().equalsIgnoreCase("Advanced")){
                                Intent intent = new Intent(context, AdvancedQuestionSetActivity.class);
                                intent.putExtra("PROFESSIONAL", professional);
                                intent.putExtra("SUBJECT", subject);
                                intent.putExtra("TYPE", "MCQs");
                                intent.putExtra("CHAPTER", listItemChapter.getChapterHead());
                                intent.putExtra("MODE", "Advanced");
                                context.startActivity(intent);
                            }
                        }
                        else {
                            Toast.makeText(context, "Select Mode", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final Intent intent;
                        switch (position){
                            case 1:
                                intent = new Intent(context, BasicQuestionSetActivity.class);
                                intent.putExtra("PROFESSIONAL", professional);
                                intent.putExtra("SUBJECT", subject);
                                intent.putExtra("TYPE", "MCQs");
                                intent.putExtra("CHAPTER", listItemChapter.getChapterHead());
                                intent.putExtra("MODE", "Basic");
                                context.startActivity(intent);

                                //Toast.makeText(context, "Easy", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                intent = new Intent(context, AdvancedQuestionSetActivity.class);
                                intent.putExtra("PROFESSIONAL", professional);
                                intent.putExtra("SUBJECT", subject);
                                intent.putExtra("TYPE", "MCQs");
                                intent.putExtra("CHAPTER", listItemChapter.getChapterHead());
                                intent.putExtra("MODE", "Advanced");
                                context.startActivity(intent);
                                //Toast.makeText(context, "Medium", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                intent = new Intent(context, HardQuestionSetActivity.class);
                                context.startActivity(intent);
                                //Toast.makeText(context, "Hard", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                intent = new Intent(context, RandomQuestionSetActivity.class);
                                context.startActivity(intent);
                                //Toast.makeText(context, "Random", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(context, "Choose Your Mode", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                        Toast.makeText(context, "Choose Your Mode", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setView(mView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });*/
    }

    @Override
    public int getItemCount() {
        return listItemChapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView chapterHead, chapterDesc;
        LinearLayout linearLayoutChapters;

        public ViewHolder(View itemView) {
            super(itemView);

            chapterHead = (TextView)itemView.findViewById(R.id.textViewChapterName);
            chapterDesc = (TextView)itemView.findViewById(R.id.textViewChapterDesc);
            linearLayoutChapters = (LinearLayout) itemView.findViewById(R.id.linearLayoutChapters);
        }
    }
}

