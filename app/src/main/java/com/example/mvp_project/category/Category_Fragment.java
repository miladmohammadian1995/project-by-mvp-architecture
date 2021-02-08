package com.example.mvp_project.category;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp_project.R;
import com.example.mvp_project.base.BaseFragment;
import com.example.mvp_project.data.Category;
import com.example.mvp_project.data.NewsRepository;
import com.example.mvp_project.home.HomePresenter;

import java.util.List;

public class Category_Fragment extends BaseFragment implements CategoryContract.View {
    private CategoryContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CategoryPresenter(new NewsRepository(context()));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.atachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_category;
    }

    @Override
    public void setUpViews() {

    }

    @Override
    public void showCategroy(List<Category> showcategory) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recy_Category);
        Category_Adapter category_adapter = new Category_Adapter(showcategory);
        recyclerView.setLayoutManager(new GridLayoutManager(context(), 2));
        recyclerView.setAdapter(category_adapter);
    }

    @Override
    public void showProgressBar(boolean isShowProgressBar) {
        getBaseActivity().setProgressBar(isShowProgressBar);

    }

    @Override
    public void showError(String error) {
        Toast.makeText(context(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getActivity();
    }
}
