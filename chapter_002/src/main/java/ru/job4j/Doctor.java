package ru.job4j;

public class Doctor extends Profession {
    int salary;
    String workPlace;
    boolean graduate;
    public String pacientName;

    public Doctor(String name, int age, String specialization) {
        super(name, age, specialization);
    }

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
        return getName() + " лечит " + pacient.getName();
    }
    public void treat() {
    }
    public void makeOperation() {
    }

}
