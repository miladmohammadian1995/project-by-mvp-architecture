package com.example.mvp_project.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class LocalDataSource implements NewsDataSource {
    @Override
    public Single<List<News>> getNews() {
        return null;
    }

    @Override
    public Single<List<Banners>> getBanners() {
        return null;
    }

    @Override
    public Single<List<Category>> getCategory() {
        return null;
    }

    @Query("SELECT * FROM tbl_news WHERE is_bookmarked LIKE 1")
    @Override
    public abstract Single<List<News>> getBookmarkNews();

    @Insert
    @Override
    public abstract void bookmark(News news);

    @Delete
    @Override
    public abstract void deleteBookmark(News news);

    @Override
    public Single<List<News>> search(CharSequence keyword) {
        return null;
    }

    @Override
    public Single<List<Videos>> getVideos() {
        return null;
    }

    @Override
    public Single<List<News>> getListCategory(String grouping) {
        return null;
    }

    @Override
    public Single<List<News>> getNewsAll() {
        return null;
    }

    @Query("SELECT * FROM tbl_news")
    @Override
    public abstract List<News> getAllBookmark();
}
