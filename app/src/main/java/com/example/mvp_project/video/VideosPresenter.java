package com.example.mvp_project.video;

import com.example.mvp_project.R;
import com.example.mvp_project.data.NewsDataSource;
import com.example.mvp_project.data.Videos;
import com.example.mvp_project.home.HomeContract;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideosPresenter implements VideosContract.Presenter {
    private VideosContract.View view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable disposable = new CompositeDisposable();
    private boolean isRenderd;

    public VideosPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void getVideos() {
        view.showProgressBar(true);
        newsDataSource.getVideos().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Videos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Videos> videosList) {
                        view.showProgressBar(false);
                        view.showVideos(videosList);
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
    public void atachView(VideosContract.View view) {
        this.view = view;
        if (!isRenderd) {
            getVideos();
        }
    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable != null || disposable.size() > 0) {
            disposable.clear();
        }

    }
}
