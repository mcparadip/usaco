/*
ID: oliver.6
LANG: JAVA
TASK: spin
*/

// USACO Training, Chapter 3.2
// Problem 4. Spinning Wheels
// Not yet tested

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class spin {

    private static class Wedge {
        int start;
        int end;

        public Wedge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class Wheel {
        int speed;
        List<Wedge> wedges;

        public Wheel(int speed) {
            this.speed = speed;
            wedges = new ArrayList<>();
        }

        public void addWedge(Wedge wedge) {
            wedges.add(wedge);
        }
    }

    private static List<Wheel> wheels;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("spin.in"));
        wheels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            wheels.add(new Wheel(Integer.parseInt(st.nextToken())));
            int s = Integer.parseInt(st.nextToken());
            for (int j = 0; j < s; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                wheels.get(i).addWedge(new Wedge(a, a + b));
            }
        }
        in.close();

        int r = -1;
        outer:
        for (int i = 0; i < 360; i++) {
            Map<Integer, Integer> m = new HashMap<>();
            for (Wheel wheel : wheels) {
                int add = wheel.speed * i;
                for (Wedge wedge : wheel.wedges) {
                    for (int j = wedge.start + add; j <= wedge.end + add; j++) {
                        m.putIfAbsent(j % 360, 0);
                        m.put(j % 360, m.get(j % 360) + 1);
                    }
                }
            }
            for (Map.Entry<Integer, Integer> e : m.entrySet()) {
                if (e.getValue() == 5) {
                    r = i;
                    break outer;
                }
            }
        }

        PrintWriter out = new PrintWriter("spin.out");
        out.println(r != -1 ? r : "none");
        out.close();

    }

}