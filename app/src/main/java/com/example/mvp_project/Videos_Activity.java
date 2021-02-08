package com.example.mvp_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mvp_project.data.Videos;
import com.squareup.picasso.Picasso;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class Videos_Activity extends AppCompatActivity {

    private Videos getVideo;
    JZVideoPlayerStandard jzVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_);
        getVideo = getIntent().getParcelableExtra("video");

         jzVideoPlayerStandard = findViewById(R.id.jz);
        jzVideoPlayerStandard = findViewById(R.id.jz);
        jzVideoPlayerStandard.setUp(getVideo.getVideolink(), JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN);
        jzVideoPlayerStandard.setVisibility(View.VISIBLE);
        Picasso.get().load(getVideo.getImage()).into(jzVideoPlayerStandard.thumbImageView);
        jzVideoPlayerStandard.fullscreenButton.setVisibility(View.GONE);
        jzVideoPlayerStandard.batteryLevel.setVisibility(View.GONE);
        jzVideoPlayerStandard.tinyBackImageView.setVisibility(View.GONE);
        jzVideoPlayerStandard.backButton.setVisibility(View.GONE);
        jzVideoPlayerStandard.videoCurrentTime.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (jzVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        jzVideoPlayerStandard.releaseAllVideos();
    }
}
