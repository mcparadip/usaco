// USACO 2008 February, Silver
// Problem 2. Meteor Shower
// Not yet tested

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class identifier {

    

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("identifier.in"));
        in.close();

        PrintWriter out = new PrintWriter("identifier.out");
        out.close();

    }

}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class meteor {

    private static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        List<Point> getNeighbors() {

            List<Point> result = new ArrayList<>();

            if (x > 0)
                result.add(new Point(x-1, y));
            if (x < 299)
                result.add(new Point(x+1, y));
            if (y > 0)
                result.add(new Point(x, y-1));
            if (y < 299)
                result.add(new Point(x, y+1));

            return result;

        }

        @Override
        public int hashCode() {
            return 300 * x + y;
        }

        @Override
        public boolean equals(Object obj) {

            if (obj instanceof Point) {

                Point pnt = (Point) obj;

                if (pnt.x == this.x && pnt.y == this.y) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        }

        @Override
        public String toString() {

            return "(" + x + ", " + y + ")";

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("meteor.in"));

        int[][] map = new int[400][400];

        for (int[] row : map) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        int M = Integer.parseInt(in.readLine());

        for (int i = 0; i < M; i++) {

            StringTokenizer st = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (map[x][y] > t)
                map[x][y] = t;
            if (x > 0 && map[x-1][y] > t)
                map[x - 1][y] = t;
            if (x < 299 && map[x+1][y] > t)
                map[x + 1][y] = t;
            if (y > 0 && map[x][y-1] > t)
                map[x][y - 1] = t;
            if (y < 299 && map[x][y+1] > t)
                map[x][y + 1] = t;

        }

        PrintWriter out = new PrintWriter("meteor.out");

        Set<Point> bfs = new HashSet<>();
        bfs.add(new Point(0, 0));

        int t = 0;

        for (;;) {

            Object[] arr = bfs.toArray();
            for (Object ptn : arr) {
                Point point = (Point) ptn;

                bfs.remove(point);

                bfs.addAll(point.getNeighbors());

            }

            t++;

            arr = bfs.toArray();
            for (Object ptn : arr) {
                Point p = (Point) ptn;

                if (map[p.x][p.y] == Integer.MAX_VALUE) {

                    out.println(t);
                    out.close();
                    return;

                }

                if (map[p.x][p.y] <= t) {

                    bfs.remove(p);

                }

            }

            if (bfs.size() == 0) {
                out.println(-1);
                out.close();
                return;
            }

        }

    }

}
