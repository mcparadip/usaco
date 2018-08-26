/*
ID: oliver.6
LANG: JAVA
TASK: stamps
*/

// USACO Training, Chapter 3.1
// Problem 6. Stamps
// **********

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class stamps {

    private static int K;
    private static int N;
    private static int[] stamps;

    private static int[] dp;

    private static int dp(int i) {
        if (i == 0) return 0;
        if (i < 0) return Integer.MAX_VALUE;
        if (dp[i] == -1) {
            dp[i] = Integer.MAX_VALUE - 1;
            for (int stamp : stamps) {
                dp[i] = Math.min(dp[i], dp(i - stamp));
            }
            dp[i]++;
        }
        return dp[i];
    }

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new FileReader("stamps.in"));
        K = in.nextInt();
        N = in.nextInt();
        int max = 0;
        stamps = new int[N];
        for (int i = 0; i < N; i++) {
            stamps[i] = in.nextInt();
            max = Math.max(max, stamps[i]);
        }
        max *= K;
        in.close();

        dp = new int[max + 1];
        Arrays.fill(dp, -1);

        PrintWriter out = new PrintWriter("stamps.out");
        int c = 0;
        int m = 0;
        for (int i = 1; i <= max; i++) {
            if (dp(i) <= K) {
                // System.out.println(i);
                c++;
            } else {
                // System.out.println("----");
                m = Math.max(c, m);
                c = 0;
            }
        }
        m = Math.max(c, m);
        c = 0;
        out.println(m);
        out.close();

    }

}