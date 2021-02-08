package com.example.mvp_project.liscategory;

import com.example.mvp_project.R;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsDataSource;
import com.example.mvp_project.search.SearchContract;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListPresenter implements ListContract.Presenter {
    private ListContract.View view;
    private NewsDataSource newsDataSource;
    private Disposable disposable;

    public ListPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void getGrouping(String grouping) {

        newsDataSource.getListCategory(grouping).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<News> news) {
                        view.showResultListNews(news);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(view.context().getResources().getString(R.string.error));

                    }
                });
    }

    @Override
    public void atachView(ListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
