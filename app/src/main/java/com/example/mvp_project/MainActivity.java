package com.example.mvp_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvp_project.base.BaseActivity;
import com.example.mvp_project.bookmark.Bookmark_Fragmnet;
import com.example.mvp_project.category.Category_Fragment;
import com.example.mvp_project.home.Home_Fragment;
import com.example.mvp_project.video.Videos_Fragment;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

public class MainActivity extends BaseActivity {
    private View progressBar;
    private BottomNavigation bottomNavigation;
    private Fragment home_fragment;
    private Fragment category_fragment;
    private Fragment bookmark_Fragment;
    private Fragment videos_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();

    }

    private void setUpViews() {

        progressBar = findViewById(R.id.frameLayout_Progress);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "vazirfa.ttf");
        bottomNavigation.setTypeface(typeface);
        bottomNavigation.setDefaultItem(3);
        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                switch (i) {
                    case R.id.tab_home:
                        if (home_fragment == null)
                            home_fragment = new Home_Fragment();
                        replaceTrancation(home_fragment);
                        break;
                    case R.id.tab_category:
                        if (category_fragment == null)
                            category_fragment = new Category_Fragment();
                        replaceTrancation(category_fragment);
                        break;
                    case R.id.tab_bookmark:
                        if (bookmark_Fragment == null)
                            bookmark_Fragment = new Bookmark_Fragmnet();
                        replaceTrancation(bookmark_Fragment);
                        break;

                    case R.id.tab_videos:
                        if (videos_fragment == null)
                            videos_fragment = new Videos_Fragment();
                        replaceTrancation(videos_fragment);
                        break;


                }
            }
        });
        // bottomNavigation.setSelectedItem(3);
    }

    private void replaceTrancation(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void setProgressBar(boolean isProgress) {
//        if (isProgress)
//        {
//            progressBar.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            progressBar.setVisibility(View.GONE);
//        }
        progressBar.setVisibility(isProgress ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigation.getSelectedItem() == 3) {

            finish();
        } else {
            bottomNavigation.setSelectedItem(3);
        }
    }
}
