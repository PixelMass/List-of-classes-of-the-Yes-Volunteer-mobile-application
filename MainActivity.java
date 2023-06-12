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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    @SuppressLint("StaticFieldLeak")
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCoursesList = new ArrayList<>();

    private TextView textViewDays;
    private final Handler handler = new Handler();
    private final Runnable updateCounterRunnable = new Runnable() {
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
        setContentView(R.layout.activity_main);


        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Київ"));
        categoryList.add(new Category(2, "Дніпро"));
        categoryList.add(new Category(3, "Харків"));
        categoryList.add(new Category(4, "Запоріжжя"));
        categoryList.add(new Category(5, "Херсон"));
        categoryList.add(new Category(6, "Миколаїв"));
        categoryList.add(new Category(7, "Черкаси"));
        categoryList.add(new Category(8, "Одеса"));
        categoryList.add(new Category(9, "Кіровоград"));
        categoryList.add(new Category(10, "Черкаси"));

        setCategoryRecycler(categoryList);


        courseList.add(new Course(1, "Психолог", "Дніпро", "1 червня", "+380951948314", "Я психолог, мій стаж складає вже поняд 5-ти років. В цей складний час, коли всі люди переживають лихі часи, тому робота зі психологом - це нормальна практика, тому звоніть, я вам спробую допомогти.",2));
        courseList.add(new Course(2, "Лікар", "Київ", "16 травня", "+380951948314", "test",1));
        courseList.add(new Course(3, "Допоможу з житлом", "Миколаїв", "10 травня", "+380951948314", "text",6));
        courseList.add(new Course(4, "Допоможу з одягом для дітей", "Харків", "29 травня", "+380951948314", "text",3));
        courseList.add(new Course(5, "Допоможу постраждалим", "Київ", "30 травня", "+380951948314", "text",1));
        fullCoursesList.addAll(courseList);

        setCoureRecycler(courseList);

        textViewDays = findViewById(R.id.textViewDays);

        // Инициализация счетчика дней и запуск периодического обновления
        initAndUpdateDaysCounter();
        handler.postDelayed(updateCounterRunnable, getMillisUntilMidnight());


    }

    private void setCoureRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    public void startHomePage(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void startApplicationPage(View v) {

        Intent intent = new Intent(this, ApplicationPage.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent, options.toBundle());
    }

    public void startDonatePage(View v) {

        Intent intent = new Intent(this, DonatePageWithPeople.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent, options.toBundle());
    }

    public void onClickMenu(View v) {
        Intent intent = new Intent(this, MenuPage.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent, options.toBundle());
    }


    @SuppressLint("NotifyDataSetChanged")
    public static void showCoursesByCategory(int category) {

        courseList.clear();
        courseList.addAll(fullCoursesList);

        List<Course> filterCourses = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getCategory() == category)
                filterCourses.add(c);
        }

        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();

    }
    @SuppressLint("SetTextI18n")
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
        assert startDate != null;
        long timeDiff = currentDate.getTime() - startDate.getTime();
        long daysPassed = TimeUnit.MILLISECONDS.toDays(timeDiff);

        // Отображение количества дней
        textViewDays.setText("Війна триває " + (daysPassed + 1) + " днів");
    }

    private long getMillisUntilMidnight() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis() - System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateCounterRunnable);
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


