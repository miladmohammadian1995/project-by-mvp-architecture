package com.example.mvp_project.allnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp_project.R;
import com.example.mvp_project.base.BaseActivity;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsRepository;
import com.example.mvp_project.home.NewAdapter;
import com.example.mvp_project.search.SearchContract;
import com.example.mvp_project.search.SearchPresenter;

import java.util.List;

public class AllNews_Activity extends BaseActivity implements AllNewsConterct.View {

    private RecyclerView recyclerViewAllNews;

    private AllNewsConterct.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news_);
        presenter = new AllNewsPresenter(new NewsRepository(this));
        presenter.atachView(this);


    }

    @Override
    public void setProgressBar(boolean isProgress) {

    }

    @Override
    public void showResultNews(List<News> newsList) {
        recyclerViewAllNews= findViewById(R.id.recy_all_news);
        NewAdapter newAdapter = new NewAdapter(newsList);
        recyclerViewAllNews.setLayoutManager(new LinearLayoutManager(context(),RecyclerView.VERTICAL,false));
        recyclerViewAllNews.setAdapter(newAdapter);
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
