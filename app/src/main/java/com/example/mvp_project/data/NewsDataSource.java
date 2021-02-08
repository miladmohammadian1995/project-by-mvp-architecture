package com.example.mvp_project.data;

import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

public interface NewsDataSource {

    Single<List<News>> getNews();
    Single<List<News>> getNewsAll();
    Single<List<Banners>> getBanners();

    Single<List<Category>> getCategory();

    Single<List<News>> getBookmarkNews();

    void bookmark(News news);

    void deleteBookmark(News news);

    Single<List<News>> search(CharSequence keyword);
    Single<List<Videos>> getVideos();
    Single<List<News>> getListCategory(String grouping);
    List<News> getAllBookmark();
}
