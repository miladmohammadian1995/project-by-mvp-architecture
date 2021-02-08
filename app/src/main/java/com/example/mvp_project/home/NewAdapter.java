package com.example.mvp_project.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp_project.R;
import com.example.mvp_project.data.News;
import com.example.mvp_project.NewsDetails.NewsDetailsSctivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.BannersViewHolder> {
    private List<News> newsList = new ArrayList<>();

private int positionItem;
    public NewAdapter() {

    }

    public NewAdapter(List<News> newsList) {
        this.newsList = newsList;

    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BannersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_last_news, parent, false);
        return new BannersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannersViewHolder holder, int position) {

        holder.bindBanners(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class BannersViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewBanners;
        private CircleImageView circleImageView;
        private TextView txtDate, txtTitle, txtWriter,txtVideos;

        public BannersViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBanners = itemView.findViewById(R.id.image_Last_News_Main);
            circleImageView = itemView.findViewById(R.id.image_Writer_Main_News);
            txtDate = itemView.findViewById(R.id.txt_date_main_news);
            txtTitle = itemView.findViewById(R.id.txt_title_Main_news);
            txtWriter = itemView.findViewById(R.id.txt_writer_main_news);
            txtVideos = itemView.findViewById(R.id.txt_videos_show);
        }

        public void bindBanners(final News news) {
            if (news.isVideoShow())
            {
                txtVideos.setText("# ویدئو");
            }
            else
            {
               txtVideos.setText("# مقاله");
            }

            Picasso.get().load(news.getImage()).into(imageViewBanners);
            Picasso.get().load(news.getImage_writer()).into(circleImageView);
            txtWriter.setText(news.getWriter());
            txtTitle.setText(news.getTitle());
            txtDate.setText(news.getDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionItem=getAdapterPosition();
                    Intent intent = new Intent(itemView.getContext(), NewsDetailsSctivity.class);
                    intent.putExtra("details", news);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsBookmark(News news)
    {
        newsList.set(positionItem,news);
        notifyItemChanged(positionItem);
    }
}
