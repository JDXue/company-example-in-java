package com.starlingbank.externalservices;

public class Course {
    private String name;
    private int id;

    public Course(String name, int id){
        this.name = name;
        this.id = id;
    }

    public Course(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
