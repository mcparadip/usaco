/*
ID: oliver.6
LANG: JAVA
TASK: comehome
*/

// USACO Training, Chapter 2.4
// Problem 5. Bessie Come Home
// *********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class comehome {

    private static int N;

    private static int[][] path;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("comehome.in"));
        N = Integer.parseInt(in.readLine());
        path = new int[52][52];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            char a = st.nextToken().charAt(0);
            int an = Character.toLowerCase(a) - 'a';
            if (Character.isUpperCase(a)) an += 26;
            char b = st.nextToken().charAt(0);
            int bn = Character.toLowerCase(b) - 'a';
            if (Character.isUpperCase(b)) bn += 26;
            int dist = Integer.parseInt(st.nextToken());
            if (path[an][bn] == 0 || path[an][bn] > dist) {
                path[an][bn] = dist;
                path[bn][an] = dist;
            }
        }
        in.close();

        int[] dist = new int[52];
        boolean[] visited = new boolean[52];

        for (int i = 0; i < 52; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[51] = 0;

        for (;;) {

            int minIndex = -1;
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < 52; i++) {
                if (visited[i]) continue;
                if (dist[i] < min) {
                    min = dist[i];
                    minIndex = i;
                }
            }

            if (minIndex == -1) break;
            visited[minIndex] = true;

            for (int i = 0; i < 52; i++) {
                if (visited[i]) continue;
                if (path[minIndex][i] == 0) continue;

                int alt = dist[minIndex] + path[minIndex][i];
                if (alt < dist[i]) {
                    dist[i] = alt;
                }
            }

        }

        PrintWriter out = new PrintWriter("comehome.out");
        int minIndex = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 26; i < 51; i++) {
            if (dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
            System.out.println((char) ('A' + i - 26) + " " + dist[i]);
       }
        out.println((char) ('A' + minIndex - 26) + " " + min);
        out.close();

    }

}