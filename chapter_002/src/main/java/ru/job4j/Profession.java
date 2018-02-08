package ru.job4j;

public class Profession {
    String name;
    int age;
    String specialization;

    public Profession(String name, int age, String specialization) {
        this.name = name;
        this.age = age;
        this.specialization = specialization;
    }

    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public String getSpecialization() {
        return this.specialization;
    }
}
