package com.example.mvp_project.search;

import com.example.mvp_project.base.BasePresenter;
import com.example.mvp_project.base.Baseview;
import com.example.mvp_project.data.News;

import java.util.List;

public interface SearchContract {
    interface View extends Baseview
    {
        void showResultNews(List<News> newsList);
        void showNoResultMessage();
    }
    interface Presenter extends BasePresenter<View>
    {

        void search(CharSequence keyword);

    }

}
