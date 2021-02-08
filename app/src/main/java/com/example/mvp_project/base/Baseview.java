package com.example.mvp_project.base;

import android.content.Context;

public interface Baseview {

    void showProgressBar(boolean isShowProgressBar);
    void showError(String error);
    Context context();
}
