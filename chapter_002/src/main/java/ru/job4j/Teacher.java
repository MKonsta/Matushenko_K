package ru.job4j;

public class Teacher extends Profession {
    int workExpirience;
    int classNumber;
    public Teacher(String name, int age, String specialization) {
        super(name, age, specialization);
    }

    public int getWorkExpirience() {
        return this.workExpirience;
    }
    public int getClassNumber() {
        return this.classNumber;
    }
    public String teach(Pupil pupil) {
        return getName() + " учит " + pupil.getName();
    }
}
