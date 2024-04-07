package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BruteForceCollinearPoints {


    public boolean areCollinear(Main.Point a, Main.Point b, Main.Point c) {
        return (a.y - b.y) * (c.x - b.x) == (c.y - b.y) * (a.x - b.x);
    }

    public void deleteAllCollinear(List<Main.Point> points) {
        List<Main.Point> collinearPoints = new ArrayList<>();
        int n = points.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (areCollinear(points.get(i), points.get(j), points.get(k)) || points.get(i).equals(points.get(j))
                            || points.get(i).equals(points.get(k))) {
                        collinearPoints.add(points.get(i));
                    }
                    if (points.get(j).equals(points.get(k))) collinearPoints.add(points.get(j));
                }
            }
        }
        for (Main.Point x : collinearPoints) {
            points.remove(x);
        }
    }

    public List<Main.Point> generatePoints() {
        List<Main.Point> points = new ArrayList<>();
        Random random = new Random();
        int n = 3000;
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(-100, 100);
            int y = random.nextInt(-100, 100);
            points.add(new Main.Point(x, y));
        }

        deleteAllCollinear(points);

        return points;
    }

}
