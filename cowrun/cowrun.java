// A-Star Platinum, Week 1
// Problem 2. Running
// Not yet tested

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class cowrun {

    private static int N;
    private static int M;
    private static int[] D;

    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = new int[N];
        for (int i = 0; i < N; i++) {
            D[i] = Integer.parseInt(in.readLine());
        }
        in.close();

        dp = new int[N+3];

        for (int i = 0; i < N; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            int sum = dp[i];

            for (int j = 0; j < M && i + j * 2 < N; j++) {
                sum += D[i + j];
                dp[i + j * 2 + 2] = Math.max(dp[i + j * 2 + 2], sum);
            }
        }

        System.out.println(dp[N]);

    }

}