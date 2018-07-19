/*
ID: oliver.6
LANG: JAVA
TASK: subset
*/

// USACO Training, Chapter 2.3
// Problem 3. Subset Sums
// *******

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class subset {

    private static int N;

    private static long[][] dp;

    private static long dp(int i, int j) {

        if (dp[i][j] == -1) {

            if (j == 0) {
                if (i == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = 0;
                }
            } else {
                dp[i][j] = dp(i, j - 1);
                if (i - j >= 0) dp[i][j] += dp(i - j, j - 1);
            }

        }

        return dp[i][j];

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("subset.in"));
        N = Integer.parseInt(in.readLine());
        in.close();

        int sum = N * (N + 1) / 2;
        dp = new long[sum][N + 1];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }

        PrintWriter out = new PrintWriter("subset.out");
        if (sum % 2 == 0) {
            out.println(dp(sum / 2, N) / 2);
        } else {
            out.println(0);
        }
        out.close();

    }

}