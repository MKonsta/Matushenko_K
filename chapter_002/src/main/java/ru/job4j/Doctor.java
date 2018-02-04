package ru.job4j;

public class Doctor extends Profession {
    int salary;
    String workPlace;
    boolean graduate;
    public String pacientName;

    public int getSalary() {
        return this.salary;
    }
    public String getWorkPlace() {
        return this.workPlace;
    }
    public boolean isGraduate() {
        return this.graduate;
    }
    public String getPacientName() {
        return this.pacientName;
    }


    public String diagnoseHeal(Pacient pacient) {
        return this.name + " лечит " + pacient.getName();
    }
    public void treat() {
    }
    public void makeOperation() {

    }
}
