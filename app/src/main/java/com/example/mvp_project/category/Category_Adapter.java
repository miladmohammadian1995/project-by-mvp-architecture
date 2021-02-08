package com.example.mvp_project.category;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp_project.NewsDetails.NewsDetailsSctivity;
import com.example.mvp_project.R;
import com.example.mvp_project.data.Category;
import com.example.mvp_project.liscategory.ListCategory_Activity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Category_Adapter  extends RecyclerView.Adapter<Category_Adapter.CategoryViewHolder> {
    private List<Category> categoryList  = new ArrayList<>();

    public Category_Adapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.bindCategory(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_category);
            textView = itemView.findViewById(R.id.txt_category);
        }
        public void bindCategory(final Category category)
        {

            textView.setText(category.getName());
            Picasso.get().load(category.getImage()).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ListCategory_Activity.class);
                    intent.putExtra("grouping", category.getNumber()+"");
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
