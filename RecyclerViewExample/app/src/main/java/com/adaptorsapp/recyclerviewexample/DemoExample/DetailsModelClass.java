package com.adaptorsapp.recyclerviewexample.DemoExample;

public class DetailsModelClass {

    int img;
    String name;
    String course;

    public DetailsModelClass(int img, String name, String course){
        this.img = img;
        this.name = name;
        this.course = course;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
