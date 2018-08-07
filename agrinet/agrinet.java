/*
ID: oliver.6
LANG: JAVA
TASK: agrinet
*/

// USACO Training, Chapter 3.1
// Problem 2. Agri-Net
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class agrinet {

    private static int N;

    private static int[][] path;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("agrinet.in"));
        N = Integer.parseInt(in.readLine());
        path = new int[N][N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!st.hasMoreTokens()) st = new StringTokenizer(in.readLine());
                path[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        in.close();

        int[] dist = new int[N];
        boolean[] visited = new boolean[N];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        int r = 0;

        for (;;) {
            int e = -1;
            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;
                if (e == -1 || dist[i] < dist[e]) {
                    e = i;
                }
            }
            if (e == -1) break;
            r += dist[e];
            visited[e] = true;
            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;
                if (path[e][i] < dist[i]) dist[i] = path[e][i];
            }
        }

        PrintWriter out = new PrintWriter("agrinet.out");
        out.println(r);
        out.close();

    }

}