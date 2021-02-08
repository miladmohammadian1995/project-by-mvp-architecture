package com.example.mvp_project.video;

import com.example.mvp_project.base.BasePresenter;
import com.example.mvp_project.base.Baseview;
import com.example.mvp_project.data.Banners;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.Videos;
import com.example.mvp_project.home.HomeContract;

import java.util.List;

public interface VideosContract {

    interface View extends Baseview {
        void showVideos(List<Videos> videosList);


    }

    interface Presenter extends BasePresenter<VideosContract.View> {
        void getVideos();


    }
}
