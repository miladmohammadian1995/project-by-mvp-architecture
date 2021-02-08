package com.example.mvp_project.home;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.mvp_project.R;
import com.example.mvp_project.allnews.AllNews_Activity;
import com.example.mvp_project.base.BaseFragment;
import com.example.mvp_project.data.Banners;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsRepository;
import com.example.mvp_project.search.Search_Activty;


import java.util.List;

public class Home_Fragment extends BaseFragment implements HomeContract.View {
    private HomeContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(new NewsRepository(context()));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.atachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void showNews(List<News> showNewsList) {
        RecyclerView recyclerViewNews = rootView.findViewById(R.id.recy_lastNews);
        NewAdapter newAdapter = new NewAdapter(showNewsList);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerViewNews.setAdapter(newAdapter);
        recyclerViewNews.setNestedScrollingEnabled(false);
    }

    @Override
    public void showBanners(List<Banners> showBanners) {
        RecyclerView recyclerViewBanners = rootView.findViewById(R.id.recy_banners);
        BannersAdapter bannersAdapter = new BannersAdapter(showBanners, context());
        recyclerViewBanners.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewBanners.setAdapter(bannersAdapter);
        recyclerViewBanners.setNestedScrollingEnabled(false);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewBanners);
    }


    @Override
    public void showProgressBar(boolean isShowProgressBar) {
        getBaseActivity().setProgressBar(isShowProgressBar);
    }

    @Override
    public void showError(String error) {

        Toast.makeText(context(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void setUpViews() {
        ImageView imageViewsearch = rootView.findViewById(R.id.image_searach);
        imageViewsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Search_Activty.class));

            }
        });
        ImageView imageViewAllNews = rootView.findViewById(R.id.image_All_News);
        imageViewAllNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AllNews_Activity.class));

            }
        });


    }
}
