/*
ID: oliver.6
LANG: JAVA
TASK: nocows
*/

// USACO Training, Chapter 2.3
// Problem 2. Cow Pedigrees
// Not yet tested

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class nocows {

    private static int N;
    private static int K;

    private static int[][] dp;

    private static int dp(int n, int k) {
        if (n % 2 == 0 || 2 * k - 1 > n) dp[n][k] = 0;
        else if (k == 1) dp[n][k] = n == 1 ? 1 : 0;
        else if (dp[n][k] == -1) {
            dp[n][k] = 0;
            for (int i = 1; i < (n + 1) / 2; i += 2) {
                int left = i;
                int right = n - i - 1;
                int res = 0;

                for (int j = 1; j < k - 1; j++) {
                    res += dp(left, j) * dp(right, k - 1);
                    res += dp(left, k - 1) * dp(right, j);
                    res %= 9901;
                }

                res += dp(left, k - 1) * dp(right, k - 1);
                res %= 9901;

                if (left != right) dp[n][k] += 2 * res;
                else dp[n][k] += res;
                dp[n][k] %= 9901;
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("nocows.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        in.close();

        dp = new int[N + 1][K + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        PrintWriter out = new PrintWriter("nocows.out");
        out.println(dp(N, K));
        out.close();

    }

}