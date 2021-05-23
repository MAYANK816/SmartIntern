package com.example.admin;

public class BooksModel {
    String url;
    String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BooksModel(String url, String name) {
        this.url = url;
        this.name = name;

    }
}
