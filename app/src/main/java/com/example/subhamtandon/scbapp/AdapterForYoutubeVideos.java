package com.example.subhamtandon.scbapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.List;

public class AdapterForYoutubeVideos extends RecyclerView.Adapter<AdapterForYoutubeVideos.VideoViewHolder> {

    List<YoutubeVideos> youtubeVideosList;

    public AdapterForYoutubeVideos() {
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

    public AdapterForYoutubeVideos(List<YoutubeVideos> youtubeVideosList) {
        this.youtubeVideosList = youtubeVideosList;
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
