package ru.iteam.hackaton.models;

public class Course {
    private String text;
    //private int id;
    private String short_info;
    public Course(String text, String short_info){
        this.text = text;
        //this.id = id;
        this.short_info = short_info;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getShort_info() {
        return short_info;
    }

    public void setShort_info(String short_info) {
        this.short_info = short_info;
    }
}
