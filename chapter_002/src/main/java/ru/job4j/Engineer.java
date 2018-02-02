package ru.job4j;

public class Engineer extends Profession {
    public boolean higerEducation;
    public int houseFloors;

    public boolean getHigerEducation() {
        return this.higerEducation;
    }
    public int getHouseFloors() {
        return this.houseFloors;
    }

    public String projectBuild() {
        return "проектирует " + getHouseFloors() + "-этажный дом";
    }
    public void createDrawning() {
    }
}
