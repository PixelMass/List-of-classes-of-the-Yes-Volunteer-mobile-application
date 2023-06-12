package com.example.myapplication.model;

public class News {

    int id;
    String img, title, about, site;

    public News() {
    }

    public News(int id, String img, String title, String about, String site) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.about = about;
        this.site = site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
