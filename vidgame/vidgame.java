// A-Star Platinum, Week 2
// Problem 3. Video Game Troubles
// **********

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class vidgame {

    private static int N;
    private static int V;

    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        dp = new int[2][V+1];
        for (int i = 0; i <= V; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int price = Integer.parseInt(st.nextToken());
            int games = Integer.parseInt(st.nextToken());
            for (int j = price; j <= V; j++) {
                dp[1][j] = dp[0][j-price];
            }
            for (int j = 0; j < games; j++) {
                int p = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                for (int k = V - p; k >= price; k--) {
                    dp[1][k + p] = Math.max(dp[1][k + p], dp[1][k] + v);
                }
            }
            for (int j = price; j <= V; j++) {
                dp[0][j] = Math.max(dp[0][j], dp[1][j]);
            }
        }
        in.close();

        System.out.println(dp[0][V]);

    }

}