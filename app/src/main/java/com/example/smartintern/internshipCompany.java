package com.example.smartintern;

public class internshipCompany {
    String image,title,cgpa;

    public internshipCompany() {

    }
    public internshipCompany(String image, String title, String cgpa) {
        this.image = image;
        this.title = title;
        this.cgpa = cgpa;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
