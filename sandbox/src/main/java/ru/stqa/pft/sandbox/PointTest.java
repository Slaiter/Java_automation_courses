package ru.stqa.pft.sandbox;

public class PointTest {

    public static void main(String[] args) {

        System.out.println("Задайте координаты первой точки на плоскости: ");
        Point a = new Point(1, 1);

        System.out.println("Задайте координаты второй точки на плоскости: ");
        Point b = new Point(5, 5);

        if (a.isSame(b)) {
            System.out.println("Точки совпадают!");
        } else {
            System.out.println("Расстояние между точками: " + b.getDistance(a, b));
        }

        if (a.getRadius() < b.getRadius()) {
            System.out.println("К началу координат ближе первая точка!");
        } else if (a.getRadius() == b.getRadius()) {
            System.out.println("Точки равноудалены от начала координат!");
        } else {
            System.out.println("К началу координат ближе вторая точка!");
        }
    }
}