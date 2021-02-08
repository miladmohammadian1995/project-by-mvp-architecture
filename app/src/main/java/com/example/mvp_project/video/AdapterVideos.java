package com.example.mvp_project.video;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp_project.NewsDetails.NewsDetailsSctivity;
import com.example.mvp_project.R;
import com.example.mvp_project.Videos_Activity;
import com.example.mvp_project.data.Videos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterVideos extends RecyclerView.Adapter<AdapterVideos.VideosVideoHolder> {

    private List<Videos> videosList;

    public AdapterVideos(List<Videos> videosList) {
        this.videosList = videosList;
    }

    @NonNull
    @Override
    public VideosVideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_videos, parent, false);
        return new VideosVideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosVideoHolder holder, int position) {
        holder.bindVideos(videosList.get(position));
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class VideosVideoHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewVideos;
        private TextView textView;

        public VideosVideoHolder(@NonNull View itemView) {
            super(itemView);
            imageViewVideos = itemView.findViewById(R.id.image_videos);
            textView = itemView.findViewById(R.id.txt_videos);
        }

        public void bindVideos(final Videos videos) {
            Picasso.get().load(videos.getImage()).into(imageViewVideos);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), Videos_Activity.class);
                    intent.putExtra("video", videos);
                    itemView.getContext().startActivity(intent);
                }
            });
            textView.setText(videos.getTitle());
        }
    }
}
