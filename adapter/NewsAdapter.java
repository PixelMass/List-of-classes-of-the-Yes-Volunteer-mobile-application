package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.NewsItemPage;
import com.example.myapplication.NewsPage;
import com.example.myapplication.R;
import com.example.myapplication.model.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    List<News> newsAll;

    public NewsAdapter(Context context, List<News> newsAll) {
        this.context = context;
        this.newsAll = newsAll;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsItems = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsAdapter.NewsViewHolder(newsItems);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int imageId = context.getResources().getIdentifier("ic_" + newsAll.get(position).getImg(),"drawable", context.getPackageName());
        holder.newsImage.setImageResource(imageId);
        holder.newsTitle.setText(newsAll.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsItemPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        new Pair<View,String>(holder.newsImage,"newsImage")
                        );

                intent.putExtra("newsImage", imageId);
                intent.putExtra("newsTitle",newsAll.get(position).getTitle());
                intent.putExtra("newsAbout",newsAll.get(position).getAbout());
                intent.putExtra("newsSite",newsAll.get(position).getSite());
                context.startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsAll.size();
    }

    public static final class NewsViewHolder extends RecyclerView.ViewHolder{

        ImageView newsImage;
        TextView newsTitle;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            newsImage = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
        }
    }
}
