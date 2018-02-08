package ru.job4j;

public class Teacher extends Profession {
    int workExpirience;
    int classNumber;
    public Teacher(String name, int i, String specialization) {
        super(name, i, specialization);
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
