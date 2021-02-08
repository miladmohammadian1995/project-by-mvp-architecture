package com.example.mvp_project.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvp_project.MainActivity;
import com.example.mvp_project.R;

public abstract class BaseFragment extends Fragment {
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView==null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
            setUpViews();
        }
        return rootView;
    }

    public abstract int getLayoutRes();
    public abstract void setUpViews();
    public BaseActivity getBaseActivity()
    {
        return (MainActivity) getActivity();
    }

}
