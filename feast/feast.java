// USACO 2015 December, Gold
// Problem 2. Fruit Feast
// ************

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class feast {

    private static int T;
    private static int A;
    private static int B;

    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("feast.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        T = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        dp = new boolean[2][T+1];

        dp[0][0] = true;

        int max = 0;

        for (int i = 0; i <= T; i++) {

            if (dp[0][i]) {

                if (i > max)
                    max = i;

                if (i + A <= T)
                    dp[0][i+A] = true;
                if (i + B <= T)
                    dp[0][i+B] = true;

                dp[1][i/2] = true;

            }

        }

        for (int i = 0; i <= T; i++) {

            if (dp[1][i]) {

                if (i > max)
                    max = i;

                if (i + A <= T)
                    dp[1][i+A] = true;
                if (i + B <= T)
                    dp[1][i+B] = true;

            }

        }

        PrintWriter out = new PrintWriter("feast.out");
        out.println(max);
        out.close();

    }

}
