package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PointTest {

    @Test
    public void testPoint() {
        Point a = new Point(1, 1);
        assertTrue(new Point(1, 1).isSame(a));
    }
}