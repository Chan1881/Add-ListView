package com.example.myapplication;

public class Customer {
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_NAME = "COLUMN_NAME";
    public static final String COLUMN_NUMBER = "COLUMN_NUMBER";
    public static final String COLUMN_ID = "id";

    private String name, number;
    private int id;

    public Customer(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


