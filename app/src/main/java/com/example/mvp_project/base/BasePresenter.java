package com.example.mvp_project.base;

public interface BasePresenter <T extends Baseview> {
    void atachView(T view);
    void detachView();
}
