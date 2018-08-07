// USACO 2016 December, Gold
// Problem 1. Moocast
// ********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class moocast {

    private static int N;

    private static int[][] nodes;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("moocast.in"));

        N = Integer.parseInt(in.readLine());

        nodes = new int[N][2];

        boolean[] visited = new boolean[N];
        int[] cost = new int[N];
        int maxcost = 0;

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(in.readLine());

            nodes[i][0] =  Integer.parseInt(st.nextToken());
            nodes[i][1] =  Integer.parseInt(st.nextToken());

            visited[i] = false;
            cost[i] = Integer.MAX_VALUE;

        }

        int i = 0;

        for (;;) {

            visited[i] = true;
            cost[i] = 0;

            int min = Integer.MAX_VALUE;
            int mini = -1;

            boolean done = true;

            for (int j = 0; j < N; j++) {

                if (!visited[j]) {

                    done = false;

                    int c = Math.abs(nodes[j][0] - nodes[i][0]) * Math.abs(nodes[j][0] - nodes[i][0]) +
                            Math.abs(nodes[j][1] - nodes[i][1]) * Math.abs(nodes[j][1] - nodes[i][1]);

                    if (c < cost[j]) {
                        cost[j] = c;
                    }

                    if (cost[j] < min) {
                        min = cost[j];
                        mini = j;
                    }

                }

            }

            if (done) break;

            i = mini;

            if (cost[i] > maxcost) {
                maxcost = cost[i];
            }

        }

        PrintWriter out = new PrintWriter("moocast.out");
        out.println(maxcost);
        out.close();

    }

}
