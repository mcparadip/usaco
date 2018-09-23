// A-Star Platinum, Week 2
// Problem 1. Ski Lift
// **********

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class skilift {

    private static int N;
    private static int K;
    private static int[] plots;

    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        plots = new int[N];
        for (int i = 0; i < N; i++) {
            plots[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        dp = new int[N];

        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = Integer.MAX_VALUE / 2;
            int h = 0;
            for (int j = 1; j <= Math.min(i, K); j++) {
                if ((long) (plots[i-j] - plots[i]) * h >= (long) (plots[i-h] - plots[i]) * j) {
                    h = j;
                    dp[i] = Math.min(dp[i], dp[i-j] + 1);
                }
            }
        }

        System.out.println(dp[N-1]);

    }

}