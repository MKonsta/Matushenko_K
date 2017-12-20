package ru.job4j.condition;

/**
 * @author Konstantin Matushenko (xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow(this.x-that.x, 2) + Math.pow(this.y-that.y, 2));
    }

    public static void main(String[] args) {
        Point a = new Point(52, 8);
        Point b = new Point(11, 4);

        System.out.println(b.distanceTo(a));
    }
}