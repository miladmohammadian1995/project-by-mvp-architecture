package com.example.mvp_project.category;

import com.example.mvp_project.base.BasePresenter;
import com.example.mvp_project.base.Baseview;
import com.example.mvp_project.data.Banners;
import com.example.mvp_project.data.Category;
import com.example.mvp_project.data.News;
import com.example.mvp_project.home.HomeContract;

import java.util.List;

public interface CategoryContract {


    interface View extends Baseview {
        void showCategroy(List<Category> showcategory);


    }

    interface Presenter extends BasePresenter<CategoryContract.View> {
        void getCategory();
    }
}
