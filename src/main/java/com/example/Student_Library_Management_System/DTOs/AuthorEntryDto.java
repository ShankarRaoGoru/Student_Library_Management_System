package com.example.Student_Library_Management_System.DTOs;

public class AuthorEntryDto {
    //just an object used to take req from postman
    //it will contain the information that we want to send from postman
    //id is not here because we dont want to send it from postman
    private String name;
    private int age;
    private String country;
    private double rating;

    public AuthorEntryDto() {
    }

    public AuthorEntryDto(String name, int age, String country, double rating) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}