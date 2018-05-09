package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PointTest {

    @Test
    public void testFirstDistance() {
        Point p1 = new Point(0, 5);
        Point p2 = new Point(0, 4);
        assertEquals(p1.distance(p2), 1.0);
    }

    @Test
    public void testSecondDistance() {
        Point p1 = new Point(6.00, 3.000);
        Point p2 = new Point(35, 7.00);
        assertEquals(p1.distance(p2), 29.274562336608895);
    }
}