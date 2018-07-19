/*
ID: oliver.6
LANG: JAVA
TASK: wormhole
*/

// USACO Training, Chapter 1.4
// Problem 7. Wormholes
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class wormhole {

    private static int N;
    private static int wormholes[][];
    private static int right[];

    private static boolean cycleExists(int partner[]) {
        for (int i = 1; i <= N; i++) {
            int pos = i;
            for (int j = 0; j < N; j++) {
                pos = right[partner[pos]];
            }
            if (pos > 0) return true;
        }
        return false;
    }

    private static int solve(int partner[]) {

        for (int i = 1; i <= N; i++) {
            if (partner[i] == 0) {

                int total = 0;

                for (int j = i + 1; j <= N; j++) {
                    if (partner[j] == 0) {
                        partner[i] = j;
                        partner[j] = i;

                        total += solve(partner);

                        partner[i] = 0;
                        partner[j] = 0;
                    }
                }

                return total;
            }
        }

        if (cycleExists(partner)) {
            return 1;
        } else {
            return 0;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("wormhole.in"));
        N = Integer.parseInt(in.readLine());

        wormholes = new int[N+1][2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            wormholes[i][0] = Integer.parseInt(st.nextToken());
            wormholes[i][1] = Integer.parseInt(st.nextToken());
        }

        in.close();

        right = new int[N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (wormholes[i][1] == wormholes[j][1] && wormholes[j][0] > wormholes[i][0]) {
                    if (right[i] == 0 || wormholes[j][0] < wormholes[right[i]][0]) {
                        right[i] = j;
                    }
                }
            }
        }

        PrintWriter out = new PrintWriter("wormhole.out");
        out.println(solve(new int[N+1]));
        out.close();

    }

}