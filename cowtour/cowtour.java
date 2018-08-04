/*
ID: oliver.6
LANG: JAVA
TASK: cowtour
*/

// USACO Training, Chapter 2.4
// Problem 4. Cow Tours
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class cowtour {

    private static int N;
    private static int F;

    private static int[] x;
    private static int[] y;

    private static int[] field;

    private static boolean[][] adj;

    private static double dist(int i, int j) {
        return Math.sqrt(Math.pow(x[j] - x[i], 2) + Math.pow(y[j] - y[i], 2));
    }

    private static double[][] floydWarshall() {

        double[][] dist = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i] != field[j]) {
                    dist[i][j] = Integer.MAX_VALUE;
                } else if (adj[i][j]) {
                    dist[i][j] = dist(i, j);
                } else if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("cowtour.in"));
        N = Integer.parseInt(in.readLine());
        x = new int[N];
        y = new int[N];
        adj = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            String line = in.readLine();
            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == '1') {
                    adj[i][j] = true;
                }
            }
        }
        in.close();

        field = new int[N];
        Arrays.fill(field, -1);

        Deque<Integer> bfs = new ArrayDeque<>();
        int f = 0;
        main:
        for (;;) {
            check: {
                for (int i = 0; i < N; i++) {
                    if (field[i] == -1) {
                        bfs.push(i);
                        break check;
                    }
                }
                break main;
            }
            while (!bfs.isEmpty()) {
                int v = bfs.pop();
                field[v] = f;
                for (int i = 0; i < N; i++) {
                    if (field[i] == -1 && adj[v][i]) {
                        bfs.push(i);
                    }
                }
            }
            f++;
        }

        F = f;
        double[] diam = new double[N];
        double[] fdiam = new double[F];

        double[][] initDist = floydWarshall();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i] != field[j]) continue;
                diam[i] = Math.max(diam[i], initDist[i][j]);
            }
            fdiam[field[i]] = Math.max(fdiam[field[i]], diam[i]);
        }

        double min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i] == field[j]) continue;
                
                double d = Math.max(diam[i] + dist(i, j) + diam[j],
                           Math.max(fdiam[field[i]],
                                    fdiam[field[j]]));
                                
                min = Math.min(d, min);
            }
        }

        PrintWriter out = new PrintWriter("cowtour.out");
        DecimalFormat df = new DecimalFormat("#.000000");
        out.println(df.format(min));
        out.close();

    }

}