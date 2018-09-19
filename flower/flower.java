// A-Star Platinum, Week 2
// Problem 2. Little Shop of Flowers
// **********

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class flower {

    private static int F;
    private static int V;
    private static int[][] table;
    private static int[][] dp;

    private static int dp(int i, int j) {
        if (i > j) return Integer.MIN_VALUE;
        if (dp[i][j] == 0) {
            if (i == 0 && j == 0) dp[i][j] = table[i][j];
            else if (i == 0) {
                dp[i][j] = Math.max(table[i][j], dp(i, j - 1));
            } else {
                dp[i][j] = Math.max(table[i][j] + dp(i - 1, j - 1),
                                    dp(i, j - 1));
            }
        }
        return dp[i][j];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        F = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        table = new int[F][V];
        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < V; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        in.close();

        dp = new int[F][V];

        System.out.println(dp(F - 1, V - 1));

    }

}