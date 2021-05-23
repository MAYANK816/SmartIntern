package com.example.admin;

public class AdminDeveloperRequired {
    String androiddev,webdev,uiux,fullstackdevdev,title,number;

    public AdminDeveloperRequired() {

    }

    public AdminDeveloperRequired(String androiddev, String webdev, String uiux, String fullstackdev, String title,String number) {
        this.androiddev = androiddev;
        this.webdev = webdev;
        this.uiux = uiux;
        this.number=number;
        this.fullstackdevdev = fullstackdev;
        this.title = title;
    }

    public String getandroiddev() {
        return androiddev;
    }

    public void setandroiddev(String androiddev) {
        this.androiddev = androiddev;
    }

    public String getwebdev() {
        return webdev;
    }

    public void setwebdev(String webdev) {
        this.webdev = webdev;
    }

    public String getUiux() {
        return uiux;
    }

    public void setUiux(String uiux) {
        this.uiux = uiux;
    }

    public String getfullstackdev() {
        return fullstackdevdev;
    }

    public void setfullstackdev(String fullstackdev) {
        this.fullstackdevdev = fullstackdev;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
