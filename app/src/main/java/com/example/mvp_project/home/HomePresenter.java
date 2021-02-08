package com.example.mvp_project.home;

import com.example.mvp_project.R;
import com.example.mvp_project.data.Banners;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable disposable =new CompositeDisposable();
    private boolean isRenderd;

    public HomePresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }


    @Override
    public void getNews() {
        view.showProgressBar(true);
        newsDataSource.getNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<News> newsList) {
                        view.showNews(newsList);
                        view.showProgressBar(false);
                        isRenderd = true;

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(view.context().getResources().getString(R.string.error));
                        view.showProgressBar(false);
                    }
                });
    }

    @Override
    public void getBanners() {
        view.showProgressBar(true);
        newsDataSource.getBanners().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Banners>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                             disposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Banners> banners) {
                        view.showProgressBar(false);
                        view.showBanners(banners);
                        isRenderd = true;

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(view.context().getResources().getString(R.string.error));
                        view.showProgressBar(false);
                    }
                });
    }

    @Override
    public void atachView(HomeContract.View view) {
        this.view = view;
        if (!isRenderd) {
            getNews();
            getBanners();
        }


    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable != null && disposable.size() > 0) {
            disposable.clear();
        }

    }
}
