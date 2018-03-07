package ru.job4j.paintshape;

public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Shape triangle = new Triangle();
        Paint paint = new Paint();
        paint.draw(triangle);
        Shape square = new Square();
        paint.draw(square);
    }
}
