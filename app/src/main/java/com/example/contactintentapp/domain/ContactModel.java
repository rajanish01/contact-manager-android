package com.example.contactintentapp.domain;

import java.io.Serializable;

public class ContactModel implements Serializable {

    private String name;
    private int thumbnail;
    private int number;
    private String location;
    private String website;

    public ContactModel() {
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
