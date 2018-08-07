/*
ID: oliver.6
LANG: JAVA
TASK: inflate
*/

// USACO Training, Chapter 3.1
// Problem 3. Score Inflation
// Not yet tested

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class inflate {

    private static int M;
    private static int N;
    private static int[][] cats;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("inflate.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cats = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            cats[i][0] = Integer.parseInt(st.nextToken());
            cats[i][1] = Integer.parseInt(st.nextToken());
        }
        in.close();

        dp = new int[M+1];

        for (int i = 0; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                if (cats[j][1] > i) continue;
                dp[i] = Math.max(dp[i], cats[j][0] + dp[i - cats[j][1]]);
            }
        }

        PrintWriter out = new PrintWriter("inflate.out");
        System.out.println(Arrays.toString(dp));
        out.println(dp[M]);
        out.close();

    }

}