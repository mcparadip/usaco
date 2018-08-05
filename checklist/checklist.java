// USACO 2016 December, Gold
// Problem 2. Cow Checklist
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class checklist {

    private static int H;
    private static int G;

    private static int h[][];
    private static int g[][];

    private static long dp[][][];

    private static int cost(int[] p1, int[] p2) {
        int x1 = p1[0];
        int y1 = p1[1];
        int x2 = p2[0];
        int y2 = p2[1];
        return Math.abs(x2 - x1) * Math.abs(x2 - x1) + Math.abs(y2 - y1) * Math.abs(y2 - y1);
    }

    private static long dp(int i, int j, int type) {

        if (dp[i][j][type] == -1) {

            if (type == 0) {

                if (j == G) {

                    if (i + 1 == H) {
                        dp[i][j][type] = 0;
                    } else {
                        dp[i][j][type] = cost(h[i], h[i + 1]) + dp(i + 1, j, 0);
                    }

                } else if (i + 1 == H) {

                    dp[i][j][type] = 1000000000;

                } else {

                    dp[i][j][type] = Math.min(
                            dp(i + 1, j, 0) + cost(h[i], h[i + 1]),
                            dp(i + 1, j, 1) + cost(h[i], g[j])
                    );

                }

            } else {

                if (j + 1 == G) {

                    dp[i][j][type] = dp(i, j + 1, 0) + cost(g[j], h[i]);

                } else {

                    dp[i][j][type] = Math.min(
                            dp(i, j + 1, 1) + cost(g[j], g[j + 1]),
                            dp(i, j + 1, 0) + cost(g[j], h[i])
                    );

                }

            }

        }

        return dp[i][j][type];

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("checklist.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        H = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        h = new int[H][2];
        g = new int[G][2];
        dp = new long[H+1][G+1][2];

        for (int i = 0; i < H+1; i++) {
            for (int j = 0; j < G+1; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }

        for (int i = 0; i < H; i++) {

            st = new StringTokenizer(in.readLine());
            h[i][0] = Integer.parseInt(st.nextToken());
            h[i][1] = Integer.parseInt(st.nextToken());

        }

        for (int i = 0; i < G; i++) {

            st = new StringTokenizer(in.readLine());
            g[i][0] = Integer.parseInt(st.nextToken());
            g[i][1] = Integer.parseInt(st.nextToken());

        }

        PrintWriter out = new PrintWriter("checklist.out");
        out.println(dp(0, 0, 0));
        out.close();

    }

}
