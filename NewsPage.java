package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.adapter.NewsAdapter;
import com.example.myapplication.model.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewsPage extends AppCompatActivity {
    private static final String TAG = "NewsPage";

    private RecyclerView newsRecycler;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        List<News> newsList = new ArrayList<>();

        setNewsRecycler(newsList);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("news");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newsList.clear();
                for (DataSnapshot newsSnapshot : dataSnapshot.getChildren()) {
                    News news = newsSnapshot.getValue(News.class);
                    newsList.add(news);
                }
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void setNewsRecycler(List<News> newsList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        newsRecycler = findViewById(R.id.newsRecycler);
        newsRecycler.setLayoutManager(layoutManager);

        newsAdapter = new NewsAdapter(this, newsList);
        newsRecycler.setAdapter(newsAdapter);
    }

    public void startHomePage(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent, options.toBundle());
    }

    public void startNewsPage(View v) {
        Intent intent = new Intent(this, NewsPage.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void startDonatePage(View v) {
        Intent intent = new Intent(this,DonatePageWithVol.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent, options.toBundle());
    }

    public void onClickMenu(View v) {
        Intent intent = new Intent(this, MenuPage.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent, options.toBundle());
    }

    private static long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            finishAffinity();
        else
            Toast.makeText(getBaseContext(), "Натисніть ще раз для виходу",
                    Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}
