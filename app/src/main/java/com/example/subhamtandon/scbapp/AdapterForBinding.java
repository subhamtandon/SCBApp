package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterForBinding extends RecyclerView.Adapter<AdapterForBinding.ViewHolder> {

    List<ListItemChapters> listItemChapters;
    Context context;

    public AdapterForBinding(List<ListItemChapters> listItemChapters, Context context) {
        this.listItemChapters = listItemChapters;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chapters, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ListItemChapters listItemChapter = listItemChapters.get(position);

        holder.chapterHead.setText(listItemChapter.getChapterHead());
        holder.chapterDesc.setText(listItemChapter.getChapterDesc());
    }

    @Override
    public int getItemCount() {
        return listItemChapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView chapterHead, chapterDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            chapterHead = (TextView)itemView.findViewById(R.id.textViewChapterName);
            chapterDesc = (TextView)itemView.findViewById(R.id.textViewChapterDesc);
        }
    }
}

