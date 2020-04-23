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
    private int[] name;
    private int[] description;
    private String[] videoList;

    private String part2 = "\" frameborder=\"0\" allowfullscreen></iframe>\"";
    private String part1 = "\"<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/";
    public VideoAdapter() {
    }

    public VideoAdapter(String[] videoList, int[] name, int[] description) {
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


        holder.videoWeb.loadData( part1+videoList[position]+part2, "text/html" , "utf-8" );
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
