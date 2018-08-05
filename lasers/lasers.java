// USACO 2016 December, Gold
// Problem 3. Lasers and Mirrors
// *****ttttt*

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class lasers {

    private static int N;
    private static int[] start;
    private static int[] end;

    private static int[][] nodes;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("lasers.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken()) + 2;
        nodes = new int[N][2];
        int[] dist = new int[N];
        boolean[] visited = new boolean[N];


        nodes[0][0] = Integer.parseInt(st.nextToken());
        nodes[0][1] = Integer.parseInt(st.nextToken());

        dist[0] = 0;

        nodes[N-1][0] = Integer.parseInt(st.nextToken());
        nodes[N-1][1] = Integer.parseInt(st.nextToken());

        dist[N-1] = Integer.MAX_VALUE;

        for (int i = 1; i < N-1; i++) {

            st = new StringTokenizer(in.readLine());

            dist[i] = Integer.MAX_VALUE;

            nodes[i][0] = Integer.parseInt(st.nextToken());
            nodes[i][1] = Integer.parseInt(st.nextToken());

        }

        for (;;) {

            int i = -1;
            int mindist = Integer.MAX_VALUE;

            for (int j = 0; j < N; j++) {

                if (visited[j]) continue;

                if (dist[j] < mindist) {
                    mindist = dist[j];
                    i = j;
                }

            }

            visited[i] = true;

            if (i == N-1) {
                break;
            }

            for (int j = 0; j < N; j++) {

                if (visited[j]) continue;

                if (nodes[i][0] == nodes[j][0] || nodes[i][1] == nodes[j][1]) {
                    int alt = dist[i] + 1;
                    if (alt < dist[j]) {
                        dist[j] = alt;
                    }
                }

            }

        }

        PrintWriter out = new PrintWriter("lasers.out");
        out.println(dist[N-1]-1);
        out.close();

    }

}
