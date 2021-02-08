package com.example.mvp_project.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp_project.R;
import com.example.mvp_project.data.Banners;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BannersAdapter extends RecyclerView.Adapter<BannersAdapter.BannersViewHolder> {
    private List<Banners> bannerslist;
    private Context context;

    public BannersAdapter(List<Banners> bannerslist, Context context) {
        this.bannerslist = bannerslist;
        this.context = context;
    }

    @NonNull
    @Override
    public BannersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banners, parent, false);
        return new BannersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannersViewHolder holder, int position) {

        holder.bindBanners(bannerslist.get(position));
    }

    @Override
    public int getItemCount() {
        return bannerslist.size();
    }

    public class BannersViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewBanners;
        private CircleImageView circleImageView;
        private TextView txtDate, txtTitle, txtWriter;

        public BannersViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBanners = itemView.findViewById(R.id.image_Banners);
            circleImageView = itemView.findViewById(R.id.image_Writer_Main);
            txtDate = itemView.findViewById(R.id.txt_Date_Main);
            txtTitle = itemView.findViewById(R.id.txt_Tile_Main);
            txtWriter = itemView.findViewById(R.id.txt_Writer_Main);
        }

        public void bindBanners(Banners banners) {
            Picasso.get().load(banners.getImage()).into(imageViewBanners);
            Picasso.get().load(banners.getImage_writer()).into(circleImageView);
            txtWriter.setText(banners.getWriter());
            txtTitle.setText(banners.getTitle());
            txtDate.setText(banners.getDate());
        }
    }

}
