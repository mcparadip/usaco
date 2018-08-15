/*
ID: oliver.6
LANG: JAVA
TASK: humble
*/

// USACO Training, Chapter 3.1
// Problem 4. Humble Numbers
// ************

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class humble {

    private static int K;
    private static int N;

    private static int[] primes;
    private static int[] min;
    private static int[] humble;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("humble.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        primes = new int[K];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < K; i++) {
            primes[i] = Integer.parseInt(st.nextToken());
        }
        in.close();

        humble = new int[N + 1];
        min = new int[K];

        humble[0] = 1;

        for (int i = 1; i <= N; i++) {
            int minHum = Integer.MAX_VALUE;
            for (int j = 0; j < K; j++) {
                for (; humble[min[j]] * primes[j] <= humble[i - 1]; min[j]++) {}
                minHum = Math.min(minHum, humble[min[j]] * primes[j]);
            }
            humble[i] = minHum;
        }

        PrintWriter out = new PrintWriter("humble.out");
        out.println(humble[N]);
        out.close();

    }

}