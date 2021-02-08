package com.example.mvp_project.video;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp_project.R;
import com.example.mvp_project.base.BaseFragment;
import com.example.mvp_project.data.NewsRepository;
import com.example.mvp_project.data.Videos;
import com.example.mvp_project.home.HomePresenter;

import java.util.List;

public class Videos_Fragment extends BaseFragment implements VideosContract.View {
    private VideosContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new VideosPresenter(new NewsRepository(context()));
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
    public int getLayoutRes() {
        return R.layout.fragment_video;
    }

    @Override
    public void setUpViews() {

    }

    @Override
    public void showVideos(List<Videos> videosList) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recy_Videos);
        AdapterVideos adapterVideos = new AdapterVideos(videosList);
        recyclerView.setLayoutManager(new GridLayoutManager(context(), 2));
        recyclerView.setAdapter(adapterVideos);
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
}
