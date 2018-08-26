// USACO 2017 February, Gold
// Problem 2. Why Did the Cow Cross the Road II
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class nocross {

    private static int N;

    private static int[][] road;
    private static int[][] dp;

    private static int dp(int a, int b) {

        if (a >= N || b >= N) {

            return 0;

        } else if (dp[a][b] == -1) {

            dp[a][b] = dp(a + 1, b);

            for (int i = b; i < N; i++) {

                if (Math.abs(road[1][i] - road[0][a]) <= 4) {

                    dp[a][b] = Math.max(dp[a][b], dp(a + 1, i + 1) + 1);
                    break;

                }

            }

        }

        return dp[a][b];

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("nocross.in"));

        // Code

        N = Integer.parseInt(in.readLine());

        road = new int[2][N];

        dp = new int[N][N];

        for (int[] i : dp)
            Arrays.fill(i, -1);

        for (int i = 0; i < 2 * N; i++) {
            road[i/N][i%N] = Integer.parseInt(in.readLine());
        }

        int result = dp(0, 0);

        PrintWriter out = new PrintWriter("nocross.out");
        out.println(result);
        out.close();

    }

}
