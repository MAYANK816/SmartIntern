package com.example.smartintern;

public class developers {
    String androiddeveloper,webdeveloper,uiux,fullstack,startupname,number;

    public developers() {

    }

    public developers(String androiddeveloper, String webdeveloper, String uiux, String fullstack, String startupname,String number) {
        this.androiddeveloper = androiddeveloper;
        this.webdeveloper = webdeveloper;
        this.uiux = uiux;
        this.number=number;
        this.fullstack = fullstack;
        this.startupname = startupname;
    }

    public String getAndroiddeveloper() {
        return androiddeveloper;
    }

    public void setAndroiddeveloper(String androiddeveloper) {
        this.androiddeveloper = androiddeveloper;
    }

    public String getWebdeveloper() {
        return webdeveloper;
    }

    public void setWebdeveloper(String webdeveloper) {
        this.webdeveloper = webdeveloper;
    }

    public String getUiux() {
        return uiux;
    }

    public void setUiux(String uiux) {
        this.uiux = uiux;
    }

    public String getFullstack() {
        return fullstack;
    }

    public void setFullstack(String fullstack) {
        this.fullstack = fullstack;
    }

    public String getStartupname() {
        return startupname;
    }

    public void setStartupname(String startupname) {
        this.startupname = startupname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
