package com.example.mvp_project.bookmark;

import com.example.mvp_project.base.BasePresenter;
import com.example.mvp_project.base.Baseview;
import com.example.mvp_project.data.News;

import java.util.List;

public interface BookmarkContract  {
    interface View extends Baseview
    {
        void showBookmark(List<News> newsList);
        void showEmptyView();
        void heideEnptyView();
    }
    interface Presenter extends BasePresenter<View>
    {
        void getBokkmarkList();
    }
}
