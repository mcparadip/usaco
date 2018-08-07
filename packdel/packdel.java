// USACO 2011 March, Silver
// Problem 2. Package Delivery
// ************

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class packdel {

    private static int N;
    private static int M;
    private static int S;
    private static List<Pair>[] graph;

    private static class Pair implements Comparable<Pair> {

        int first;
        int second;

        public Pair(int x, int y) {
            this.first = x;
            this.second = y;
        }

        public int compareTo(Pair other) {

            int x = Integer.compare(this.first, other.first);
            if (x == 0)
                x = Integer.compare(this.second, other.second);
            return x;

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("packdel.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = 0;

        graph = new List[N];

        for (int i = 0; i < N; i++) {

            graph[i] = new ArrayList<>();

        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(in.readLine());

            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Pair(C, B));
            graph[B].add(new Pair(C, A));

        }

        int[] dist = new int[N];

        PriorityQueue<Pair> q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {

            dist[i] = 1000000000;

            if (i == S)
                dist[i] = 0;

            q.add(new Pair(dist[i], i));

        }

        while (!q.isEmpty()) {

            Pair u = q.poll();

            for (Pair v : graph[u.second]) {

                int alt = dist[u.second] + v.first;

                if (alt < dist[v.second]) {
                    dist[v.second] = alt;
                    q.add(new Pair(alt, v.second));
                }

            }

        }

        PrintWriter out = new PrintWriter("packdel.out");
        out.println(dist[N-1]);
        out.close();

    }

}
