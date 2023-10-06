package com.practiceproject.myhomeproject.modalClass;

import java.io.Serializable;

public class RoomsModal implements Serializable {

    int img;
    String location, bedrooms, rent, bathrooms;

    public RoomsModal(int img, String location, String bedrooms, String rent, String bathrooms) {
        this.img = img;
        this.location = location;
        this.bedrooms = bedrooms;
        this.rent = rent;
        this.bathrooms = bathrooms;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }
}
