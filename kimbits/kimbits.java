/*
ID: oliver.6
LANG: JAVA
TASK: kimbits
*/

// USACO Training, Chapter 3.2
// Problem 3. Stringsorbits
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class kimbits {

    private static int N;
    private static int L;
    private static long I;
    private static int[][] dp;

    private static int dp(int i, int j) {
        if (j == 0) return 1;
        if (i == 0) return 1;
        if (dp[i][j] == -1) {
            dp[i][j] = dp(i - 1, j) + dp(i - 1, j - 1);
        }
        return dp[i][j];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("kimbits.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        I = Long.parseLong(st.nextToken());
        in.close();

        dp = new int[N+1][L+1];
        for (int[] r : dp) Arrays.fill(r, -1);

        PrintWriter out = new PrintWriter("kimbits.out");
        int j = L;
        I -= 1;
        for (int i = N; i > 0; i--) {
            int s = dp(i - 1, j);
            if (s <= I) {
                out.print("1");
                I -= dp(i - 1, j);
                j--;
            } else {
                out.print("0");
            }
        }
        out.println();
        out.close();

    }

}