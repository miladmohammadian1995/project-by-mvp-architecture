package com.example.mvp_project.allnews;

import com.example.mvp_project.base.BasePresenter;
import com.example.mvp_project.base.Baseview;
import com.example.mvp_project.data.News;
import com.example.mvp_project.search.SearchContract;

import java.util.List;

public interface AllNewsConterct {
    interface View extends Baseview
    {
        void showResultNews(List<News> newsList);

    }
    interface Presenter extends BasePresenter<View>
    {

        void getNewsAll();

    }
}
