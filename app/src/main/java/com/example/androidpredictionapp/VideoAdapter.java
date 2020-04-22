package com.example.androidpredictionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

//    private List<YouTubeVideos> youtubeVideoList;
    private String[] name;
    private String[] description;
    private String[] videoList;

    public VideoAdapter() {
    }

    public VideoAdapter(String[] videoList, String[] name, String[] description) {
//        this.youtubeVideoList = youtubeVideoList;
        this.name = name;
        this.description = description;
        this.videoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.video_view_layout, parent, false);

        return new VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        holder.videoWeb.loadData( videoList[position], "text/html" , "utf-8" );
        holder.name.setText(name[position]);
        holder.description.setText(description[position]);


    }

    @Override
    public int getItemCount() {
        return videoList.length;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        WebView videoWeb;
        TextView name, description;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoWeb =  itemView.findViewById(R.id.webView);
            name = itemView.findViewById(R.id.songName);
            description = itemView.findViewById(R.id.songDescription);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient() {

            } );
        }
    }
}
