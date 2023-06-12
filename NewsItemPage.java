package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsItemPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item_page);

        ImageView newsImage = findViewById(R.id.newsImage);
        TextView newsTitle = findViewById(R.id.newsTitle);
        TextView newsAbout = findViewById(R.id.newsAbout);

        newsImage.setImageResource(getIntent().getIntExtra("newsImage",0));
        newsTitle.setText(getIntent().getStringExtra("newsTitle"));
        newsAbout.setText(getIntent().getStringExtra("newsAbout"));

    }
}