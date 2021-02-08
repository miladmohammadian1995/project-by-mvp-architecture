package com.example.mvp_project.home;

import com.example.mvp_project.base.BasePresenter;
import com.example.mvp_project.base.Baseview;
import com.example.mvp_project.data.Banners;
import com.example.mvp_project.data.News;

import java.util.List;

public interface HomeContract {
    interface View extends Baseview {
        void showNews(List<News> showNewsList);

        void showBanners(List<Banners> showBanners);
    }

    interface Presenter extends BasePresenter<View> {
        void getNews();

        void getBanners();
    }
}
