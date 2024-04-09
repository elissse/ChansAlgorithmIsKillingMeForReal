package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.*;

public class Main {


    public static void main(String[] args) {
        Integer[][] points = {{1, 1}, {3, 1}, {3, 3}, {1, 2}, {4, 3}, {-1, 2}, {0, -3}, {-3, 2}};
        List<List<Integer>> miniHulls = chan(points);
        List<Integer> jarvsosos = jarviss(points, miniHulls);
        for (Integer x : jarvsosos) System.out.println(x);
    }

    public static List<List<Integer>> chan(Integer[][] points) {
        int n = points.length;
//        for (int t = 1; t < n; t++) {
        List<List<Integer>> miniHulls = new ArrayList<>();
        int m = 4;
        int r = (int) ceil(n / m);
        for (int i = 0; i < r; i++) {
            int start = i * m;
            int end = start + m - 1;
            miniHulls.add(graham(points, start, end));
        }
//        }
        for (List<Integer> list : miniHulls) {
            for (Integer p : list) {
                System.out.print(p + " ");
            }
            System.out.println();
        }
        return miniHulls;
    }

    public static List<Integer> jarvisss(Integer[][] points, List<List<Integer>> miniHulls) {
        int leftessst = miniHulls.get(0).get(0);
        int whichHull = 0;
        for (int i = 1; i < miniHulls.size(); i++) {
            if (points[leftessst][0] > points[miniHulls.get(i).get(0)][0]) {
                leftessst = miniHulls.get(i).get(0);
                whichHull = i;
            }

        }
        List<Integer> hull = new ArrayList<>();
        hull.add(leftessst);
        miniHulls.get(whichHull).remove(0);
        miniHulls.get(whichHull).add(hull.get(0));
        while (true) {
            Integer[] leftmost = new Integer[miniHulls.size()];
            for (int h = 0; h < miniHulls.size(); h++) {
                leftmost[h] = 0;
                for (int i = 0; i < miniHulls.get(h).size(); i++) {
                    if (rotate(points[hull.get(hull.size() - 1)], points[miniHulls.get(h).get(leftmost[h])], points[miniHulls.get(h).get(i)]) > 0) {
                        leftmost[h] = i;
                    }
                }
            }
            for(Integer x:leftmost) System.out.println(x);
            leftessst = 0;
            for (int h = 0; h < miniHulls.size(); h++) {
                if (rotate(points[hull.get(hull.size() - 1)], points[leftmost[leftessst]], points[leftmost[h]]) > 0) {
                    leftessst = leftmost[h];
                    whichHull = h;
                }
            }
            System.out.println(leftessst + " " + whichHull);
            if (miniHulls.get(whichHull).get(leftmost[leftessst]) == hull.get(0)) {
                break;
            } else {
                hull.add(miniHulls.get(whichHull).get(leftmost[leftessst]));
                miniHulls.get(whichHull).remove(leftmost[leftessst]);
            }
        }
        return hull;
    }

    public static List<Integer> jarvis(Integer[][] points, List<List<Integer>> miniHulls) {
        List<Integer> p = new ArrayList<>();
        for (List<Integer> list : miniHulls) {
            for (Integer i : list)
                p.add(i);
        }
        for (int i = 0; i < p.size(); i++) {
            if (points[p.get(i)][0] < points[p.get(0)][0]) {
                Integer t = p.get(i);
                p.set(i, p.get(0));
                p.set(0, t);
            }
        }
        List<Integer> hull = new ArrayList<>();
        hull.add(p.get(0));
        p.remove(0);
        p.add(hull.get(0));
        while (true) {
            int leftmost = 0;
            for (int i = 1; i < p.size(); i++) {
                if (rotate(points[hull.get(hull.size() - 1)], points[p.get(leftmost)], points[p.get(i)]) > 0) {
                    leftmost = i;
                }
            }
            if (p.get(leftmost) == (hull.get(0))) {
                break;
            } else {
                hull.add(p.get(leftmost));
                p.remove(leftmost);
            }
        }
        return hull;
    }

    public static List<Integer> jarviss(Integer[][] points, List<List<Integer>> miniHulls) {
        int leftessst = miniHulls.get(0).get(0);
        int whichHull = 0;
        for (int i = 1; i < miniHulls.size(); i++) {
            if (points[leftessst][0] > points[miniHulls.get(i).get(0)][0]) {
                leftessst = miniHulls.get(i).get(0);
                whichHull = i;
            }
        }
        List<Integer> p = new ArrayList<>();
        for (List<Integer> list : miniHulls) {
            for (Integer i : list)
                p.add(i);
        }
        for (int i = 0; i < p.size(); i++) {
            if (points[p.get(i)][0] < points[p.get(0)][0]) {
                Integer t = p.get(i);
                p.set(i, p.get(0));
                p.set(0, t);
            }
        }
        List<Integer> hull = new ArrayList<>();
        hull.add(p.get(0));
        p.remove(0);
        p.add(hull.get(0));
        while (true) {
            int leftmost = 0;
            for (int i = 1; i < p.size(); i++) {
                if (rotate(points[hull.get(hull.size() - 1)], points[p.get(leftmost)], points[p.get(i)]) > 0) {
                    leftmost = i;
                }
            }
            if (p.get(leftmost) == (hull.get(0))) {
                break;
            } else {
                hull.add(p.get(leftmost));
                p.remove(leftmost);
            }
        }
        return hull;
    }

    public static boolean compare(Integer leftmost, Integer[][] points, Integer i, Integer j) {
        return rotate(points[leftmost], points[i], points[j]) > 0;
    }

    public static void merge(Integer leftmost, Integer[][] points, Integer[] p, Integer[] l, Integer[] r, Integer left, Integer right) {
        Integer i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (compare(leftmost, points, r[j], l[i])) {
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

    public static void mergeSort(Integer leftmost, Integer[][] points, Integer[] p) {
        int n = p.length;
        if (n < 2) {
            return;
        }
        Integer middle = n / 2;
        Integer[] l = new Integer[middle];
        Integer[] r = new Integer[n - middle];

        for (int i = 0; i < middle; i++) {
            l[i] = p[i];
        }
        for (Integer i = middle; i < n; i++) {
            r[i - middle] = p[i];
        }
        mergeSort(leftmost, points, l);
        mergeSort(leftmost, points, r);
        merge(leftmost, points, p, l, r, middle, n - middle);
    }

    public static Integer rotate(Integer[] a, Integer[] b, Integer[] c) {
        return (b[0] - a[0]) * (c[1] - b[1]) - (b[1] - a[1]) * (c[0] - b[0]);
        // если > 0 значит с лежит слева от вектора ав
        // если < 0 значит с лежит справа от вектора ав
        // если = 0 значит с лежит на векторе ав
    }

    public static List<Integer> graham(Integer[][] points, int start, int end) {
        Integer n = end - start + 1;
        Integer[] p = new Integer[n];
        for (int i = start; i <= end; i++) {
            p[i - start] = i;
        }
        //searching for the leftmost point (point with lowest x)
        for (int i = 0; i < p.length; i++) {
            if (points[p[i]][0] < points[p[0]][0]) {
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
            while (rotate(points[s.get(s.size() - 2)], points[s.get(s.size() - 1)], points[p[i]]) > 0) {
                s.remove(s.size() - 1);
            }
            s.add(p[i]);
        }
        return s;
    }

//    public static int binarySearch(List<Integer> p, Integer[][] points, int l, int r, int leftmost, int left) {
//        if (r >= l) {
//            int mid = l + (r - l) / 2;
//            if (!compare(left, points, mid, leftmost)) {
//                leftmost = mid;
//                return binarySearch(p, points, l, mid - 1, leftmost, left);
//            }
//            return binarySearch(p, points, mid + 1, r, leftmost, left);
//        }
//        return leftmost;
//    }
//
//    public static List<Integer> jarvis(Integer[][] points, List<List<Integer>> pp) {
//        int leftest = pp.get(0).get(0);
//        int chosenone = 0;
//        for (int i = 1; i < pp.size(); i++) {
//            System.out.println(points[leftest][0] + " " + points[pp.get(i).get(0)][0]);
//            if (points[leftest][0] > points[pp.get(i).get(0)][0]) {
//                chosenone = i;
//                leftest = pp.get(i).get(0);
//            }
//
//        }
//        System.out.println();
//        System.out.println(chosenone);
//        System.out.println();
//        List<Integer> p = new ArrayList<>();
//        Integer n = points.length;
//        for (Integer i = 0; i < n; i++) {
//            p.add(i);
//        }
//        for (Integer i = 0; i < n; i++) {
//            if (points[p.get(i)][0] < points[p.get(0)][0]) {
//                Integer t = p.get(i);
//                p.set(i, p.get(0));
//                p.set(0, t);
//            }
//        }
//        List<Integer> hull = new ArrayList<>();
//        hull.add(p.get(0));
//        p.remove(0);
//        p.add(hull.get(0));
//        while (true) {
//            int leftmost = 0;
//            for (int i = 1; i < p.size(); i++) {
//                if (rotate(points[hull.get(hull.size() - 1)], points[p.get(leftmost)], points[p.get(i)]) > 0) {
//                    leftmost = i;
//                }
//            }
//            if (p.get(leftmost) == (hull.get(0))) {
//                break;
//            } else {
//                hull.add(p.get(leftmost));
//                p.remove(leftmost);
//            }
//        }
//        return hull;
//    }
}