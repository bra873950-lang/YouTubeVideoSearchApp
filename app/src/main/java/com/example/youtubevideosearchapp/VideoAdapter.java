package com.example.youtubevideosearchapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;


import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videos;
    private Context context;

    public VideoAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.title.setText(video.title);
        holder.description.setText(video.description);
        holder.channel.setText(video.channelTitle);
        holder.publishedAt.setText(video.publishedAt);
        Glide.with(context).load(video.thumbnailUrl).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, channel, publishedAt;
        ImageView thumbnail;

        VideoViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
            channel = itemView.findViewById(R.id.tvChannel);
            publishedAt = itemView.findViewById(R.id.tvPublishedAt);
            thumbnail = itemView.findViewById(R.id.ivThumbnail);
        }
    }
}
