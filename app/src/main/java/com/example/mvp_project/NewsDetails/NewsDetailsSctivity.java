package com.example.mvp_project.NewsDetails;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.mvp_project.R;
import com.example.mvp_project.Utility.CustomImageView;
import com.example.mvp_project.bookmark.BookmarkContract;
import com.example.mvp_project.data.AppDatabase;
import com.example.mvp_project.data.News;
import com.example.mvp_project.data.NewsDataSource;
import com.example.mvp_project.data.NewsRepository;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class NewsDetailsSctivity extends AppCompatActivity {
    public static final String EXTRA_KEYS_NEWS = "news";
    private News news;
    private JZVideoPlayerStandard jzVideoPlayerStandard;
    private FrameLayout frameLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private static final int heitht = 254;
    private static final int witdthe = 400;
    private TextView txtDate;
    private HtmlTextView txtContent;
    private ImageView imageBookmark;
    private String one;
    private String two;
    private NewsDataSource newsDataSource;
    private AppDatabase appDatabase;
    private List<News> getTblBookmark = new ArrayList<>();
    private News infoNews;
    private int idRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details_sctivity);
        newsDataSource = new NewsRepository(this);
        setUpviews();
    }

    private void setUpviews() {

        appDatabase = AppDatabase.getInstance(this);
        newsDataSource = appDatabase.getLocalDataSource();
        getTblBookmark = newsDataSource.getAllBookmark();
        news = getIntent().getParcelableExtra("details");

        txtDate = findViewById(R.id.txt_date_news);

        txtDate.setText(news.getDate());
        txtContent = findViewById(R.id.txt_content_news);
        imageBookmark = findViewById(R.id.image_bookmark);
        // txtContent.setHtml(description, new HtmlHttpImageGetter(txtDescriptionsProducts));
        String content = news.getDescription().replace("upload", "http://mvp2.miladstrike.ir/upload");
        txtContent.setHtml(content, new HtmlHttpImageGetter(txtContent, null, true));
        //  Log.i("c", "setUpviews: "+news.getContent());
        Toolbar toolbar = findViewById(R.id.toolbar_news_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle(news.getTitle());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout = findViewById(R.id.collapsong_toolbar);
        collapsingToolbarLayout.setExpandedTitleTextColor(ContextCompat.getColorStateList(this, android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColorStateList(this, android.R.color.white));
        collapsingToolbarLayout.setTitle(news.getTitle());
        if (news.isVideoShow()) {
            final FrameLayout frameLayout = findViewById(R.id.fram_container_video);
            frameLayout.setVisibility(View.VISIBLE);
            frameLayout.post(new Runnable() {
                @Override
                public void run() {
                    ViewGroup.LayoutParams framelayoutparams = frameLayout.getLayoutParams();
                    framelayoutparams.height = frameLayout.getWidth() * 254 / 400;
                    frameLayout.setLayoutParams(framelayoutparams);
                }
            });
            jzVideoPlayerStandard = findViewById(R.id.jz);
            jzVideoPlayerStandard.setUp(news.getVideo(), JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN);
            jzVideoPlayerStandard.setVisibility(View.VISIBLE);
            Picasso.get().load(news.getImage()).into(jzVideoPlayerStandard.thumbImageView);
            jzVideoPlayerStandard.fullscreenButton.setVisibility(View.GONE);
            jzVideoPlayerStandard.batteryLevel.setVisibility(View.GONE);
            jzVideoPlayerStandard.tinyBackImageView.setVisibility(View.GONE);
            jzVideoPlayerStandard.backButton.setVisibility(View.GONE);
            jzVideoPlayerStandard.videoCurrentTime.setVisibility(View.GONE);
        } else {
            CustomImageView customImageView = findViewById(R.id.image_news_details);
            Picasso.get().load(news.getImage()).into(customImageView);
            customImageView.setVisibility(View.VISIBLE);
        }
        final ImageView imageViewBookmar = findViewById(R.id.image_bookmark);

        for (int i = 0; i < getTblBookmark.size(); i++) {
            infoNews = getTblBookmark.get(i);
            if (news.getId() == infoNews.getId()) {
                idRoom = infoNews.getId();
            }
        }
        if (idRoom==news.getId())
        {
            imageViewBookmar.setImageResource(R.drawable.ic_bookmark_black_24dp);
        }
        else
        {
            imageViewBookmar.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
        }
        imageViewBookmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (news.isBookmarked()) {
//                    imageViewBookmar.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
//                    ;
//                    news.setBookmarked(!news.isBookmarked());
//                    newsDataSource.deleteBookmark(news);
//                } else {
//                    news.setBookmarked(!news.isBookmarked());
//                    newsDataSource.bookmark(news);
//                    //   EventBus.getDefault().post(news);
//                    imageViewBookmar.setImageResource(R.drawable.ic_bookmark_black_24dp);
//                }
                //  imageViewBookmar.setImageResource(news.isBookmarked() ? R.drawable.ic_bookmark_black_24dp : R.drawable.ic_bookmark_border_black_24dp);

                if (idRoom == news.getId()) {
                    //Toast.makeText(NewsDetailsSctivity.this, "equal", Toast.LENGTH_SHORT).show();
                    imageViewBookmar.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                    news.setBookmarked(!news.isBookmarked());
                    newsDataSource.deleteBookmark(news);
                }
                else
                {
                    if (news.isBookmarked())
                    {
                        imageViewBookmar.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                        news.setBookmarked(!news.isBookmarked());
                        newsDataSource.deleteBookmark(news);
                    }
                    else
                    {
                        news.setBookmarked(!news.isBookmarked());
                        newsDataSource.bookmark(news);
                        imageViewBookmar.setImageResource(R.drawable.ic_bookmark_black_24dp);
                    }
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (jzVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        jzVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
