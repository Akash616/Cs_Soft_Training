package com.adaptorsapp.recyclerviewexample.onClickListenerExample.modalClass;

import java.io.Serializable;

public class ModalClass implements Serializable {

    private int image;
    private String name;
    private String Course;

    public ModalClass(int img, String name, String course){
        this.image = img;
        this.name = name;
        this.Course = course;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return Course;
    }
}
