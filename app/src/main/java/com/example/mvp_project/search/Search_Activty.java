package com.example.mvp_project.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp_project.R;
import com.example.mvp_project.base.BaseActivity;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsRepository;
import com.example.mvp_project.home.NewAdapter;

import java.util.List;

import retrofit2.http.GET;

public class Search_Activty extends BaseActivity implements SearchContract.View {

    private RecyclerView recyclerViewSearch;
    private ImageView imageViewBack, imageViewClear;
    private TextView txtnoResult;
    private EditText editTextSearch;
    private NewAdapter newAdapter;
    private SearchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__activty);
        presenter = new SearchPresenter(new NewsRepository(this));
        presenter.atachView(this);
        setUpViews();
    }

    private void setUpViews() {
        recyclerViewSearch = findViewById(R.id.recy_search);
        imageViewBack = findViewById(R.id.image_back);
        imageViewClear = findViewById(R.id.image_clear);
        txtnoResult = findViewById(R.id.txt_noResult);
        editTextSearch = findViewById(R.id.edt_search);
        newAdapter = new NewAdapter();
        imageViewClear.setVisibility(View.INVISIBLE);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerViewSearch.setAdapter(newAdapter);
        imageViewClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextSearch.setText("");
            }
        });
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    presenter.search(s.toString());
                    imageViewClear.setVisibility(View.VISIBLE);
                } else {
                    imageViewClear.setVisibility(View.INVISIBLE);
                    recyclerViewSearch.setVisibility(View.GONE);
                    txtnoResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void setProgressBar(boolean isProgress) {

    }

    @Override
    public void showResultNews(List<News> newsList) {

        newAdapter.setNewsList(newsList);
        txtnoResult.setVisibility(View.INVISIBLE);
        recyclerViewSearch.setVisibility(View.VISIBLE);


    }

    @Override
    public void showNoResultMessage() {

        recyclerViewSearch.setVisibility(View.INVISIBLE);
        txtnoResult.setVisibility(View.VISIBLE);

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
}
