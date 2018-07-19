/*
ID: oliver.6
LANG: JAVA
TASK: milk
*/

// USACO Training, Chapter 1.4
// Problem 2. Mixing Milk
// **********

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk {

    private static int N;
    private static int M;

    private static int[][] farmers;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("milk.in"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        farmers = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            farmers[i][0] = Integer.parseInt(st.nextToken());
            farmers[i][1] = Integer.parseInt(st.nextToken());
        }

        in.close();

        Arrays.sort(farmers, Comparator.comparingInt(o -> o[0]));

        int total = 0;
        int cost = 0;

        for (int i = 0; total < N; i++) {
            int units = farmers[i][1] < N - total ? farmers[i][1] : N - total;
            total += units;
            cost += units * farmers[i][0];
        }

        PrintWriter out = new PrintWriter("milk.out");
        out.println(cost);
        out.close();

    }

}
