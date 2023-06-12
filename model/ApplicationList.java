package com.example.myapplication.model;

public class ApplicationList {
    String category;
    String id,title, city, data, num, about;

    public ApplicationList(String id, String title, String city, String data, String num, String about) {
        this.category = category;
        this.id = id;
        this.title = title;
        this.city = city;
        this.data = data;
        this.num = num;
        this.about = about;
    }

    public String getCategoty() {
        return category;
    }

    public void setCategoty(String categoty) {
        this.category = categoty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
