/*
ID: oliver.6
LANG: JAVA
TASK: lamps
*/

// USACO Training, Chapter 2.2
// Problem 6. Party Lamps
// ********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.lang.StringBuilder;

public class lamps {

    private static int N;
    private static int C;

    private static List<Integer> on;
    private static List<Integer> off;

    private static boolean checkAndAdd(List<Integer> list, String elem) {
        if (Integer.parseInt(elem) == -1) return false;
        list.add(Integer.parseInt(elem) - 1);
        return true;
    }

    private static void button1(boolean[] a) {
        for (int i = 0; i < N; i++) {
            a[i] = !a[i];
        }
    }

    private static void button2(boolean[] a) {
        for (int i = 0; i < N; i += 2) {
            a[i] = !a[i];
        }
    }

    private static void button3(boolean[] a) {
        for (int i = 1; i < N; i += 2) {
            a[i] = !a[i];
        }
    }

    private static void button4(boolean[] a) {
        for (int i = 0; i < N; i += 3) {
            a[i] = !a[i];
        }
    }

    private static boolean isValid(boolean[] a) {
        for (int b : on) {
            if (a[b]) return false;
        }
        for (int b : off) {
            if (!a[b]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("lamps.in"));
        N = Integer.parseInt(in.readLine());
        C = Integer.parseInt(in.readLine());
        on = new ArrayList<>();
        off = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(in.readLine());
        while (checkAndAdd(on, st.nextToken())) {}
        st = new StringTokenizer(in.readLine());
        while (checkAndAdd(off, st.nextToken())) {}
        in.close();

        PrintWriter out = new PrintWriter("lamps.out");

        List<String> res = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        if (i + j + k + l > C || (i + j + k + l) % 2 != C % 2) continue;
                        boolean[] a = new boolean[N];
                        if (i > 0) button1(a);
                        if (j > 0) button2(a);
                        if (k > 0) button3(a);
                        if (l > 0) button4(a);
                        if (isValid(a)) {
                            StringBuilder r = new StringBuilder();
                            for (boolean b : a) {
                                r.append(b ? 0 : 1);
                            }
                            res.add(r.toString());
                        }
                    }
                }
            }
        }

        Collections.sort(res);

        if (res.size() == 0) out.println("IMPOSSIBLE");

        for (String r : res) {
            out.println(r);
        }

        out.close();

    }

}