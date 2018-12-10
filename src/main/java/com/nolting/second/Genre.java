package com.nolting.second;

public class Genre {
    private String name;
    private String rating;

    public Genre(String name, String rating) {
        this.name = name;
        this.rating = rating;
    }

    public Genre() {

    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


}
