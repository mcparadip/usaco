/*
ID: oliver.6
LANG: JAVA
TASK: holstein
*/

// USACO Training, Chapter 2.1
// Problem 6. Healthy Holsteins
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class holstein {

    private static int V;
    private static int G;

    private static int[] reqs;

    private static int[][] feeds;

    private static boolean test(int[] combo) {

        int[] total = new int[V];

        for (int feed : combo) {
            for (int i = 0; i < V; i++) {
                total[i] += feeds[feed][i];
            }
        }

        for (int i = 0; i < V; i++) {
            if (total[i] < reqs[i]) return false;
        }

        return true;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
        V = Integer.parseInt(in.readLine());
        reqs = new int[V];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < V; i++) {
            reqs[i] = Integer.parseInt(st.nextToken());
        }
        G = Integer.parseInt(in.readLine());
        feeds = new int[G][V];
        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < V; j++) {
                feeds[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        in.close();

        int[] res = {};

        List<int[]> prev = new ArrayList<>();
        prev.add(new int[]{});

        mainLoop:
        for (int i = 0; i < G; i++) {

            List<int[]> cur = new ArrayList<>();

            for (int[] combo : prev) {

                int start = combo.length == 0 ? 0 : combo[combo.length - 1] + 1;

                for (int j = start; j < G; j++) {

                    int[] newc = Arrays.copyOf(combo, combo.length + 1);
                    newc[combo.length] = j;

                    cur.add(newc);

//                    System.out.println(Arrays.toString(newc));

                    if (test(newc)) {
                        res = newc;
                        break mainLoop;
                    }

                }

            }

            prev = cur;

        }

        PrintWriter out = new PrintWriter("holstein.out");
        out.print(res.length);
        for (int feed : res) {
            out.print(" " + (feed + 1));
        }
        out.println();
        out.close();

    }

}