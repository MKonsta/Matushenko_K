package ru.job4j;

public class Teacher extends Profession {
    int workExpirience;
    int classNumber;

    public int getWorkExpirience() {
        return this.workExpirience;
    }
    public int getClassNumber() {
        return this.classNumber;
    }
    public String teach() {
        return getName() + " учит " + getClassNumber() + " класс";
    }
}
