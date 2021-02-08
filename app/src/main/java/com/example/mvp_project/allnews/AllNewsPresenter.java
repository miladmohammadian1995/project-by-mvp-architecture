package com.example.mvp_project.allnews;

import com.example.mvp_project.R;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AllNewsPresenter implements AllNewsConterct.Presenter {

    private AllNewsConterct.View view;
    private NewsDataSource newsDataSource;
    private Disposable disposable;

    public AllNewsPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void getNewsAll() {
        view.showProgressBar(true);
        newsDataSource.getNewsAll().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<News> news) {
                        view.showResultNews(news);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(view.context().getResources().getString(R.string.error));

                    }
                });
    }

    @Override
    public void atachView(AllNewsConterct.View view) {
        this.view = view;
        getNewsAll();
    }

    @Override
    public void detachView() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
