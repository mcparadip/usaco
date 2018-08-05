// USACO 2009 February, Silver
// Problem 3. Bulls and Cows
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class bullcow {

    private static long N;
    private static long K;

    private static long[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("bullcow.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[(int) N];

        long r = 0;
        long z = 0;

        for (long i = 0; i < N-K-1; i++) {

            r += dp[(int) i];
            z += i + 1;
            dp[(int) (i+K+1)] = (z + r) % 5000011;

        }

        PrintWriter out = new PrintWriter("bullcow.out");
        out.println((dp[(int) (N-1)]+N+1) % 5000011);
        out.close();

    }

}
