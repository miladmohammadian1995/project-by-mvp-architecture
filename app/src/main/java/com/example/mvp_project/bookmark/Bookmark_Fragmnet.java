package com.example.mvp_project.bookmark;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp_project.R;
import com.example.mvp_project.base.BaseFragment;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsRepository;
import com.example.mvp_project.home.NewAdapter;

import java.util.List;

public class Bookmark_Fragmnet extends BaseFragment implements BookmarkContract.View {
    private BookmarkContract.Presenter presenter;
    private View emptyView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BookmarkPresenter(new NewsRepository(context()));
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
        return R.layout.fragment_bookmark;
    }

    @Override
    public void setUpViews() {

        emptyView = rootView.findViewById(R.id.frame_empty);
    }

    @Override
    public void showBookmark(List<News> newsList) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recy_Bookmark);
        NewAdapter newAdapter = new NewAdapter(newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(newAdapter);
//        Toast.makeText(context(), newsList.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void heideEnptyView() {
emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar(boolean isShowProgressBar) {
        getBaseActivity().setProgressBar(isShowProgressBar);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(context(), "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getActivity();
    }
}
