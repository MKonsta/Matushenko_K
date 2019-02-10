package ru.job4j.servlets.ajaxjquery;

public class Person {
    private String name;
    private String sirname;
    private String gender;
    private String discription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSirname() {
        return sirname;
    }

    public void setSirname(String sirname) {
        this.sirname = sirname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sirname='" + sirname + '\'' +
                ", gender='" + gender + '\'' +
                ", discription='" + discription + '\'' +
                '}';
    }
}
