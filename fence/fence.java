/*
ID: oliver.6
LANG: JAVA
TASK: fence
*/

// USACO Training, Chapter 3.3
// Problem 2. Riding the Fences
// Not yet tested

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.StringTokenizer;

public class fence {

    private static final int N = 501;
    private static int F;
    private static int[][] adj;
    private static int[] deg;
    private static Deque<Integer> cir;

    private static void findCircuit(int i) {
        while (deg[i] > 0) {
            int j = 0; while (adj[j][i] == 0) j++;
            adj[j][i]--; adj[i][j]--;
            deg[j]--; deg[i]--;
            findCircuit(j);
        }
        cir.addFirst(i);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("fence.in"));
        F = Integer.parseInt(in.readLine());
        cir = new ArrayDeque<>();
        adj = new int[N][N];
        deg = new int[N];
        for (int i = 0; i < F; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a][b]++;
            adj[b][a]++;
            deg[a]++;
            deg[b]++;
        }
        in.close();

        int start = -1;
        for (int i = 0; i < N; i++) {
            if (deg[i] > 0 && start == -1) start = i;
            if (deg[i] % 2 == 1) {
                start = i;
                break;
            }
        }

        findCircuit(start);

        PrintWriter out = new PrintWriter("fence.out");
        for (int i : cir) {
            out.println(i);
        }
        out.close();

    }

}