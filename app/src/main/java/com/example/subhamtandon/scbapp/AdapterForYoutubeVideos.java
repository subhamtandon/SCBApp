package com.example.subhamtandon.scbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AdapterForYoutubeVideos extends RecyclerView.Adapter<AdapterForYoutubeVideos.VideoViewHolder> {

    RecyclerView recyclerView;
    Context context;

    Vector<YoutubeVideos> youtubeVideosList = new Vector<>();
    ArrayList<String> linksArrayList = new ArrayList<>();

    public AdapterForYoutubeVideos() {
    }

    public AdapterForYoutubeVideos(RecyclerView recyclerView, Context context, Vector<YoutubeVideos> youtubeVideosList) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.youtubeVideosList = youtubeVideosList;
    }

    public void update(String link){
        linksArrayList.add(link);
        youtubeVideosList.add( new YoutubeVideos(link));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterForYoutubeVideos.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.layout_list_of_youtube_videos,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForYoutubeVideos.VideoViewHolder holder, int position) {

        holder.videoView.loadData(youtubeVideosList.get(position).getVideoUrl(),"text/html", "utf-8");
    }

    @Override
    public int getItemCount() {
        return youtubeVideosList.size();
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder{
        WebView videoView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.youtubeVideoWebView);

            videoView.getSettings().setJavaScriptEnabled(true);
            videoView.setWebChromeClient(new WebChromeClient() {
            } );

        }
    }
}
