package ru.stqa.pft.sandbox;

public class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(x - a.x, 2) + Math.pow(y - b.y, 2));
    }

    boolean isSame(Point a) {
        if (x == a.x && y == a.y) {
            return true;
        } else {
            return false;
        }
    }

    double getRadius() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        return this.getDistance(a, b);
    }
}