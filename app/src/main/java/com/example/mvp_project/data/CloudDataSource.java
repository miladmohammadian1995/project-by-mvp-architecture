package com.example.mvp_project.data;


import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CloudDataSource implements NewsDataSource {

    private ApiService apiService;
    public CloudDataSource()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mvp2.miladstrike.ir/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    @Override
    public Single<List<News>> getNews() {
        return apiService.getNews();
    }

    @Override
    public Single<List<News>> getNewsAll() {
        return apiService.getNewsAll();
    }

    @Override
    public Single<List<Banners>> getBanners() {
        return apiService.getBanners();
    }

    @Override
    public Single<List<Category>> getCategory() {
        return apiService.getCategory();
    }

    @Override
    public Single<List<News>> getBookmarkNews() {
        return null;
    }


    @Override
    public void bookmark(News news) {

    }

    @Override
    public void deleteBookmark(News news) {

    }

    @Override
    public Single<List<News>> search(CharSequence keyword) {
        return apiService.getSearchResultNews(keyword.toString());
    }

    @Override
    public Single<List<Videos>> getVideos() {
        return apiService.getVideos();
    }

    @Override
    public Single<List<News>> getListCategory(String grouping) {
        return apiService.getListCategoryNews(grouping);
    }

    @Override
    public List<News> getAllBookmark() {
        return null;
    }


}
