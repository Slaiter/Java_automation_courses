package ru.stqa.pft.sandbox;

public class PointTest {

    public static void main(String[] args) {
        Point firstPoint = new Point(5.0, 5.0);
        Point secondPoint = new Point(10.0, 10.0);
        displayOnScreen(firstPoint.distance(secondPoint));
        displayOnScreen(secondPoint.distance(firstPoint));
    }

    public static void displayOnScreen (double distance) {
        System.out.println("Расстояние между двумя случайными точками равно = " + distance);
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p2.y - p1.y), 2));
    }
}