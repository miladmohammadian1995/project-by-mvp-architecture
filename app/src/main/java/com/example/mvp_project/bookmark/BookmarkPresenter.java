package com.example.mvp_project.bookmark;

import com.example.mvp_project.R;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookmarkPresenter implements BookmarkContract.Presenter {
    private BookmarkContract.View view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean isRenderd;

    public BookmarkPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void getBokkmarkList() {
        view.showProgressBar(true);
        newsDataSource.getBookmarkNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<News> newsList) {

                        if (newsList.isEmpty()) {
                            view.showEmptyView();
                        } else {
                            view.showBookmark(newsList);
                            view.heideEnptyView();
                        }

                        view.showProgressBar(false);


                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showProgressBar(false);
                        view.showError(view.context().getResources().getString(R.string.error));

                    }
                });
    }

    @Override
    public void atachView(BookmarkContract.View view) {
        this.view = view;

        getBokkmarkList();

    }

    @Override
    public void detachView() {
        this.view = null;
        if (compositeDisposable != null && compositeDisposable.size() > 0) {
            compositeDisposable.clear();
        }

    }
}
