package com.example.birthdayrememberer;

public class Bday {

    String name;
    String image;
    long date;

   void  bday (String name, String image, long date ) {

                this.name = name;
                this.image = image;
                this.date = date;

    }


    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public long getDate() {
        return date;
    }
}
