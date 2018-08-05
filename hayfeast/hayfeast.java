// USACO 2017 December, Gold
// Problem 3. Haybale Feast
// *****x****

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class hayfeast {

    private static int N;
    private static long M;

    private static long[] flavor;
    private static long[] spice;

    private static long[] psums;

    private static boolean[] visited;

    private static class Haybale implements Comparable<Haybale> {

        int index;
        long flavor;
        long spiciness;

        public Haybale(int index, long flavor, long spiciness) {

            this.index = index;
            this.flavor = flavor;
            this.spiciness = spiciness;

        }

        public int compareTo(Haybale other) {

            if (this.spiciness == other.spiciness)
                return Long.compare(other.flavor, this.flavor);

            return Long.compare(this.spiciness, other.spiciness);

        }

        public String toString() {

            return ("s" + this.spiciness + "f" + this.flavor);

        }

    }

    private static Set<Haybale> bales;

    private static long INF = 1000000000;

    private static long sum(int i, int j) {

        if (i == 0) return psums[j];
        else return psums[j] - psums[i - 1];

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("hayfeast.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        bales = new TreeSet<>();
        flavor = new long[N];
        spice = new long[N];

        psums = new long[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(in.readLine());

            flavor[i] = Integer.parseInt(st.nextToken());
            spice[i] = Integer.parseInt(st.nextToken());

            bales.add(new Haybale(i, flavor[i], spice[i]));

            if (i == 0) psums[i] = flavor[i];
            else psums[i] = psums[i - 1] + flavor[i];

        }

        long max = 0;

        for (Haybale bale : bales) {

            visited[bale.index] = true;

            int i, j;
            for (i = bale.index; ; i++) {

                if (i == N || !visited[i]) {
                    break;
                }

            }
            for (j = bale.index; ; j--) {

                if (j == -1 || !visited[j]) {
                    break;
                }

            }

            max = bale.spiciness;

            if (sum(j + 1, i - 1) >= M) {

                break;

            }

        }

        PrintWriter out = new PrintWriter("hayfeast.out");
        out.println(max);
        out.close();

    }

}
