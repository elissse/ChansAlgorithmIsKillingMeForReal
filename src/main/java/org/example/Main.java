package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

public class Main {
    public static int LEFT_TURN = 1;
    public static int RIGHT_TURN = -1;
    public static int COLLINEAR = 0;

    public static class Point {
        public Integer x;
        public Integer y;

        Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    public static void main(String[] args) {
        BruteForceCollinearPoints bfcl = new BruteForceCollinearPoints();
        List<Point> points = bfcl.generatePoints();
        System.out.println(points.size());
        for (Point point : points) System.out.println(point);
        List<Integer> hull = chans(points);
        for (Integer x : hull) System.out.println(x);
    }

    public static List<Integer> figureOutStartAndEnd(List<Point> points, int i, int m, int r) {
        int start, end;
        if (i != r - 1) {
            start = i * m;
            end = start + m - 1;
        } else {
            end = points.size() - 1;
            start = end - m + 1;
        }
        return graham(points, start, end);
    }

    public static List<Integer> chans(List<Point> points) {
        int n = points.size();
        List<Integer> hull = new ArrayList<>();
        for (int t = 1; t < n; t++) {
            List<List<Integer>> miniHulls = new ArrayList<>();
            int m = min((int) pow(2, pow(2, t)), n);
            if (m < n) {
                int r = (int) ceil((double)n / m);
                for (int i = 0; i < r; i++) {
                    miniHulls.add(figureOutStartAndEnd(points, i, m, r));
                }
                hull = jarvisMarch(points, miniHulls, m);
                if (hull.get(0).equals(hull.get(hull.size() - 1))) {
                    hull.remove(hull.size() - 1);
                    break;
                }
            } else {
                hull = graham(points, 0, points.size() - 1);
            }
        }
        return hull;
    }

    public static int mod(int x, int n) {
        int r = x % n;
        return (r >= 0) ? r : r + n;
    }

    public static int tangent(int p, List<Point> points, List<Integer> miniHull) {
        int l = 0;
        int r = miniHull.size() - 1;//is -1 needed here
        int lBefore = orient(points.get(p), points.get(miniHull.get(0)), points.get(miniHull.get(miniHull.size() - 1)));
        int lAfter = orient(points.get(p), points.get(miniHull.get(0)), points.get(miniHull.get((l + 1) % miniHull.size())));
        while (l < r) {
            int c = (l + r) / 2;
            int cBefore = orient(points.get(p), points.get(miniHull.get(c)), points.get(miniHull.get(mod(c - 1, miniHull.size()))));
            int cAfter = orient(points.get(p), points.get(miniHull.get(c)), points.get(miniHull.get((c + 1) % miniHull.size())));
            int cSide = orient(points.get(p), points.get(miniHull.get(l)), points.get(miniHull.get(c)));
            if (cBefore != RIGHT_TURN && cAfter != RIGHT_TURN) return c;
            else if (((cSide == LEFT_TURN) && (lAfter == RIGHT_TURN || lBefore == lAfter)) || (cSide == RIGHT_TURN && cBefore == RIGHT_TURN)) {
                r = c;
            } else {
                l = c + 1;
            }
            lBefore = -cAfter;
            lAfter = orient(points.get(p), points.get(miniHull.get(l)), points.get(miniHull.get((l + 1) % miniHull.size())));
        }
        return l;
    }


    public static List<Integer> jarvisMarch(List<Point> points, List<List<Integer>> miniHulls, int m) {
        List<Integer> hull = new ArrayList<>();
        int leftessst = miniHulls.get(0).get(0);
        int whichHull = 0;
        int index = 0;
        for (int i = 1; i < miniHulls.size(); i++) {
            for (int j = 0; j < miniHulls.get(i).size(); j++)
                if (points.get(leftessst).x < points.get(miniHulls.get(i).get(j)).x) {
                    leftessst = miniHulls.get(i).get(j);
                    whichHull = i;
                    index = j;
                }
        }
        hull.add(leftessst);
        miniHulls.get(whichHull).remove(index);
        miniHulls.get(whichHull).add(hull.get(0));
        for (int k = 0; k < m; k++) {
            Integer[] leftmost = new Integer[miniHulls.size()];
            Integer[] indexes = new Integer[miniHulls.size()];
            for (int h = 0; h < miniHulls.size(); h++) {
                if (!miniHulls.get(h).isEmpty()) {
                    indexes[h] = tangent(hull.get(hull.size() - 1), points, miniHulls.get(h));
                    leftmost[h] = miniHulls.get(h).get(indexes[h]);
                }
            }
            leftessst = leftmost[0];
            whichHull = 0;
            index = indexes[0];

            for (int i = 1; i < leftmost.length; i++) {
                if (orient(points.get(hull.get(hull.size() - 1)), points.get(leftessst), points.get(leftmost[i])) < 0) {
                    leftessst = leftmost[i];
                    whichHull = i;
                    index = indexes[i];
                }
            }
            if (miniHulls.get(whichHull).get(index).equals(hull.get(0))) {
                hull.add(hull.get(0));
                break;
            } else {
                hull.add(leftessst);
                miniHulls.get(whichHull).remove(index);
            }
        }
        return hull;
    }

    public static int compare(Integer leftmost, List<Point> points, Integer i, Integer j) {
        return orient(points.get(leftmost), points.get(i), points.get(j));
    }

    public static void merge(Integer leftmost, List<Point> points, Integer[] p, Integer[] l, Integer[] r, Integer left, Integer right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (compare(leftmost, points, r[j], l[i]) > 0) {
                p[k++] = l[i++];
            } else {
                p[k++] = r[j++];
            }
        }
        while (i < left) {
            p[k++] = l[i++];
        }
        while (j < right) {
            p[k++] = r[j++];
        }
    }

    public static void mergeSort(Integer leftmost, List<Point> points, Integer[] p) {
        int n = p.length;
        if (n < 2) {
            return;
        }
        int middle = n / 2;
        Integer[] l = new Integer[middle];
        Integer[] r = new Integer[n - middle];

        for (int i = 0; i < middle; i++) {
            l[i] = p[i];
        }
        for (int i = middle; i < n; i++) {
            r[i - middle] = p[i];
        }
        mergeSort(leftmost, points, l);
        mergeSort(leftmost, points, r);
        merge(leftmost, points, p, l, r, middle, n - middle);
    }

    public static Integer orient(Point a, Point b, Point c) {
        int orientation = (b.x - a.x) * (c.y - b.y) - (b.y - a.y) * (c.x - b.x);
        if (orientation == 0) return 0;
        return orientation > 0 ? 1 : -1;
        // если > 0 значит с лежит слева от вектора ав
        // если < 0 значит с лежит справа от вектора ав
        // если = 0 значит с лежит на векторе ав
    }

    public static List<Integer> graham(List<Point> points, int start, int end) {

        int n = end - start + 1;
        Integer[] p = new Integer[n];
        for (int i = start; i <= end; i++) {
            p[i - start] = i;
        }
        //searching for the leftmost point (point with lowest x)
        for (int i = 0; i < p.length; i++) {
            if (points.get(p[i]).x > points.get(p[0]).x) {
                Integer t = p[i];
                p[i] = p[0];
                p[0] = t;
            }
        }
        //sorting rest of the points in relation to being lefter to leftmost
        Integer leftmost = p[0];
        p = Arrays.copyOfRange(p, 1, n);
        mergeSort(leftmost, points, p);
        List<Integer> s = new ArrayList<>();
        s.add(leftmost);
        s.add(p[0]);

        for (int i = 1; i < p.length; i++) {
            while (s.size() > 1 && orient(points.get(s.get(s.size() - 2)), points.get(s.get(s.size() - 1)), points.get(p[i])) >= 0) {
                s.remove(s.size() - 1);
            }
            s.add(p[i]);
        }
        return s;
    }

}