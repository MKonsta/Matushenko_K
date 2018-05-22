package ru.job4j.bank;

import java.util.Objects;

public class Account {
    private double value;
    private String requisits;

    public Account(double value, String requisits) {
        this.value = value;
        this.requisits = requisits;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisits() {
        return requisits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.value, value) == 0
                && Objects.equals(requisits, account.requisits);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, requisits);
    }

    @Override
    public String toString() {
        return "Account{"
                + "value=" + value
                + ", requisits='" + requisits + '\''
                + '}';
    }
}
