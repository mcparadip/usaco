/*
ID: oliver.6
LANG: JAVA
TASK: money
*/

// USACO Training, Chapter 2.3
// Problem 4. Money Systems
// Not yet tested

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class money {

    private static int V;
    private static int N;

    private static Set<Integer> coins;
    private static long[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("money.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        V = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        coins = new HashSet<>();
        int ii = 0;
        for (; ii < V; ii++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(in.readLine());
            coins.add(Integer.parseInt(st.nextToken()));
        }
        in.close();

        dp = new long[N+1];

        dp[0] = 1;

        for (int coin : coins) {
            for (int i = 0; i <= N; i++) {
                if (i - coin >= 0) dp[i] += dp[i - coin];
            }
        }

        PrintWriter out = new PrintWriter("money.out");
        out.println(dp[N]);
        out.close();

    }

}