package com.example.mvp_project.category;

import com.example.mvp_project.R;
import com.example.mvp_project.data.Category;
import com.example.mvp_project.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter implements CategoryContract.Presenter {
    private CategoryContract.View view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable disposable = new CompositeDisposable();
    private boolean isRenderd;

    public CategoryPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void getCategory() {
        view.showProgressBar(true);
        newsDataSource.getCategory().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Category> categories) {
                        view.showCategroy(categories);
                        view.showProgressBar(false);
                        isRenderd=true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(view.context().getResources().getString(R.string.error));
                        view.showProgressBar(false);
                        isRenderd=true;
                    }
                });
    }

    @Override
    public void atachView(CategoryContract.View view) {
        this.view = view;
        if (!isRenderd) {
            getCategory();
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
