// A-Star Platinum, Week 1
// Problem 3. Treasure Chest
// **********

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class treasure {

    private static int N;

    private static int[] coin;
    private static int[] prefixsums;
    private static int[][] dp;

    private static int sum(int i, int j) {

        if (i == 0)
            return prefixsums[j];
        return prefixsums[j] - prefixsums[i-1];

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());

        coin = new int[N];
        prefixsums = new int[N];
        dp = new int[2][N];

        for (int i = 0; i < N; i++) {

            coin[i] = Integer.parseInt(in.readLine());

            if (i > 0)
                prefixsums[i] += coin[i] + prefixsums[i-1];
            else
                prefixsums[i] += coin[i];

        }

        dp[0][0] = coin[0];
        dp[(N-1)%2][N-1] = coin[N-1];

        for (int i = N - 2; i >= 0; i--) {

            for (int j = 1; j < N; j++) {

                if (i == j)
                    dp[i%2][j] = coin[i];
                else
                    dp[i%2][j] = Math.max(
                        coin[i] + sum(i+1, j) - dp[(i+1)%2][j],
                        coin[j] + sum(i, j-1) - dp[i%2][j-1]
                    );

            }
            
        }

        System.out.println(dp[0][N-1]);

    }

}
