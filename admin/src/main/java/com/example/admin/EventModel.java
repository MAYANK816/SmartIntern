package com.example.admin;

public class EventModel {
    public String imageUrl;

    public EventModel() {

    }

    public EventModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
