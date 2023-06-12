package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.adapter.CourseAdapter;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Course;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;



public class MainActivity2 extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    CourseAdapter courseAdapter;
    List<Category> categoryList = new ArrayList<>();
    List<Course> courseList = new ArrayList<>();

    private TextView textViewDays;
    private Handler handler = new Handler();
    private Runnable updateCounterRunnable = new Runnable() {
        @Override
        public void run() {
            initAndUpdateDaysCounter();
            handler.postDelayed(this, getMillisUntilMidnight());
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        courseRecycler = findViewById(R.id.courseRecycler);
        textViewDays = findViewById(R.id.textViewDays);


        // Установка макета для курсов
        LinearLayoutManager courseLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        courseRecycler.setLayoutManager(courseLayoutManager);
        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);

        // Загрузка данных из Firebase
        loadCoursesFromFirebase();

        // Инициализация счетчика дней и запуск периодического обновления
        initAndUpdateDaysCounter();
        handler.postDelayed(updateCounterRunnable, getMillisUntilMidnight());

    }



    private void loadCoursesFromFirebase() {
        FirebaseDatabase.getInstance().getReference("courses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseList.clear();
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                    Course course = courseSnapshot.getValue(Course.class);
                    if (course != null) {
                        courseList.add(course);
                    }
                }

                // Сортировка списка курсов по городам
                Collections.sort(courseList, new Comparator<Course>() {
                    @Override
                    public int compare(Course course1, Course course2) {
                        return course1.getCity().compareTo(course2.getCity());
                    }
                });

                courseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибки при загрузке данных
            }
        });
    }

    private void initAndUpdateDaysCounter() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date startDate;
        try {
            startDate = dateFormat.parse("24.02.2022");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        Date currentDate = new Date();
        long timeDiff = currentDate.getTime() - startDate.getTime();
        long daysPassed = TimeUnit.MILLISECONDS.toDays(timeDiff);

        // Отображение количества дней
        textViewDays.setText("Війна триває: " + String.valueOf(daysPassed + 1) + " дней");
    }

    private long getMillisUntilMidnight() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long millisUntilMidnight = calendar.getTimeInMillis() - System.currentTimeMillis();
        return millisUntilMidnight;
    }

    public void startHomePage(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void startNewsPage(View v) {
        Intent intent = new Intent(this, NewsPage.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent, options.toBundle());
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
