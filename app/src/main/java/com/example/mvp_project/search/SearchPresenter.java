package com.example.mvp_project.search;

import com.example.mvp_project.R;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;
    private NewsDataSource newsDataSource;
    private Disposable disposable;

    public SearchPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void search(CharSequence keyword) {

        newsDataSource.search(keyword).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<News> newsList) {

                        if (newsList.isEmpty()) {
                            view.showNoResultMessage();
                        } else {
                            view.showResultNews(newsList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(view.context().getResources().getString(R.string.error));
                    }
                });
    }

    @Override
    public void atachView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
