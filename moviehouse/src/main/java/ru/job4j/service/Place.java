package ru.job4j.service;

public class Place {
    private int num; //Номер сиденья
    private int price;
    private boolean condition; //Если место свободно то condition == false

    public Place(int num, int price, boolean condition) {
        this.num = num;
        this.price = price;
        this.condition = condition;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Place{" +
                "num=" + num +
                ", price=" + price +
                ", condition=" + condition +
                '}';
    }
}
