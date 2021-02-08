package com.example.mvp_project.data;

import android.content.Context;

import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

public class NewsRepository implements NewsDataSource {
    CloudDataSource cloudDataSource = new CloudDataSource();
    LocalDataSource localDataSource ;

    public NewsRepository (Context context)
    {
        localDataSource = AppDatabase.getInstance(context).getLocalDataSource();
    }
    public Single<List<News>> getNews() {
        return cloudDataSource.getNews();
    }

    @Override
    public Single<List<News>> getNewsAll() {
        return cloudDataSource.getNewsAll();
    }

    @Override
    public Single<List<Banners>> getBanners() {
        return cloudDataSource.getBanners();
    }

    @Override
    public Single<List<Category>> getCategory() {
        return cloudDataSource.getCategory();
    }

    @Override
    public Single<List<News>> getBookmarkNews() {
        return localDataSource.getBookmarkNews();
    }


    @Override
    public void bookmark(News news) {
        localDataSource.bookmark(news);
    }

    @Override
    public void deleteBookmark(News news) {
        localDataSource.deleteBookmark(news);
    }

    @Override
    public Single<List<News>> search(CharSequence keyword) {
        return cloudDataSource.search(keyword);
    }

    @Override
    public Single<List<Videos>> getVideos() {
        return cloudDataSource.getVideos();
    }

    @Override
    public Single<List<News>> getListCategory(String grouping) {
        return cloudDataSource.getListCategory(grouping);
    }

    @Override
    public List<News> getAllBookmark() {
        return localDataSource.getAllBookmark();
    }


}
