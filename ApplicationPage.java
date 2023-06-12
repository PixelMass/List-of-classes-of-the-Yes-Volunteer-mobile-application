package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.model.Course;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApplicationPage extends AppCompatActivity {

    private EditText applicationTitle, applicationCity, applicationDate, applicationPhone, applicationAbout;
    private DatabaseReference mDataBase;
    private String COURSE_KEY = "courses";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_page);
        init();
    }

    private void init() {
        applicationTitle = findViewById(R.id.applicationTitle);
        applicationCity = findViewById(R.id.applicationCity);
        applicationDate = findViewById(R.id.applicationDate);
        applicationAbout = findViewById(R.id.applicationAbout);
        applicationPhone = findViewById(R.id.applicationPhone);
        mDataBase = FirebaseDatabase.getInstance().getReference(COURSE_KEY);
    }

    public void onClickApplyApplication(View view) {
        DatabaseReference newCourseRef = mDataBase.push();
        String key = newCourseRef.getKey();
        int id = newCourseRef.hashCode();
        String title = applicationTitle.getText().toString();
        String city = applicationCity.getText().toString();
        String data = applicationDate.getText().toString();
        String num = applicationPhone.getText().toString();
        String about = applicationAbout.getText().toString();

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(data) && !TextUtils.isEmpty(num) && !TextUtils.isEmpty(about)) {
            Course newCourse = new Course(id, title, city, data, num, about, 0);
            newCourseRef.setValue(newCourse);

            Toast.makeText(this, "Запрос успешно отправлен", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else {
            Toast.makeText(this, "Вы не заполнили все поля", Toast.LENGTH_SHORT).show();
        }
    }


    public void startHomePage(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent, options.toBundle());
    }

    public void startApplicationPage(View v) {
        Intent intent = new Intent(this, ApplicationPage.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void startDonatePage(View v) {
        Intent intent = new Intent(this, DonatePageWithPeople.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(intent, options.toBundle());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
