package com.example.admin;

public class ComanyVisitingModel {
    String location,title,cgpa;

    public ComanyVisitingModel() {

    }
    public ComanyVisitingModel(String location, String title, String cgpa) {
        this.location = location;
        this.title = title;
        this.cgpa = cgpa;
    }

    public String getlocation() {
        return location;
    }

    public void setlocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }
}
