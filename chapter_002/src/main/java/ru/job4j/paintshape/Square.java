package ru.job4j.paintshape;

public class Square implements Shape {
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("88888");
        pic.append(System.lineSeparator());
        pic.append("8   8");
        pic.append(System.lineSeparator());
        pic.append("8   8");
        pic.append(System.lineSeparator());
        pic.append("8   8");
        pic.append(System.lineSeparator());
        pic.append("88888");
        return pic.toString();
    }

}
