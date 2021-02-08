package com.example.mvp_project.liscategory;

import com.example.mvp_project.base.BasePresenter;
import com.example.mvp_project.base.Baseview;
import com.example.mvp_project.data.News;
import com.example.mvp_project.search.SearchContract;

import java.util.List;

public interface ListContract {

    interface View extends Baseview
    {
        void showResultListNews(List<News> newsList);

    }
    interface Presenter extends BasePresenter<View>
    {

        void getGrouping(String grouping);

    }


}
