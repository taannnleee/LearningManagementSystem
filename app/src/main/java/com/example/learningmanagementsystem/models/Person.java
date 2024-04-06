package com.example.learningmanagementsystem.models;

public class Person {

    private int id;

    private String name;



    public long getId() {

        return id;

    }



    public void setId(int id) {

        this.id = id;

    }



    public String getName() {

        return name;

    }



    public void setName(String comment) {

        this.name = comment;

    }



    // Will be used by the ArrayAdapter in the ListView

    @Override

    public String toString() {

        return name;

    }

}
