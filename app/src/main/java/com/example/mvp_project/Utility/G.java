package com.example.mvp_project.Utility;

import android.app.Application;
import android.content.Context;

import com.example.mvp_project.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class G extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("vazirfa.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

    }

}
