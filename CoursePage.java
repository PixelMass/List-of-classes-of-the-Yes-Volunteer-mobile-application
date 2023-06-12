package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CoursePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        TextView courseTitle = findViewById(R.id.coursePageTitle);
        TextView courseCity = findViewById(R.id.coursePageCity);
        TextView courseData = findViewById(R.id.coursePageData);
        TextView courseNum = findViewById(R.id.coursePageNum);
        TextView courseAbout = findViewById(R.id.coursePageAbout);

        courseTitle.setText(getIntent().getStringExtra("courseTitle"));
        courseCity.setText(getIntent().getStringExtra("courseCity"));
        courseData.setText(getIntent().getStringExtra("courseData"));
        courseNum.setText(getIntent().getStringExtra("courseNum"));
        courseAbout.setText(getIntent().getStringExtra("courseAbout"));

        courseNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = courseNum.getText().toString();
                dialPhoneNumber(phoneNumber);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @SuppressLint("MissingPermission")
    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
