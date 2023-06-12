package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CoursePage;
import com.example.myapplication.R;
import com.example.myapplication.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.courseTitle.setText(courses.get(position).getTitle());
        holder.courseCity.setText(courses.get(position).getCity());
        holder.courseData.setText(courses.get(position).getData());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, CoursePage.class);


            intent.putExtra("courseTitle", courses.get(position).getTitle());
            intent.putExtra("courseCity", courses.get(position).getCity());
            intent.putExtra("courseData", courses.get(position).getData());
            intent.putExtra("courseNum", courses.get(position).getNum());
            intent.putExtra("courseAbout", courses.get(position).getAbout());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder{

        TextView courseTitle, courseCity, courseData;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseCity = itemView.findViewById(R.id.courseCity);
            courseData = itemView.findViewById(R.id.courseData);
        }
    }

}
