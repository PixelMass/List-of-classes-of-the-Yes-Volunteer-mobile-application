package com.example.myapplication.model;

public class Course {

    int  id, category;
    String title, city, data, num, about;


    public Course(int id, String title, String city, String data, String num, String about, int category) {
        this.id = id;
        this.title = title;
        this.city = city;
        this.data = data;
        this.num = num;
        this.about = about;
        this.category = category;

    }

    public Course() {
        // Пустой конструктор без аргументов
    }

    public int getCategory() {
        return category;
    }

    public String getNum() {
        return num;
    }

    public String getAbout() {
        return about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getData() {
        return data;
    }

}
