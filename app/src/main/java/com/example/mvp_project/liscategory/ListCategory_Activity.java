package com.example.mvp_project.liscategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvp_project.R;
import com.example.mvp_project.base.BaseActivity;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsRepository;

import java.util.List;

public class ListCategory_Activity extends BaseActivity implements ListContract.View{

    private RecyclerView recyclerView;
    private ListContract.Presenter presenter;
    private ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category_);
        presenter=new ListPresenter(new NewsRepository(this));
        presenter.atachView(this);
        recyclerView = findViewById(R.id.recy_ListCategory);
        listAdapter = new ListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(listAdapter);
        presenter.getGrouping(getIntent().getStringExtra("grouping"));

    }

    @Override
    public void setProgressBar(boolean isProgress) {

    }

    @Override
    public void showResultListNews(List<News> newsList) {

        listAdapter.setNewsList(newsList);
    }

    @Override
    public void showProgressBar(boolean isShowProgressBar) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
