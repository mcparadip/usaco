/*
ID: oliver.6
LANG: JAVA
TASK: milk3
*/

// USACO Training, Chapter 1.5
// Problem 3. Mother's Milk
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class milk3 {

    private static int[] max;

    private static boolean[][][] visited;
    private static boolean[] counted;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        max = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        in.close();

        visited = new boolean[max[0]+1][max[1]+1][max[2]+1];
        counted = new boolean[max[2]+1];

        ArrayDeque<int[]> bfs = new ArrayDeque<>();
        bfs.add(new int[]{0, 0, max[2]});

        while (!bfs.isEmpty()) {

            int[] v = bfs.pop();
            visited[v[0]][v[1]][v[2]] = true;
            if (v[0] == 0) {
                counted[v[2]] = true;
            }

            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    if (from == to) continue;

                    int[] n = v.clone();
                    int amt = Math.min(max[to] - v[to], v[from]);
                    n[from] -= amt;
                    n[to] += amt;

                    if (!visited[n[0]][n[1]][n[2]]) {
                        bfs.add(n);
                    }
                }
            }

        }

        PrintWriter out = new PrintWriter("milk3.out");
        boolean space = false;
        for (int i = 0; i <= max[2]; i++) {
            if (counted[i]) {
                if (space) out.print(" ");
                out.print(i);
                space = true;
            }
        }
        out.println();
        out.close();

    }

}