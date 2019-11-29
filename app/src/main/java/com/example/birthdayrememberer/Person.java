package com.example.birthdayrememberer;

public class Person {
    private String name;
    private  String image;
    private long birthDayDate;

    Person (String name, String image, long birthDaydate){

        this.name = name;
        this.image = image;
        this.birthDayDate= birthDaydate;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public long getBirthDayDate() {
        return birthDayDate;
    }
}
