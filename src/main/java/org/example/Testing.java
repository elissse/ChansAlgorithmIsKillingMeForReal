package org.example;

import java.util.List;

public class Testing {
    public void testing() {
        int[] ns = {1009, 2003, 3001, 4001, 5003, 6007, 7001, 8089, 9013, 10079, 15077, 20071, 30103, 40093, 50069, 60037,
                70061, 80039, 90023, 100129, 110017, 120047, 130073, 140069, 150089, 160049, 170057, 180001, 190121, 200017,
                210037, 220151, 230003, 240049, 250013, 260089, 270029, 280009, 290033, 300007};
        int[] ms = {16, 32};
        for (int m : ms) {
            System.out.println(m);
            for (int n : ns) {
                while (true) {
                    Main.iterations = 0;
                    BruteForceCollinearPoints bfcl = new BruteForceCollinearPoints();
                    List<Main.Point> points = bfcl.notDumb(n);
                    Main.start = System.nanoTime();
                    List<Integer> hull = Main.chans(points);
                    Main.end = System.nanoTime();
                    if (hull.size() == m) {
                        System.out.println(n + " " + Main.iterations + " " + (Main.end - Main.start));
                        break;
                    }
                }
            }
        }
    }
}
